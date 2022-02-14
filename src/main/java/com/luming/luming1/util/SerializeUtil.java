package com.luming.luming1.util;

import java.io.*;

//序列化与反序列化对象的工具类
public class SerializeUtil
{
    public static void SerializeUtil()
    {

    }
    //序列化
    public static byte[] serialize(Object object)
    {
        ObjectOutputStream oos = null;
        //设置序列化缓冲区
        ByteArrayOutputStream baos = null;
        try
        {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            close(oos);
            close(baos);
        }
        return null;
    }

    //反序列化
    public static Object unserialize( byte[] bytes)
    {
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try
        {
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            close(bais);
            close(ois);
        }
        return null;
    }

    //关闭io流方法
    public static void close(Closeable closeable)
    {
        if (closeable != null)
        {
            try
            {
                closeable.close();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
