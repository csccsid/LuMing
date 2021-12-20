package com.luming.luming1.util;

import java.util.ArrayList;

public class Book
{
    //书籍唯一标识id
    private int bookid;
    //书名
    private String bookname;
    //爬该书籍的地址
    private String link;
    //书籍作者
    private String author;
    //书籍简介
    private String bookabstract;
    //总章节数
    private int episodenumber;
    //书籍的内容，按章节分
    private ArrayList<Episode> bookepisode = null;



    public void setid(int bookid)
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

    public void setBookabstract(String bookabstract)
    {
        this.bookabstract = bookabstract;
    }

    public void setbookepisode(ArrayList<Episode> bookepisode)
    {
        this.bookepisode = bookepisode;
        episodenumber = bookepisode.size();
    }

    public int getbookid()
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

    public String getBookabstract()
    {
        return bookabstract;
    }

    public String getauthor()
    {
        return author;
    }

    public int getEpisodenumber()
    {
        return episodenumber;
    }

    public ArrayList<Episode> getbookepisode()
    {
        return bookepisode;
    }

}

