/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kl;

/**
 *
 * @author nic35
 */
public class Product {
    private String productId;
    private String productName;
    private String productDetail;
    private double price;
    private String dateOfManufacture;
    private String origin;

    public Product() {
    }

    public Product(String productId, String productName, String productDetail, double price, String dateOfManufacture, String origin) {
        this.productId = productId;
        this.productName = productName;
        this.productDetail = productDetail;
        this.price = price;
        this.dateOfManufacture = dateOfManufacture;
        this.origin = origin;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDateOfManufacture() {
        return dateOfManufacture;
    }

    public void setDateOfManufacture(String dateOfManufacture) {
        this.dateOfManufacture = dateOfManufacture;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
    
    public void addProduct() {
        
    }
}
