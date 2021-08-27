/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kl;

import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.lang.*;


/**
 *
 * @author nic35
 */

public class Product{
   
    private String productName;
    private String warranty;
    private double price;
    private String dateOfManufacture;
    private String origin;

    public Product() {
        
        this("","",0.0,"","");
    }

    public Product(String productName, String warranty, double price, String dateOfManufacture, String origin) {
        this.productName = productName;
        this.warranty = warranty;
        this.price = price;
        this.dateOfManufacture = dateOfManufacture;
        this.origin = origin;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
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
    
    //Get product details from users
    public void getProductDetailsFromUser(Product product) {
        
        //Declare input scanner
        Scanner scan = new Scanner(System.in);
        Scanner scan1 = new Scanner(System.in);
        
        //Get and as input and set product name
        System.out.println("Please enter product name");
        String productName = scan.nextLine();
        product.setProductName(productName);
        
        //Get and as input and set product detail
        System.out.println("Please enter warranty (1 year)");
        String warranty = scan.nextLine();
        product.setWarranty(warranty);
        
        //Get and as input and set price
        System.out.println("Please enter price (20.00)");
        double price = scan1.nextDouble();
        product.setPrice(price);
        
        //Get and as input and set Date of Manufacture
        System.out.println("Please enter Date of Manufacture (2021-05-12)");
        String manuDate = scan.nextLine();
        product.setDateOfManufacture(manuDate);
        
        //Get and as input and set Origin
        System.out.println("Please enter Origin (China)");
        String origin = scan.nextLine();
        product.setOrigin(origin);
    }
    
    
    public static void addProduct(Connection con, Product product) {
        String insertNewSql = "INSERT INTO Product (ProductName, Price, DateOfManufacture, Origin, Warranty)" + " VALUES (?,?,?,?,?)";
         try {
         PreparedStatement preparedStatement = con.prepareStatement(insertNewSql);   
         preparedStatement.setString(1, product.getProductName());
         preparedStatement.setDouble(2, product.getPrice());
         preparedStatement.setString(3, product.getDateOfManufacture());
         preparedStatement.setString(4, product.getOrigin());
         preparedStatement.setString(5, product.getWarranty());
         preparedStatement.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public static <T> void updateProduct(Connection con, String columnToUpdate, T newValue, String productName) {
        String insertNewSql = "Update Product set " + columnToUpdate + "=" + newValue + "where " + "productName=" + productName;
         try {
         PreparedStatement preparedStatement = con.prepareStatement(insertNewSql);
         preparedStatement.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void listProducts(Connection con) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Product");
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int count = rsMetaData.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= count; i++) {
                    
                    String columnValue = rs.getString(i);
                    
                    if (rsMetaData.getColumnName(i).equals("ProductName")) {
                     System.out.print(columnValue);   
                    } 
                }
            System.out.println("");
        }
      } catch(Exception ex) {
          System.out.println(ex);
      }
    }
}