package com.luming.luming1;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import redis.clients.jedis.Jedis;

import java.io.IOException;

//返回每章具体内容
@WebServlet(name = "content", value = "/bookcontent")
public class contentReturn extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookid = null;
        String episode = null;
        bookid = request.getParameter("bookid");
        episode = request.getParameter("episode");
        Jedis jedis = new Jedis("localhost");

        if(episode == null)
        {
          request.getRequestDispatcher("ROOT/returnBook.jsp?bookid="+bookid).forward(request, response);
        } else
        {
            request.getRequestDispatcher("ROOT/returnEpisode.jsp?bookid="+bookid+"&episode="+episode)
                    .forward(request, response);
        }
    }
}
