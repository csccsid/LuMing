package com.luming.luming1.util;

import java.io.Serializable;
import java.util.ArrayList;

public class Book implements Serializable//为了能序列化，一定要实现Serializable接口
{
    //书籍唯一标识id
    private String bookid;
    //书名
    private String bookname;
    //爬该书籍的地址
    private String link;
    //书籍作者
    private String author;
    //书籍简介
    private String BookAbstract;
    //总章节数
    private int EpisodeNumber;
    //书籍的内容，按章节分
    private ArrayList<Episode> bookepisode = null;



    public void setId(String bookid)
    {
        this.bookid = bookid;
    }

    public void setbookname(String bookname)
    {
        this.bookname = bookname;
    }

    public void setlink(String link)
    {
        this.link = link;
    }

    public void setauthor(String author)
    {
        this.author = author;
    }

    public void setBookAbstract(String BookAbstract)
    {
        this.BookAbstract = BookAbstract;
    }

    public void setbookepisode(ArrayList<Episode> bookepisode)
    {
        this.bookepisode = bookepisode;
        EpisodeNumber = bookepisode.size();
    }

    public void setBookEpisodeNumber(int EpisodeNumber)
    {
        this.EpisodeNumber = EpisodeNumber;
    }




    public String getbookid()
    {
        return bookid;
    }

    public String getbookname()
    {
        return bookname;
    }

    public String getlink()
    {
        return link;
    }

    public String getBookAbstract()
    {
        return BookAbstract;
    }

    public String getauthor()
    {
        return author;
    }

    public int getEpisodenumber()
    {
        return EpisodeNumber;
    }

    public ArrayList<Episode> getbookepisode()
    {
        return bookepisode;
    }

}

