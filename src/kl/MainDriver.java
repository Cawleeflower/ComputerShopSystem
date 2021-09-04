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

/**
 *
 * @author Ng Pheng Loong
 */

public class MainDriver {
    public static void main(String[] args) {
        
        Database dbComputerShop = new Database();
        Product[] arrProduct = new Product[5];
        Cart cart = new Cart();
        Scanner scan = new Scanner(System.in);
        Scanner scan1 = new Scanner(System.in);
        
        cart.addToCart(dbComputerShop.getCon(),"b@gmail.com", "BBB", 5, 5);
        cart.addToCart(dbComputerShop.getCon(),"b@gmail.com", "BBB", 5, 5);
    
        cart.addToCart(dbComputerShop.getCon(),"b@gmail.com", "CCC", 5, 5);
    }
}