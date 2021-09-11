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
import java.util.HashSet;
import java.util.Random;
/**
 *
 * @author user
 */
public class Register {
    protected String Email,Password;
    String CustName;
    int phoneNo;

    public Register() {
        this("","","",0);
    }

    public Register(String Email, String Password, String CustName, int phoneNo) {
        this.Email = Email;
        this.Password = Password;
        this.CustName = CustName;
        this.phoneNo = phoneNo;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setCustName(String CustName) {
        this.CustName = CustName;
    }
    
    public void setPhoneNo(int phoneNo) {
        this.phoneNo = phoneNo;
    }
   
    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public String getCustName() {
        return CustName;
    }

    public int getPhoneNo() {
        return phoneNo;
    }

    @Override
    public String toString() {
        return "Email=" + Email + "\nPassword=" + Password + "\nCustomer Name=" + CustName + "\nPhone No=" + phoneNo;
    }
    
    
    public static void insert(String Email, String Password, String CustName,int phoneNo,Connection myConObj){
    try {
            myConObj = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/computershop", "ngphengloong", "lolhaha123");
            String insertNewUserSQL = "INSERT INTO Register (Email,Password,CustName,PhoneNumber)" + " VALUES (?,?,?,?)";
            String updateUserSQL = "UPDATE Register SET Email = ?" + " WHERE Email = ?";
            PreparedStatement pstmt = myConObj.prepareStatement(insertNewUserSQL);
            
            pstmt.setString(1,Email);
            pstmt.setString(2, Password);
            pstmt.setString(3, CustName);
            pstmt.setInt(4, phoneNo);

            
            
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
}
    public static Register CustRegister(){
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
        System.out.print("Enter Your Phone Number:");
        int phoneNo = s2.nextInt();
        register.setPhoneNo(phoneNo);
    
            Register.insert(register.getEmail(),register.getPassword(),register.getCustName(),register.getPhoneNo(),myConObj);
        return register;
    }
}
