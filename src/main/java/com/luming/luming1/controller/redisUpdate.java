package com.luming.luming1.controller;

import com.luming.luming1.DAO.OperationDAO;
import com.luming.luming1.pool.SqlConpool;
import com.luming.luming1.util.Book;
import com.luming.luming1.util.Episode;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;

//当redis缓存中数据过期时，从数据库更新信息
public class redisUpdate
{
    public boolean update(Jedis jedis, SqlConpool scp, String id)
    {
        Book book = new Book();
        ArrayList<Episode> content = null;
        //用stringbuilder，因为之后频繁拼接字符串，节约资源
        StringBuilder bookid = new StringBuilder(id);
        OperationDAO operation = new OperationDAO();
        book.setId(id);

        //获取该书章节数
        int epnumber = operation.getTable(book, scp);
        System.out.println("number: "+epnumber);
        if(epnumber >= 0)
        {
            //数据库中查询书本具体内容
            book.setBookEpisodeNumber(epnumber);
            book = operation.getbyId(book, scp);
            content = book.getbookepisode();

            //在redis中存入该书
            //整个对象存入jedis序列化和反序列化太耗资源类，所以数据分别存入。
            //在redis中存入章节数
            jedis.set(String.valueOf(bookid.append("-number")), String.valueOf(epnumber));
            bookid = new StringBuilder(id);
            //在redis中存入书的各个内容
            jedis.set(String.valueOf(bookid.append("-name")), book.getbookname(),
                    "nx", "ex", 3000);
            bookid = new StringBuilder(id);
            for(int i = 1; i <= book.getbookepisode().size(); i++)
            {
                jedis.set(String.valueOf(bookid.append("episodetitle").append(i)),
                        content.get(i-1).getepisodetitle(), "nx", "ex", 3000);
                bookid = new StringBuilder(id);
                jedis.set(String.valueOf(bookid.append("episodecontent").append(i)),
                        content.get(i-1).getepisodecontent(), "nx", "ex", 3000);
                bookid = new StringBuilder(id);
            }
            jedis.set(String.valueOf(bookid), "true", "nx", "ex", 3000);
            System.out.println(bookid+"已存入Redis");
        } else
            return false;

        return true;
    }
}
