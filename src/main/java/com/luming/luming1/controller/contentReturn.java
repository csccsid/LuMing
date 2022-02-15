package com.luming.luming1.controller;


import com.luming.luming1.DAO.OperationDAO;
import com.luming.luming1.controller.redisUpdate;
import com.luming.luming1.pool.SqlConpool;
import com.luming.luming1.util.Book;
import com.luming.luming1.util.Episode;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.ArrayList;

//返回每章具体内容
@WebServlet(name = "content", value = "/bookcontent")
public class contentReturn extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //获取书id和章节两个参数
        //用stringbuilder，因为之后频繁拼接字符串，节约资源
        StringBuilder bookid = null;
        String episode = null;
        bookid = new StringBuilder(request.getParameter("bookid"));
        String id = request.getParameter("bookid");
        episode = request.getParameter("episode");

        JedisPool jpool;
        jpool = (JedisPool) request.getSession().getServletContext().getAttribute("jedispool");
        Jedis jedis = jpool.getResource();
        Book book = new Book();
        ArrayList<Episode> content = null;

        //如果Redis中数据过期，更新Redis中缓存
        if(!jedis.exists(String.valueOf(bookid)))
        {
            //获取全局数据库连接池
            SqlConpool scp;
            scp = (SqlConpool) request.getSession().getServletContext().getAttribute("dataconpool");

            redisUpdate rup = new redisUpdate();
            rup.update(jedis, scp , String.valueOf(bookid));
        }

        //判断请求，转向jsp页面
        if(episode == null)
        {
            //请求书本页面

            //在Redis中获取书本具体信息
            book.setbookname(jedis.get(String.valueOf(bookid.append("-name"))));
            bookid = new StringBuilder(id);
            //用于计数章节数目
            int index=1;
            content = new ArrayList<Episode>();

            while(jedis.exists(String.valueOf(bookid.append("episodecontent").append(index))))
            {
                bookid = new StringBuilder(id);
                Episode episodeTemp = new Episode();
                episodeTemp.setepisodetitle(jedis.get
                        (String.valueOf(bookid.append("episodetitle").append(index))));
                bookid = new StringBuilder(id);
                episodeTemp.setepisodecontent(jedis.get
                        (String.valueOf(bookid.append("episodecontent").append(index))));
                bookid = new StringBuilder(id);
                content.add(episodeTemp);
                index++;
            }
            book.setbookepisode(content);

            //转向
            request.setAttribute("book", book);
            request.getRequestDispatcher("ROOT/returnBook.jsp?bookid="+bookid).forward(request, response);
        } else
        {
            request.getRequestDispatcher("ROOT/returnEpisode.jsp?bookid="+bookid+"&episode="+episode)
                    .forward(request, response);
        }
    }
}
