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
public class PurchasingClass extends PurchasesIn{
    private String warehouse;
    private String productIteam;
    private static double servicesTax;
    private static double totalPrice;

    public PurchasingClass() {
        this("","","","",0.0,0.0);
    }

    public PurchasingClass( String purchasesID, String vendor,String warehouse, String productIteam, double servicesTax, double totalPrice) {
        super(purchasesID, vendor);
        this.warehouse = warehouse;
        this.productIteam = productIteam;
        this.servicesTax = servicesTax;
        this.totalPrice = totalPrice;
    }

   
    public String getVendor() {
        return warehouse;
    }

    public void setVendor(String vendor) {
        this.warehouse = vendor;
    }

    public String getProductIteam() {
        return productIteam;
    }

    public void setProductIteam(String productList) {
        this.productIteam = productIteam;
    }

    public static double getTotalPrice() {
        return totalPrice;
    }

    public static double getServicesTax() {
        return servicesTax;
    }

    public double calculateTotal(double totalPrice ){
        return totalPrice + servicesTax  ;
        
    }
    public static void addTotalPrice(double prices, double quantity){
        totalPrice += prices * quantity;
        servicesTax = totalPrice*0.06;
        
    }
      public static void insertDelivery(Connection myConObj, String random, String date, String deliveryCompany, String details){
            Scanner s1 = new Scanner(System.in);
     try{
            myConObj = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/computershop", "ngphengloong", "lolhaha123");
            String insertNewUserSQL = "INSERT INTO computershop.Purcese(PurchasingID, DeliveryDate, DeliveryCompany, Details)" + " VALUES (?,?, ?,?)";
            //String updateUserSQL = "UPDATE computershop.Delivery SET DeliveryID = ?" + " WHERE DeliveryID = ?";
            PreparedStatement pstmt = myConObj.prepareStatement(insertNewUserSQL);
            pstmt.setString(1, random);
            pstmt.setString(2, date);
            pstmt.setString(3, deliveryCompany);
            pstmt.setString(4, details);
           
            int optional = 0;
            System.out.println("1. Yes");
            System.out.println("2. No");
            System.out.println("Please comfirm the information is correct");
            optional = s1.nextInt();
                }catch (SQLException e) {
            e.printStackTrace();
            
        }          
}
}
    

