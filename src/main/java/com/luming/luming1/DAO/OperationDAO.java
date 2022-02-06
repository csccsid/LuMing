package com.luming.luming1.DAO;

import com.luming.luming1.pool.SqlConpool;
import com.luming.luming1.util.Book;
import com.luming.luming1.util.Episode;

import java.sql.*;
import java.util.ArrayList;


//书籍查询的对数据库操作DAO类
public class OperationDAO implements DAO
{
    private Book book = null;


    //向待收录列表中添加
    @Override
    public void add(Book book, SqlConpool scp)
    {
        Connection conn = null;
        PreparedStatement prestate = null;
        try
        {
            conn = scp.getconnection();
            String sql = "INSERT INTO noincludebook (bookname) VALUES (?)";
            prestate = conn.prepareStatement(sql);
            prestate.setString(1, book.getbookname());
            prestate.execute();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            if(conn != null)
            {
                scp.returnconnection(conn);
                conn = null;
            }
            if(prestate !=null)
            {
                try
                {
                    prestate.close();
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
                prestate = null;
            }
        }
    }

    //查询booklist中有没有这个书
    @Override
    public boolean get(Book book, SqlConpool scp)
    {
        Connection conn = null;
        PreparedStatement prestate = null;
        try
        {
            conn = scp.getconnection();
            String sql = "SELECT * FROM booklist WHERE bookname = ?";
            prestate = conn.prepareStatement(sql);
            prestate.setString(1, book.getbookname());
            ResultSet resultSet = null;
            resultSet = prestate.executeQuery();

            if(resultSet.next())
                return true;
            else
                return false;
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            if(conn != null)
            {
                scp.returnconnection(conn);
                conn = null;
            }
            if(prestate !=null)
            {
                try
                {
                    prestate.close();
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
                prestate = null;
            }
        }
        return false;
    }

    //查询数据库中有没有这个表，有的话返回表有多少条数据，即章节数
    @Override
    public int getTable(Book book, SqlConpool scp)
    {
        Connection conn = null;
        PreparedStatement prestate = null;
        Statement state = null;
        try
        {
            conn = scp.getconnection();
            String sql = "SELECT * FROM information_schema.TABLES WHERE TABLE_NAME = ?";
            prestate = conn.prepareStatement(sql);
            prestate.setString(1, book.getbookname());
            ResultSet resultSet = null;
            resultSet = prestate.executeQuery();

            if(resultSet.next())
            {

                //因为已经验证过了有这个表，所以可以用普通statement和字符串拼接的sql了
                String sql2 = "SELECT count(*) FROM " + book.getbookname();
                state = conn.createStatement();
                resultSet = state.executeQuery(sql2);
                if(resultSet.next())
                    return resultSet.getInt(1);
            }
            else
                return -1;
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            if(conn != null)
            {
                scp.returnconnection(conn);
                conn = null;
            }
            if(prestate !=null)
            {
                try
                {
                    prestate.close();
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
                prestate = null;
            }
            if(state !=null)
            {
                try
                {
                    state.close();
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
                state = null;
            }
        }

        //返回章节数为负数则代表无结果
        return -1;
    }

    //在未收录列表中查找，避免重复添加
    @Override
    public boolean getNotInclude(Book book, SqlConpool scp)
    {
        Connection conn = null;
        PreparedStatement prestate = null;

        try
        {
            conn = scp.getconnection();
            String sql = "SELECT * FROM noincludebook WHERE bookname = ?";
            prestate = conn.prepareStatement(sql);
            prestate.setString(1, book.getbookname());
            ResultSet resultSet = null;
            resultSet = prestate.executeQuery();

            if(resultSet.next())
                return true;
            else
                return false;
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            if(conn != null)
            {
                scp.returnconnection(conn);
                conn = null;
            }
            if(prestate !=null)
            {
                try
                {
                    prestate.close();
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
                prestate = null;
            }
        }
        return false;
    }

    @Override
    public ArrayList<Book> getBook(String bookname, SqlConpool scp)
    {
        Connection conn = null;
        PreparedStatement prestate = null;

        //储存数据库模糊查询的书籍列表内容
        ArrayList<Book> booklist  = new ArrayList<>();

        try
        {
            conn = scp.getconnection();
            String sql = "SELECT * FROM booklist WHERE bookname LIKE ?";
            prestate = conn.prepareStatement(sql);
            prestate.setString(1, bookname);
            ResultSet resultSet = null;
            resultSet = prestate.executeQuery();

            while(resultSet.next())
            {
                Book bookTemp = new Book();
                bookTemp.setId(resultSet.getString("bookid"));
                bookTemp.setbookname(resultSet.getString("bookname"));
                bookTemp.setauthor(resultSet.getString("author"));
                bookTemp.setBookAbstract(resultSet.getString("abstract"));
                bookTemp.setBookEpisodeNumber(resultSet.getInt("episodenumber"));

                booklist.add(bookTemp);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            if(conn != null)
            {
                scp.returnconnection(conn);
                conn = null;
            }
            if(prestate !=null)
            {
                try
                {
                    prestate.close();
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
                prestate = null;
            }
        }

        return booklist;
    }

    //epnumber为章节数，能提前开辟ArrayList空间，节约时间
    @Override
    public Book getbyId(Book book, SqlConpool scp, int epnumber)
    {
        Connection conn = null;
        Statement state = null;
        //储存数据库查询的书籍章节内容
        ArrayList<Episode> content  = new ArrayList<>(epnumber);
        try
        {
            //确定书名合法，所以不用预编译sql
            conn = scp.getconnection();
            String sql = "SELECT * FROM " + book.getbookname();
            state = conn.createStatement();
            ResultSet resultSet = null;
            resultSet = state.executeQuery(sql);

            while(resultSet.next())
            {
                Episode episodetemp = new Episode();
                episodetemp.setepisodetitle(resultSet.getString("title"));
                episodetemp.setepisodecontent(resultSet.getString("content"));
                content.add(episodetemp);
            }
            book.setbookepisode(content);
            this.book=book;
            return this.book;
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            if(conn != null)
            {
                scp.returnconnection(conn);
                conn = null;
            }
            if(state !=null)
            {
                try
                {
                    state.close();
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
                state = null;
            }
        }
        return this.book;
    }

    //查询所有书籍列表
    @Override
    public ArrayList<Book> getAll(SqlConpool scp)
    {
        return null;
    }
}
