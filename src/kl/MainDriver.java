/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
/**
 *
 * @author Ng Pheng Loong
 */
public class MainDriver {
    public static void main(String[] args) {
        Connection myConObj = null;
        Statement mystatObj = null;
        ResultSet myResObj = null;
        String query = "Select * from ngphengloong.product";
        String url = "";
        
        
        try {
            myConObj = DriverManager.getConnection("jdbc:derby://localhost:1527/test", "ngphengloong", "123");
            mystatObj = myConObj.createStatement();
            myResObj = mystatObj.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }   
        
        Scanner scan = new Scanner(System.in);
        Scanner scan1 = new Scanner(System.in);
        
        System.out.println("Enter product name");
        String name = scan.nextLine();
        
        System.out.println("Enter product price");
        double price = scan1.nextDouble();
        
        System.out.println("Enter product details");
        String details = scan.nextLine();
        
        System.out.println("Enter product manufacture date");
        String dateOfManufacture = scan.nextLine();
        
        System.out.println("Enter product origin");
        String origin = scan.nextLine();
        
        String insertQuery = "insert into ngphengloong.product values (" + name + "," + price + "," + details + "," + dateOfManufacture + "," + origin + ")";
        try {
            
            myConObj = DriverManager.getConnection("jdbc:derby://localhost:1527/test", "ngphengloong", "123");
            String insertNewUserSQL = "INSERT INTO ngphengloong.product (productid, productname, productdetail, price, dateOfManufacture, origin)" + " VALUES (?,?, ?, ?, ?, ?)";
            PreparedStatement pstmt = myConObj.prepareStatement(insertNewUserSQL);
            pstmt.setString(1, "P002");
            pstmt.setString(2, name);
            // ... repeat this step until the last parameter ....
            pstmt.setDouble(3, price);
            pstmt.setString(4, details);
            pstmt.setString(5, dateOfManufacture);
            pstmt.setString(6, origin);
            pstmt.executeUpdate();  
        } catch (SQLException e) {
            e.printStackTrace();
        }        
    }
}
