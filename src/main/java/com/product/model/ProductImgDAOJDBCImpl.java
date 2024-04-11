package com.product.model;

import entity.ProductImg;
import util.JDBCUtil;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductImgDAOJDBCImpl implements ProductImgDAO {

    private static final String INSERT_STMT = "INSERT INTO product_img(product_no, product_img) VALUES (?, ?)";
    private static final String UPDATE_STMT = "UPDATE product_img SET product_no = ?, product_img = ? WHERE product_img_no = ?";
    private static final String DELETE_STMT = "DELETE FROM product_img WHERE product_img_no = ?";
    private static final String FIND_BY_PK = "SELECT * FROM product_img WHERE product_img_no = ?";
    private static final String GET_ALL = "SELECT * FROM product_img ORDER BY product_no, product_img_no";
    private static final String GET_IMG_BY_PRODUCT_NO = "SELECT product_img_no, product_img FROM product_img WHERE product_no = ?";

    static {
        try {
            Class.forName(JDBCUtil.DRIVER);
        } catch (ClassNotFoundException ce) {
            ce.printStackTrace();
        }
    }

    @Override
    public int insert(ProductImg productImg) {

        try (Connection connection = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USER, JDBCUtil.PASSWORD);
             PreparedStatement ps = connection.prepareStatement(INSERT_STMT)) {
            ps.setInt(1, productImg.getProductNo());
            ps.setBytes(2, productImg.getProductImg());
            return ps.executeUpdate();

        } catch (SQLException se) {
            se.printStackTrace();
        }
        return -1;
    }

    @Override
    public int update(ProductImg productImg) {

        try (Connection connection = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USER, JDBCUtil.PASSWORD);
             PreparedStatement ps = connection.prepareStatement(UPDATE_STMT)) {
            ps.setInt(1, productImg.getProductNo());
            ps.setBytes(2, productImg.getProductImg());
            ps.setInt(3, productImg.getProductImgNo());
            return ps.executeUpdate();

        } catch (SQLException se) {
            se.printStackTrace();
        }
        return -1;
    }

    @Override
    public int delete(Integer productImgNo) {

        try (Connection connection = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USER, JDBCUtil.PASSWORD);
             PreparedStatement ps = connection.prepareStatement(DELETE_STMT)) {
            ps.setInt(1, productImgNo);
            return ps.executeUpdate();

        } catch (SQLException se) {
            se.printStackTrace();
        }
        return -1;
    }

    @Override
    public ProductImg findByPK(Integer productImgNo) {

        ProductImg productImg = null;

        try (Connection connection = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USER, JDBCUtil.PASSWORD);
             PreparedStatement ps = connection.prepareStatement(FIND_BY_PK)) {
            ps.setInt(1, productImgNo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                productImg = new ProductImg();
                productImg.setProductImgNo(rs.getInt("product_img_no"));
                productImg.setProductNo(rs.getInt("product_no"));
                try (InputStream in = rs.getBinaryStream("product_img")) {
                    if (in != null) {
                        byte[] imgBytes = in.readAllBytes();
                        productImg.setProductImg(imgBytes);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return productImg;
    }

    @Override
    public List<ProductImg> getAll() {

        List<ProductImg> productImgList = new ArrayList<>();
        ProductImg productImg = null;

        try (Connection connection = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USER, JDBCUtil.PASSWORD);
             PreparedStatement ps = connection.prepareStatement(GET_ALL)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                productImg = new ProductImg();
                productImg.setProductImgNo(rs.getInt("product_img_no"));
                productImg.setProductNo(rs.getInt("product_no"));
                try (InputStream in = rs.getBinaryStream("product_img")) {
                    if (in != null) {
                        byte[] imgBytes = in.readAllBytes();
                        productImg.setProductImg(imgBytes);
                    }
                }
                productImgList.add(productImg);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return productImgList;
    }

    @Override
    public List<ProductImg> getImgByProductNo(Integer productNo) {

        List<ProductImg> productImgList = new ArrayList<>();
        ProductImg productImg = null;

        try (Connection connection = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USER, JDBCUtil.PASSWORD);
             PreparedStatement ps = connection.prepareStatement(GET_IMG_BY_PRODUCT_NO)) {
            ps.setInt(1, productNo);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                productImg = new ProductImg();
                productImg.setProductImgNo(rs.getInt("product_img_no"));
                try (InputStream in = rs.getBinaryStream("product_img")) {
                    if (in != null) {
                        byte[] imgBytes = in.readAllBytes();
                        productImg.setProductImg(imgBytes);
                    }
                }
                productImgList.add(productImg);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return productImgList;
    }
}
