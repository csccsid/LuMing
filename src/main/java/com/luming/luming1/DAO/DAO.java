package com.luming.luming1.DAO;

import com.luming.luming1.pool.SqlConpool;
import com.luming.luming1.util.Book;
import com.luming.luming1.util.Episode;

import java.util.ArrayList;

public interface DAO
{
    //增加
    public void add(Book book, SqlConpool scp);

    //查询list
    public boolean get(Book book, SqlConpool scp);

    //查询table，选择int来判断是为了能多提供信息，即有book多少章节，从而初始化ArrayList的时候分配空间
    //减少时间花销。当返回小于0时则为不存在。
    public int gettable(Book book, SqlConpool scp);

    //查询noincludelist，避免重复添加
    public boolean getnoinclude(Book book, SqlConpool scp);

    //分页查询
    public Book getcontent(Book book, SqlConpool scp, int epnumber);

    //分页查询所有书籍
    public ArrayList<Book> getall(SqlConpool scp);
}
