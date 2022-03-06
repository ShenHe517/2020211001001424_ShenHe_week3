package com.ShenHe.controller;

import com.ShenHe.dao.ProductDao;
import com.ShenHe.model.Product;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/getImg")
public class GetImgServlet extends HttpServlet {
Connection con=null;
public void init() throws ServletException
{
    con= (Connection) getServletContext().getAttribute("con");
}
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    response.setContentType("text/html");
        ProductDao dao=new ProductDao();
        int id=0;
        if(request.getParameter("id")!=null)
        {
            id=Integer.parseInt(request.getParameter("id"));
        }
        try
        {
            byte[] imgByte= new byte[0];
            imgByte=dao.getPictureById(id,con);
            if(imgByte!=null)
            {
                response.setContentType("image/gif");
                OutputStream os= response.getOutputStream();
                os.write(imgByte);
                os.flush();
            }
        }catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
