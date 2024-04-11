package com.product.controller;

import com.product.model.ProductImgService;
import entity.ProductImg;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/product.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ProductServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html; charset=UTF-8");
        String action = req.getParameter("action");

// 查詢
        if ("getOne_For_Display".equals(action)) {

            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);

            // 接收請求參數，輸入格式的錯誤處理
            String str = req.getParameter("productImgNo");
            if (str == null || (str.trim()).isEmpty()) {
                errorMsgs.add("請輸入圖片編號");
            }

            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/product/select_page.jsp");
                failureView.forward(req, res);
                return;
            }

            Integer productImgNo = null;
            try {
                productImgNo = Integer.valueOf(str);
            } catch (Exception e) {
                errorMsgs.add("圖片編號格式不正確");
            }

            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/product/select_page.jsp");
                failureView.forward(req, res);
                return;
            }

            // 開始查詢資料
            ProductImgService imgSvc = new ProductImgService();
            ProductImg productImg = imgSvc.getOneProductImg(productImgNo);

            if (productImg == null) {
                errorMsgs.add("查無資料");
            }

            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/product/select_page.jsp");
                failureView.forward(req, res);
                return;
            }

            // 查詢完成後轉交前台
            req.setAttribute("productImg", productImg);
            String url = "/product/listOneProductImg.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }

// 修改
        if ("getOne_For_Update".equals(action)) {

            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);

            // 接收請求參數
            Integer productImgNo = Integer.valueOf(req.getParameter("productImgNo"));

            //查詢資料
            ProductImgService imgSvc = new ProductImgService();
            ProductImg productImg = imgSvc.getOneProductImg(productImgNo);

            // 查詢完成後轉交前台
            req.setAttribute("productImg", productImg);
            String url = "/product/updateProductImg.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }

        if ("update".equals(action)) {

            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);

            // 接收請求參數
            Integer productImgNo = Integer.valueOf(req.getParameter("productImgNo").trim());
            Integer productNo = Integer.valueOf(req.getParameter("productNo").trim());

            //圖片新增
            byte[] img = null;
            Part part = req.getPart("productImg");
            if (part != null) {
                try (InputStream is = part.getInputStream()) {
                    img = new byte[is.available()];
                    is.read(img);
                } catch (IOException e) {
                    errorMsgs.add("讀取圖片失敗");
                }
            }

            ProductImg productImg = new ProductImg();
            productImg.setProductImgNo(productImgNo);
            productImg.setProductNo(productNo);
            productImg.setProductImg(img);

            if (!errorMsgs.isEmpty()) {
                req.setAttribute("productImg", productImg);
                String failUrl = "/product/updateProductImg.jsp";
                RequestDispatcher fail = req.getRequestDispatcher(failUrl);
                fail.forward(req, res);
                return;
            }

            // 輸入正確，做資料修改
            ProductImgService imgSvc = new ProductImgService();
            productImg = imgSvc.update(productNo, img, productImgNo);

            // 修改完成，轉交前台
            String url = "/product/listAllProductImg.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }

// 新增
        if ("insert".equals(action)) {

            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);

            // 接收請求參數，輸入格式的錯誤處理
            Integer productNo = null;
            try {
                productNo = Integer.valueOf(req.getParameter("productNo").trim());
            } catch (NumberFormatException e) {
                errorMsgs.add("產品編號請填數字.");
            }

            byte[] img = null;
            Part part = req.getPart("productImg");
            if (part != null) {
                try (InputStream is = part.getInputStream()) {
                    img = new byte[is.available()];
                    is.read(img);
                } catch (IOException e) {
                    errorMsgs.add("讀取圖片失敗");
                }
            }

            ProductImg productImg = new ProductImg();
            productImg.setProductNo(productNo);
            productImg.setProductImg(img);

            if (!errorMsgs.isEmpty()) {
                req.setAttribute("productImg", productImg);
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/product/addProductImg.jsp");
                failureView.forward(req, res);
                return;
            }

            // 開始新增資料
            ProductImgService imgSvc = new ProductImgService();
            productImg = imgSvc.addImg(productNo, img);

            // 新增完成,準備轉交
            String url = "/product/listAllProductImg.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }

// 刪除
        if ("delete".equals(action)) {

            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);

            // 接收請求參數
            Integer productImgNo = Integer.valueOf(req.getParameter("productImgNo"));

            // 開始刪除資料
            ProductImgService imgSvc = new ProductImgService();
            imgSvc.deleteImg(productImgNo);

            // 刪除完成,準備轉交
            String url = "/product/listAllProductImg.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }
    }
}



