package com.luming.luming1;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

//返回每章具体内容
@WebServlet(name = "content", value = "/123")
public class contentReturn extends HttpServlet
{
}
