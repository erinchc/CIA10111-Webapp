package entity;

import javax.persistence.*;

@Entity
@Table(name = "product_img")
public class ProductImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_img_no", updatable = false)
    private Integer productImgNo;

    @Column(name = "product_no")
    private Integer productNo;

    @Column(name = "product_img", columnDefinition = "longblob")
    private byte[] productImg;

    public ProductImg() {
    }

    public Integer getProductImgNo() {
        return productImgNo;
    }

    public void setProductImgNo(Integer productImgNo) {
        this.productImgNo = productImgNo;
    }

    public Integer getProductNo() {
        return productNo;
    }

    public void setProductNo(Integer productNo) {
        this.productNo = productNo;
    }

    public byte[] getProductImg() {
        return productImg;
    }

    public void setProductImg(byte[] productImg) {
        this.productImg = productImg;
    }
}
