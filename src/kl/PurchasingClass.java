/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class PurchasingClass{
    private String purchasesID;
    private String warehouse;
    private String purchasesDate;
    private String productList;
    private static double servicesTax;
    private static double totalPrice;
    private double price;
    private int quantity;
    private String paymentMethod;
    private String paymentNumber;
    private String creditCard;
    private String creditType;
    

    public PurchasingClass() {
        this("","","","",0.0,0.0,0.0,0,"","","","");
    }

    public PurchasingClass( String purchasesID,String warehouse, String productList, String purchasesDate, double servicesTax, double totalPrice, double price, int quantity, String paymentMethod, String paymentNumber, String creditCard ,String  creditType ) {
        this.purchasesID = purchasesID;
        this.warehouse = warehouse;
        this.productList = productList;
        this.servicesTax = servicesTax;
        this.totalPrice = totalPrice;
        this.purchasesDate = purchasesDate;
        this.price = price;
        this.quantity = quantity;
        this.paymentMethod = paymentMethod;
        this.paymentNumber = paymentNumber;
        this.creditCard = creditCard;
        this.creditType = creditType;
    }

   
    public String getVendor() {
        return warehouse;
    }

    public void setVendor(String vendor) {
        this.warehouse = vendor;
    }

    public String getProductList() {
        return productList;
    }

    public void setProductList(String productList) {
        this.productList = productList;
    }

    public static double getTotalPrice() {
        return totalPrice;
    }

    public static double getServicesTax() {
        return servicesTax;
    }

    public String getPurchasesID() {
        return purchasesID;
    }

    public void setPurchasesID(String purchasesID) {
        this.purchasesID = purchasesID;
    }

    public String getPurchasesDate() {
        return purchasesDate;
    }

    public void setPurchasesDate(String purchasesDate) {
        this.purchasesDate = purchasesDate;
    }

    public double calculateTotal(double totalPrice ){
        return totalPrice + servicesTax  ;
        
    }
    public static void addTotalPrice(double prices, double quantity){
        totalPrice += prices * quantity;
        servicesTax = totalPrice*0.06;
        
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public static void setTotalPrice(double totalPrice) {
        PurchasingClass.totalPrice = totalPrice;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(String paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getCreditType() {
        return creditType;
    }

    public void setCreditType(String creditType) {
        this.creditType = creditType;
    }
    
    
      public static void insertPurchases(Connection myConObj,String purchasesID, String vendor, String date, String productList, double totalPrice, double price,int quantity, String method){
            Scanner s1 = new Scanner(System.in);
     try{
            myConObj = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/computershop", "ngphengloong", "lolhaha123");
            String insertNewUserSQL = "INSERT INTO computershop.Purchase(PurchaseID, Vendor, PurchaseDate, ProductList, TotalPrices, Price, Quantity, PaymentMethod )" + " VALUES (?,?,?,?,?,?,?,?)";
            //String updateUserSQL = "UPDATE computershop.Delivery SET DeliveryID = ?" + " WHERE DeliveryID = ?";
            PreparedStatement pstmt = myConObj.prepareStatement(insertNewUserSQL);
            pstmt.setString(1, purchasesID);
            pstmt.setString(2, vendor);
            pstmt.setString(3, date);
            pstmt.setString(4, productList);
            pstmt.setDouble(5, totalPrice);
            pstmt.setDouble(6, price);
            pstmt.setInt(7, quantity);
            pstmt.setString(8, method);
            pstmt.executeUpdate();
           }catch (SQLException e) {
            e.printStackTrace();
            
        }          
}

}
    

