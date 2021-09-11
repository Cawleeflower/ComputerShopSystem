/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kl;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import wagu.Block;
import wagu.Board;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import wagu.Table;

/**
 *
 * @author Ng Pheng Loong
 */

public class MainDriver {
    public static void main(String[] args) {
        
        
        Database database = new Database();
        boolean isRunning = true;
        boolean repeatProcess = true;
        Cart cart = new Cart();
        Product[] products = new Product[10];
        Scanner s3 = new Scanner(System.in);
        cart.setCartId("b@gmail.com");
        
        while (isRunning) {
            System.out.println("\n" + cart.showCartTable(database.getCon()));
            System.out.println("1. Monitor");
            System.out.println("2. Motherboard");
            System.out.println("3. Harddrive");
            System.out.println("What hardware do you want to purchase? ");
            System.out.print("Your choices: ");
            String choice = s3.nextLine();
            if (choice.equals("1")) {
                products[0] = new Monitor();
                do {
                    String quantity;
                    String productChoice;
                    System.out.println("\n" + ((Monitor) products[0]).getProductsFromDatabase(database));
                    System.out.println("Enter the product you would like to purchase (Press '4' to quit)");
                    System.out.println("Your choice: ");
                    productChoice = s3.nextLine();
                    try {
                        if (productChoice.equals("4")) {
                            
                            break;
                           
                       } else if (!Product.productExist(database, productChoice).next()) {
                           System.out.println("Product Doesn't exist in database, please enter again!");
                           continue;
                           
                       }
                       } catch (SQLException ex) {
                           ex.printStackTrace();
                       }
                    
                    System.out.println("Enter quantity: ");
                    quantity = s3.nextLine();
                    if (Product.checkQuantity(database, Integer.parseInt(quantity), productChoice)) {
                        System.out.println("Adding product to your cart...");
                        cart.addToCart(database.getCon(), productChoice, Integer.parseInt(quantity));
                        System.out.println("Successfully added to cart!");
                    } else {
                        System.out.println("Insufficient quantity, available quantity: " + Product.getQuantity(database, productChoice));
                    }
                } while(repeatProcess);
                
            } else if (choice.equals("2")) {
                String quantity;
                String productChoice;
                products[0] = new Motherboard();
                System.out.println("\n" + ((Motherboard) products[0]).getProductsFromDatabase(database));
                System.out.println("Enter the product you would like to purchase (Press '4' to quit)");
                    System.out.println("Your choice: ");
                    productChoice = s3.nextLine();
                    try {
                        if (productChoice.equals("4")) {
                            
                            break;
                           
                       } else if (!Product.productExist(database, productChoice).next()) {
                           System.out.println("Product Doesn't exist in database, please enter again!");
                           continue;
                           
                       }
                       } catch (SQLException ex) {
                           ex.printStackTrace();
                       }
                    
                    System.out.println("Enter quantity: ");
                    quantity = s3.nextLine();
                    if (Product.checkQuantity(database, Integer.parseInt(quantity), productChoice)) {
                        System.out.println("Adding product to your cart...");
                        cart.addToCart(database.getCon(), productChoice, Integer.parseInt(quantity));
                        System.out.println("Successfully added to cart!");
                    } else {
                        System.out.println("Insufficient quantity, available quantity: " + Product.getQuantity(database, productChoice));
                    }
            } else if (choice.equals("3")) {
                String quantity;
                String productChoice;
                products[0] = new Harddrive();
                System.out.println("\n" + ((Harddrive) products[0]).getProductsFromDatabase(database));
                System.out.println("Enter the product you would like to purchase (Press '4' to quit)");
                    System.out.println("Your choice: ");
                    productChoice = s3.nextLine();
                    try {
                        if (productChoice.equals("4")) {
                            
                            break;
                           
                       } else if (!Product.productExist(database, productChoice).next()) {
                           System.out.println("Product Doesn't exist in database, please enter again!");
                           continue;
                           
                       }
                       } catch (SQLException ex) {
                           ex.printStackTrace();
                       }
                    
                    System.out.println("Enter quantity: ");
                    quantity = s3.nextLine();
                    if (Product.checkQuantity(database, Integer.parseInt(quantity), productChoice)) {
                        System.out.println("Adding product to your cart...");
                        cart.addToCart(database.getCon(), productChoice, Integer.parseInt(quantity));
                        System.out.println("Successfully added to cart!");
                    } else {
                        System.out.println("Insufficient quantity, available quantity: " + Product.getQuantity(database, productChoice));
                    }
            }
        }
        
        
        //===============================================================================================================================
        boolean notExisted = true;
        int indexOfProduct = 0;
        boolean insertAgain = true;
        String productChoice;
        String operationChoice;
        Product[] product = new Product[100];
        
        Scanner s1 = new Scanner(System.in);
        Scanner s2 = new Scanner(System.in);
        while (notExisted) {
               System.out.println("\n"+Product.getProductsFromDatabase(database)); 
               System.out.println("1. Insert Product");
               System.out.println("2. Update Product");
               System.out.println("3. Delete Product");
               System.out.println("4. Exit");
               System.out.print("Please enter your choice: ");
               operationChoice = s1.nextLine();
                if (operationChoice.equals("1")) {
                    do {
                        productChoice= Product.ProductChoice(database);
                        if (productChoice.equals("1")) {
                            product[indexOfProduct] = new Monitor();
                            product[indexOfProduct].getProductDetailsFromUser();
                            ((Monitor) product[indexOfProduct]).addProduct(database);
                            indexOfProduct += 1;
                            insertAgain = Product.insertAgain();
                            //(Monitor product[indexOfProduct]).addProduct(database,)
                        } else if (productChoice.equals("2")) {
                            product[indexOfProduct] = new Harddrive();
                            product[indexOfProduct].getProductDetailsFromUser();
                            ((Harddrive) product[indexOfProduct]).addProduct(database);
                            indexOfProduct += 1;
                            insertAgain = Product.insertAgain();
                        } else if (productChoice.equals("3")) {
                            product[indexOfProduct] = new Motherboard();
                            product[indexOfProduct].getProductDetailsFromUser();
                            ((Motherboard) product[indexOfProduct]).addProduct(database);
                            indexOfProduct += 1;
                            insertAgain = Product.insertAgain();
                        } else if (productChoice.equals("4")) {
                            break;
                        }
                        else {
                            System.out.println("Wrong input! Enter either 1, 2 or 3");
                            continue;
                        }
                }while(insertAgain);    
               } else if (operationChoice.equals("2")) {
                   do {
                       System.out.println("\nEnter product to be updated (Press '4' to exit)");
                       System.out.print("Your choice: ");
                       productChoice = s1.nextLine();
                       try {
                        if (productChoice.equals("4")) {
                            
                            break;
                           
                       } else if (!Product.productExist(database, productChoice).next()) {
                           System.out.println("Product Doesn't exist in database, please enter again!");
                           continue;
                           
                       }
                       } catch (SQLException ex) {
                           ex.printStackTrace();
                       }
                       
                       String subProductId = Product.getSubProductId(database, productChoice);
                        if (subProductId.charAt(0) == 'M') {
                            product[indexOfProduct] = new Monitor();
                            System.out.println(((Monitor) product[indexOfProduct]).getProductsFromDatabase(database));
                            ((Monitor) product[indexOfProduct]).printColumnNames();
                            product[indexOfProduct].setProductName(productChoice);
                            System.out.println("Enter what column to update (Press '4' to exit)");
                            System.out.print("Your choice: ");
                            String column = s2.nextLine();
                            System.out.println("Enter new value: ");
                            System.out.print("Your choice: ");
                            String value = s2.nextLine();
                            ((Monitor) product[indexOfProduct]).updateProduct(database, column, value);
                        } else if (subProductId.charAt(0) == 'H') {
                            product[indexOfProduct] = new Harddrive();
                            System.out.println(((Harddrive) product[indexOfProduct]).getProductsFromDatabase(database));
                            ((Harddrive) product[indexOfProduct]).printColumnNames();
                            product[indexOfProduct].setProductName(productChoice);
                            System.out.println("Enter what column to update");
                            System.out.print("Your choice: ");
                            String column = s2.nextLine();
                            System.out.println("Enter new value: ");
                            System.out.print("Your choice: ");
                            String value = s2.nextLine();
                            ((Harddrive) product[indexOfProduct]).updateProduct(database, column, value);    
                        } else if ((String.valueOf(subProductId.charAt(0)) + String.valueOf(subProductId.charAt(1))).equals("MB")) {
                            product[indexOfProduct] = new Harddrive();
                            System.out.println(((Motherboard) product[indexOfProduct]).getProductsFromDatabase(database));
                            ((Motherboard) product[indexOfProduct]).printColumnNames();
                            product[indexOfProduct].setProductName(productChoice);
                            System.out.println("Enter what column to update");
                            System.out.print("Your choice: ");
                            String column = s2.nextLine();
                            System.out.println("Enter new value: ");
                            System.out.print("Your choice: ");
                            String value = s2.nextLine();
                            ((Motherboard) product[indexOfProduct]).updateProduct(database, column, value);    
                        } 
                }while(insertAgain);
               } else if (operationChoice.equals("3")) {
                   do {
                   productChoice = Product.ProductChoice(database);
                   if (productChoice.equals("1")) {
                        product[indexOfProduct] = new Monitor();
                        System.out.println(((Monitor) product[indexOfProduct]).getProductsFromDatabase(database));
                        ((Monitor) product[indexOfProduct]).printColumnNames();
                        System.out.println("Enter Product Name you wish to delete (Press '4' to exit)");
                        System.out.print("Your choice: ");
                        String productName = s2.nextLine();
                        product[indexOfProduct].setProductName(productName);
                        ((Monitor) product[indexOfProduct]).deleteProduct(database, productName);
                    } else if (productChoice.equals("2")) {
                        product[indexOfProduct] = new Harddrive();
                        System.out.println(((Harddrive) product[indexOfProduct]).getProductsFromDatabase(database));
                        ((Harddrive) product[indexOfProduct]).printColumnNames();
                        System.out.println("Enter Product Name you wish to delete (Press '4' to exit)");
                        System.out.print("Your choice: ");
                        String productName = s2.nextLine();
                        product[indexOfProduct].setProductName(productName);
                        ((Harddrive) product[indexOfProduct]).deleteProduct(database, productName);
                    } else if (productChoice.equals("3")) {
                        product[indexOfProduct] = new Motherboard();
                        System.out.println(((Motherboard) product[indexOfProduct]).getProductsFromDatabase(database));
                        ((Motherboard) product[indexOfProduct]).printColumnNames();
                        System.out.println("Enter Product Name you wish to delete (Press '4' to exit)");
                        System.out.print("Your choice: ");
                        String productName = s2.nextLine();
                        product[indexOfProduct].setProductName(productName);
                        ((Motherboard) product[indexOfProduct]).deleteProduct(database, productName);
                    } else if (productChoice.equals("4")) {
                        break;
                    }
                    else {
                        System.out.println("Wrong input! Enter either 1, 2 or 3");
                        continue;
                    }
                }while(insertAgain);
               } else {
                    System.out.println("INPUT FAILED, PLEASE ENTER 1, 2, OR 3");
               }
               
       }
        
     }
    
}