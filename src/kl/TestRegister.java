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
import java.util.Date;

/**
 *
 * @author user
 */
public class TestRegister {
    public static void main(String[] args){
        Connection myConObj = null;
        Statement mystatObj = null;
        ResultSet myResObj = null;
        String query = "Select * from Register";
        String url = "";
        
        try {
            myConObj = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/computershop", "ngphengloong", "lolhaha123");
            mystatObj = myConObj.createStatement();
            myResObj = mystatObj.executeQuery(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Register register = new Register();
        Scanner s1 = new Scanner(System.in);
        Scanner s2 = new Scanner(System.in);
        System.out.print("Enter Your Email:");
        String Email = s1.nextLine();
        register.setEmail(Email);
        System.out.print("Enter Your Password:");
        String Password = s1.nextLine();
        register.setPassword(Password);
        System.out.print("Enter Your Name:");
        String CustName = s1.nextLine();
        register.setCustName(CustName);
        System.out.print("Enter Your Address:");
        String Address = s1.nextLine();
        register.setAddress(Address);
        System.out.print("Enter Your Address States:");
        String States = s1.nextLine();
        register.setStates(States);
        System.out.print("Enter Your Phone Number:");
        int phoneNo = s2.nextInt();
        register.setPhoneNo(phoneNo);
        System.out.print("Enter Your Date Of Birth:");
        String DateOfBirth = s1.nextLine();
        register.setDateOfBirth(DateOfBirth);
        
        printInf(register);
            
            Register.insert(register.getEmail(),register.getPassword(),register.getCustName(),register.getAddress(),register.getStates(),register.getDateOfBirth(),register.getPhoneNo(),myConObj);
        
    }
    
    private static void printInf(Register register){
        System.out.print(register.toString());
    }
}