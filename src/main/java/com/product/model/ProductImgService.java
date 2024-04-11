package com.product.model;

import entity.ProductImg;

import java.util.List;

public class ProductImgService {

    private ProductImgDAO dao;

    public ProductImgService() {
        dao = new ProductImgDAOJDBCImpl();
    }

    public ProductImg addImg(Integer productNo, byte[] img) {

        ProductImg productImg = new ProductImg();

        productImg.setProductNo(productNo);
        productImg.setProductImg(img);
        dao.insert(productImg);

        return productImg;
    }

    public ProductImg update(Integer productNo, byte[] img, Integer productImgNo) {
        ProductImg productImg = new ProductImg();

        productImg.setProductNo(productNo);
        productImg.setProductImg(img);
        productImg.setProductImgNo(productImgNo);
        dao.update(productImg);

        return productImg;
    }

    public void deleteImg(Integer productImgNo) {
        dao.delete(productImgNo);
    }

    public List<ProductImg> getAll() {
        return dao.getAll();
    }

    public ProductImg getOneProductImg(Integer productImgNo) {
        return dao.findByPK(productImgNo);
    }

    public List<ProductImg> getImgByProduct(Integer productNo) {
        return dao.getImgByProductNo(productNo);
    }







}
