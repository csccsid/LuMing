package com.luming.luming1;

import com.luming.luming1.pool.SqlConpool;
import com.mysql.jdbc.AbandonedConnectionCleanupThread;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.JedisPool;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

public class initprepare implements ServletContextListener
{
    //监听tomcat初始化，执行该方法
    public void contextInitialized(ServletContextEvent servletContextEvent)
    {
        ServletContext sct = servletContextEvent.getServletContext();

        //创建全局数据库连接池，20个连接
        SqlConpool sqp = new SqlConpool(20);
        sct.setAttribute("dataconpool", sqp);
        System.out.println("初始化全局数据库连接池");

        //创建全局redis连接池
        //配置连接池
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxIdle(20);
        poolConfig.setMaxTotal(20);
        poolConfig.setMaxWaitMillis(3000);
        //启动连接池
        JedisPool jpool = new JedisPool(poolConfig,"localhost");
        sct.setAttribute("jedispool", jpool);
        System.out.println("初始化全局redis连接池");
    }

    //在整个web应用销毁之前调用
    public void contextDestroyed(ServletContextEvent servletContextEvent)
    {
        ServletContext servletContext = servletContextEvent.getServletContext();
        //将所有应用空间所设置的内容清空
        ((SqlConpool) servletContext.getAttribute("dataconpool")).closeall();

        //注销JDBC程序驱动
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        Driver driver = null;
        while(drivers.hasMoreElements())
        {
            try
            {
                driver = drivers.nextElement();
                DriverManager.deregisterDriver(driver);

            } catch (SQLException ex)
            {
                // deregistration failed, might want to do something, log at the very least
            }
        }
        //结束JDBC用的线程
        try
        {
            AbandonedConnectionCleanupThread.shutdown();
        } catch (InterruptedException e)
        {
            // again failure, not much you can do
        }

        servletContext.removeAttribute("dataconpool");

        System.out.println("销毁工作完成...");
    }
}
