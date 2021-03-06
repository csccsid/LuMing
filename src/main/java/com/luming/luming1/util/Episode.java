package com.luming.luming1.util;

import java.io.Serializable;

//章节的实体类
public class Episode implements Serializable//为了能序列化，一定要实现Serializable接口
{
    //爬该章节的网址
    private String link = null;
    //章节名
    private String title = null;
    //章节内容
    private String content = null;

    public Episode()
    {
    }

    public synchronized void setlink(String link)
    {
        this.link = link;
    }

    public synchronized void setepisodetitle(String title)
    {
        this.title = title;
    }

    public synchronized void setepisodecontent(String content)
    {
        this.content = content;
    }


    public String getlink()
    {
        return link;
    }

    public String getepisodetitle()
    {
        return title;
    }

    public String getepisodecontent()
    {
        return content;
    }
}
