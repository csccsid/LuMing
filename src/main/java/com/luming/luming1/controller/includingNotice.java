package com.luming.luming1.controller;

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
        try
        {
            request.getRequestDispatcher("ROOT/includeNotice.jsp?bookname="+bookname).forward(request, response);
        } catch (ServletException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
