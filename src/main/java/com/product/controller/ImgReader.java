package com.product.controller;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.*;

@WebServlet("/ImgReader")

public class ImgReader extends HttpServlet {

    Connection con;

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("image/gif");
        ServletOutputStream sout = res.getOutputStream();

        try {
            Statement stmt = con.createStatement();
            String productImgNo = req.getParameter("productImgNo").trim();

            ResultSet rs = stmt.executeQuery(
                    "SELECT product_img FROM product_img WHERE product_img_no =" + productImgNo);

            if (rs.next()) {
                BufferedInputStream bis = new BufferedInputStream(rs.getBinaryStream("product_img"));
                byte[] b = new byte[4 * 1024]; // 4K buffer
                int len;
                while ((len = bis.read(b)) != -1) {
                    sout.write(b, 0, len);
                }
                bis.close();
            } else {
                //res.sendError(HttpServletResponse.SC_NOT_FOUND);
                InputStream is = getServletContext().getResourceAsStream("/NoData/none2.jpg");
                byte[] b = new byte[is.available()];
                is.read(b);
                sout.write(b);
                is.close();
            }

            rs.close();
            stmt.close();

        } catch (Exception e) {
            InputStream in = getServletContext().getResourceAsStream("/NoData/null.jpg");
            byte[] b = new byte[in.available()];
            in.read(b);
            sout.write(b);
            in.close();
        }
    }

    public void init() throws ServletException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/eztest?serverTimezone=Asia/Taipei", "root", "a123456");
        } catch (ClassNotFoundException e) {
            throw new UnavailableException("Couldn't load JdbcOdbcDriver");
        } catch (SQLException e) {
            throw new UnavailableException("Couldn't get db connection");
        }
    }

    public void destroy() {
        try {
            if (con != null) con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}