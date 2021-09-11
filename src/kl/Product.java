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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import wagu.Board;
import wagu.Table;


/**
 *
 * @author nic35
 */

public abstract class Product{
   
    protected String productName;
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

    protected String getProductName() {
        return productName;
    }

    protected void setProductName(String productName) {
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
    public void getProductDetailsFromUser() {
        
        //Declare input scanner
        Scanner scan = new Scanner(System.in);
        Scanner scan1 = new Scanner(System.in);
        boolean repeat = true;
        String appendedPrice= "";
        
        //Get and as input and set product name
        System.out.println("Please enter product name");
        String productName = scan.nextLine();
        setProductName(productName);
        
        //Get and as input and set product detail
        System.out.println("Please enter warranty (1 year)");
        String warranty = scan.nextLine();
        setWarranty(warranty);
        
        //Get and as input and set price
        do {
            System.out.println("Please enter price (20.00)");
            String price = scan1.nextLine();
            if (price.matches(".*\\d.*")) {
                repeat = false;
            }
            for (char c : price.toCharArray()) {
            
            if (Character.isDigit(c)) {
                System.out.println("Test");
                 appendedPrice += c;
            }
        }
        } while(repeat);
        setPrice(Double.parseDouble(appendedPrice));
        
        //Get and as input and set Date of Manufacture
        System.out.println("Please enter Date of Manufacture (2021-05-12)");
        String manuDate = scan.nextLine();
        setDateOfManufacture(manuDate);
        
        //Get and as input and set Origin
        System.out.println("Please enter Origin (China)");
        String origin = scan.nextLine();
        setOrigin(origin);
    }
    
    public static String getProductsFromDatabase(Database database) {
        List<List<String>> listOfLists = new ArrayList<>();
        List<String> headersList = Arrays.asList("PRODUCTNAME", "PRICE", "DATEOFMANUFACTURE", "ORIGIN", "WARRANTY", "SUBPRODUCTID");
        try {
            Statement stmt = database.getCon().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Product");
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                List<String> innerList = new ArrayList<>();
                
                innerList.add(rs.getString(1));
                innerList.add(rs.getString(2));
                innerList.add(rs.getString(3));
                innerList.add(rs.getString(4));
                innerList.add(rs.getString(5));   
                innerList.add(rs.getString(6));  
                listOfLists.add(innerList);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        if (!listOfLists.isEmpty()) {
            Board board = new Board(75);
            String tableString1 = board.setInitialBlock(new Table(board, 75, headersList, listOfLists).tableToBlocks()).build().getPreview();
            return tableString1;
        }
        return "";
    }
    
    public static boolean insertAgain() {
        Scanner s1 = new Scanner(System.in);
        System.out.print("Insert again?: (Y/N)");
        String choice = s1.nextLine();
        if (choice.toLowerCase().equals("y")) 
            return true;
        else
            return false;
    }
    
    public void printColumnNames() {
        System.out.println("\nProduct Name");
        System.out.println("Price");
        System.out.println("Date Of Manufacture");
        System.out.println("Origin");
        System.out.println("Warranty");
    }
    
    public abstract void addProduct(Database database);
    
    public abstract <T> void updateProduct(Database database, String columnToUpdate, String newValue);

    public abstract void listProducts(Database database, Product product);
    
    public static String ProductChoice(Database database) {
        Scanner s1 = new Scanner(System.in);
        System.out.println("\n" + Product.getProductsFromDatabase(database)); 
        System.out.println("1. Monitor");
        System.out.println("2. Harddrive");
        System.out.println("3. Motherboard");
        System.out.println("\nWhat type of product to update? (Press '4' to exit)");
        System.out.print("Please enter your choice: ");
        return s1.nextLine();
    }
    
    public static ResultSet productExist(Database database, String productName) {
        String selectSql = "SELECT SubProductId FROM Product WHERE productName='" + productName + "'";
        try {
            Statement stmt = database.getCon().createStatement();
            ResultSet rs = stmt.executeQuery(selectSql);
            return rs;
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static String getSubProductId(Database database, String productName) {
        try{
            ResultSet rs = productExist(database, productName);
            if(rs.next()) {
                return rs.getString(1);    
            }
            
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static boolean checkQuantity(Database database, int quantity, String productName) {
        try {
            Statement stmt = database.getCon().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Quantity FROM Warehouses WHERE NameProduct='" + productName + "'");
            int warehouseQuantity;
            if (rs.next()) {
                warehouseQuantity = rs.getInt(1);
                System.out.println(warehouseQuantity + quantity);
                if (warehouseQuantity < quantity) {
                    return false;
                } else {
                    return true;
                }
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return false;
    }
    
    public static int getQuantity(Database database, String productName) {
        try {
            Statement stmt = database.getCon().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Quantity FROM Warehouses WHERE NameProduct='" + productName + "'");
            int warehouseQuantity;
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return 0;
    }
}