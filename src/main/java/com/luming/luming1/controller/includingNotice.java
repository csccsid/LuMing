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

@WebServlet(name = "including", value = "/including")
public class includingNotice extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        String bookname = request.getParameter("bookname");
        Book book = new Book();
        //设置待收录书籍书名
        book.setbookname(bookname);

        //获取全局数据库连接池
        SqlConpool scp;
        scp = (SqlConpool) getServletContext().getAttribute("dataconpool");

        OperationDAO operation = new OperationDAO();
        //如果待收录列表中已有，就不重复添加
        if(!operation.getNotInclude(book,scp))
            operation.add(book, scp);
        try
        {
            request.getRequestDispatcher("ROOT/view/includeNotice.jsp?bookname="+bookname).forward(request, response);
        } catch (ServletException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
