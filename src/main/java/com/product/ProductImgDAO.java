package com.product;

import entity.ProductImg;

import java.util.List;

public interface ProductImgDAO {

    int insert(ProductImg productImg);
    int update(ProductImg productImg);
    int delete(Integer productImgNo);
    ProductImg findByPK(Integer productImgNo);
    List<ProductImg> getAll();

    // 查詢某項產品的所有圖片
    List<ProductImg> getImgByProductNo(Integer productNo);

}
