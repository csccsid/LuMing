package com.luming.luming1.controller;

import com.luming.luming1.DAOModel.OperationDAO;
import com.luming.luming1.pool.SqlConpool;
import com.luming.luming1.util.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "search", value = "/searchresult")
public class SearchResult extends HttpServlet
{
    //创建后端返回书籍实体类
    Book book = new Book();
    String bookname;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        bookname = request.getParameter("bookname");
        System.out.println(bookname);
        book.setbookname(bookname);

        //获取全局数据库连接池
        SqlConpool scp;
        scp = (SqlConpool) getServletContext().getAttribute("dataconpool");

        //查询书
        OperationDAO operation = new OperationDAO();
        ArrayList<Book> bookLiet = new ArrayList<Book>();
        bookLiet = operation.getBookList(bookname, scp);
        if(bookLiet != null)
        {
            try
            {
                request.setAttribute("bookList", bookLiet);
                request.setAttribute("searchname", bookname);
                request.getRequestDispatcher("ROOT/view/returnBookList.jsp").forward(request, response);
            } catch (ServletException e)
            {
                e.printStackTrace();
            }
        }



        /*
        //获取书章节数
        int epnumber = operation.getTable(book, scp);
        //判断数据库中是否有该书
        if(operation.get(book, scp) && epnumber >= 0)
        {
            book = operation.getbyId(book, scp, epnumber);
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter pw = response.getWriter();

            try
            {
                request.setAttribute("book", book);
                request.getRequestDispatcher("ROOT/returnBookList.jsp").forward(request, response);
            } catch (ServletException e)
            {
                e.printStackTrace();
            }

            /*
            //后端返回小说每章
            Episode epresp = book.getbookepisode().get(0);
            pw.println( "<div style='text-align:center;" +
                    "font-family: Times New Roman, Times, serif;font-size: 40px'>"
                    +epresp.getepisodetitle()+
                    "</div> </br><div style='text-align:center;" +
                    "        font-family: Times New Roman, Times, serif;font-size: 25px'>"
                    +epresp.getepisodecontent()+"</div>");

        }
        else
        {
            //如果待收录列表里有，就避免重复添加
            if(operation.getNotInclude(book, scp) == false)
                operation.add(book, scp);
            PrintWriter pw = response.getWriter();
            pw.println("<div style='color:red'>nothing yet</div>");
        }
        */


    }

}
