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
public class Register {

    String Email,Password,CustName,Address,States,DateOfBirth;
    int phoneNo;

    public Register() {
        this("","","","","","",0);
    }

    public Register(String Email, String Password, String CustName, String Address, String States, String DateOfBirth, int phoneNo) {
        this.Email = Email;
        this.Password = Password;
        this.CustName = CustName;
        this.Address = Address;
        this.States = States;
        this.DateOfBirth = DateOfBirth;
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

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setStates(String States) {
        this.States = States;
    }

    public void setDateOfBirth(String DateOfBirth) {
        this.DateOfBirth = DateOfBirth;
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

    public String getAddress() {
        return Address;
    }

    public String getStates() {
        return States;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public int getPhoneNo() {
        return phoneNo;
    }

    @Override
    public String toString() {
        return "Email=" + Email + "\nPassword=" + Password + "\nCustomer Name=" + CustName + "\nAddress=" + Address + "\nStates=" + States + "\nDateOfBirth=" + DateOfBirth + "\nPhone No=" + phoneNo;
    }
    
    
    public static void insert(String Email, String Password, String CustName, String Address, String States, String DateOfBirth, int phoneNo,Connection myConObj){
    try {
            myConObj = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/computershop", "ngphengloong", "lolhaha123");
            String insertNewUserSQL = "INSERT INTO Register (Email,Password,CustName,Address,States,PhoneNumber,DateOfBirth)" + " VALUES (?,?,?,?,?,?,?)";
            String updateUserSQL = "UPDATE Register SET Email = ?" + " WHERE Email = ?";
            PreparedStatement pstmt = myConObj.prepareStatement(insertNewUserSQL);
            
            pstmt.setString(1,Email);
            pstmt.setString(2, Password);
            pstmt.setString(3, CustName);
            pstmt.setString(4, Address);
            pstmt.setString(5, States);
            pstmt.setInt(6, phoneNo);
            pstmt.setString(7, DateOfBirth);
            
            
            
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
}
}