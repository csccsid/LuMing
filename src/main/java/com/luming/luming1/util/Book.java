package com.luming.luming1.util;

import java.util.ArrayList;

public class Book
{
    //书名
    private String bookname;
    //爬该书籍的地址
    private String link;
    //书籍作者
    private String author;
    //总章节数
    private int episodenumber;
    //书籍的内容，按章节分
    private ArrayList<Episode> bookepisode = null;



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

    public void setbookepisode(ArrayList<Episode> bookepisode)
    {
        this.bookepisode = bookepisode;
        episodenumber = bookepisode.size();
    }

    public String getbookname()
    {
        return bookname;
    }

    public String getlink()
    {
        return link;
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

