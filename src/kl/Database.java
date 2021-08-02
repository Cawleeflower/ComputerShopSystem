/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Ng Pheng Loong
 */
public class Database {
    
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
            while(myResObj.next()) {
                String productId = myResObj.getString("ProductId");
                String productName = myResObj.getString("ProductName");
                System.out.println(productId + productName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }   
    }
}
