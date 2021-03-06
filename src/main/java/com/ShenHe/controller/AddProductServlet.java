package com.ShenHe.controller;

import com.ShenHe.dao.ProductDao;
import com.ShenHe.model.Category;
import com.ShenHe.model.Product;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name="AddProductServlet",value="/admin/addProduct")
public class AddProductServlet extends HttpServlet {
    Connection con = null;

    public void init() {
        con = (Connection) getServletContext().getAttribute("con");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categoryList = Category.findAllCategory(con);
        request.setAttribute("categoryList", categoryList);
        String path = "/WEB-INF/views/admin/addProduct.jsp";
        request.getRequestDispatcher(path).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productName = request.getParameter("productName");
        Double price = request.getParameter("price") != null ? Double.parseDouble(request.getParameter("price")) : 0.0;
        int categoryId = request.getParameter("categoryId") != null ? Integer.parseInt(request.getParameter("categoryId")) : 0;
        String productDescription = request.getParameter("productDescription");
        InputStream inputStream = null;
        Part filePart = request.getPart("picture");
        if (filePart != null) {
            System.out.println("file name:" + filePart.getName() + "size" + filePart.getSize() + "file type" + filePart.getContentType());
            inputStream = filePart.getInputStream();
        }
        Product product = new Product();
        product.setProductName(productName);
        product.setPrice(price);
        product.setProductDescription(productDescription);
        product.setCategoryId(categoryId);


        ProductDao dao = new ProductDao();
        int i = 0;
        i = dao.save(product, inputStream, con);


    }
}

