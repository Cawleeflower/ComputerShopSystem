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
public class CustomerClass {
    protected String Email,Password;
    String CustID,CustName,Address,States,DateOfBirth;
    String phoneNo;

    public CustomerClass() {
        this("","","","","","","","");
    }

    public CustomerClass(String Email, String Password, String CustName, String Address, String States, String DateOfBirth, String phoneNo,String CustID) {
        this.CustID = CustID;
        this.Email = Email;
        this.Password = Password;
        this.CustName = CustName;
        this.Address = Address;
        this.States = States;
        this.DateOfBirth = DateOfBirth;
        this.phoneNo = phoneNo;
    }

    public void setCustID(String CustID) {
        this.CustID = CustID;
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

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getCustID() {
        return CustID;
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

    public String getPhoneNo() {
        return phoneNo;
    }
    
    
    
    public static void insert(String Email, String Password, String CustName, String Address, String States, String DateOfBirth, String phoneNo,String CustID,Connection myConObj){
    try {
            myConObj = DriverManager.getConnection("jdbc:derby://localhost:1527/test", "ngphengloong", "123");
            String insertNewUserSQL = "INSERT INTO Customer (Email,Password,CustName,Address,States,PhoneNumber,DateOfBirth,CustID)" + " VALUES (?,?,?,?,?,?,?,?)";
            String updateUserSQL = "UPDATE Customer SET Email = ?" + " WHERE Email = ?";
            PreparedStatement pstmt = myConObj.prepareStatement(insertNewUserSQL);
            
            pstmt.setString(1,Email);
            pstmt.setString(2, Password);
            pstmt.setString(3, CustName);
            pstmt.setString(4, Address);
            pstmt.setString(5, States);
            pstmt.setString(6, phoneNo);
            pstmt.setString(7, DateOfBirth);
            pstmt.setString(8, CustID);
            
            
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
}
    
    public static void CustomerInfor(String Email, Register reg){
        Connection myConObj = null;
        Statement mystatObj = null;
        ResultSet myResObj = null;
        String replacedEmail = "";
        String replacedPassword = "";
        String replacedCustName = "";
        String replacedPhoneNumber = "";
        String query = "SELECT * FROM CUSTOMER";
        String url = "";
        
        try {
            myConObj = DriverManager.getConnection("jdbc:derby://localhost:1527/test", "ngphengloong", "123");
            mystatObj = myConObj.createStatement();
            myResObj = mystatObj.executeQuery(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        CustomerClass Cust = new CustomerClass();
        Scanner s1 = new Scanner(System.in);
        Scanner s2 = new Scanner(System.in);
        Random rd=new Random();
                        HashSet<Integer> set= new HashSet<Integer>();
                        while(set.size()<1)
                        {
                            int ran=rd.nextInt(9999)+10000;
                            set.add(ran);
                        }
                        int len = 5;
                        String random=String.valueOf(len);
                        for(int  random1:set)
                        {
                            System.out.println("Your ID : " + random1);
                            random=Integer.toString(random1);
                            Cust.setCustID(random);
                        }
        if (Email.equals("")) {
            System.out.println("RAN IN EMAIL.equals");
            replacedEmail = reg.getEmail();
            replacedPassword = reg.getPassword();
            replacedCustName = reg.getCustName();
            replacedPhoneNumber = reg.getCustName();
        } else {
            try {
                query = "SELECT * FROM REGISTER WHERE EMAIL = '" + Email + "'";
                Statement stmt = myConObj.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    replacedEmail = rs.getString("Email");
                    replacedPassword= rs.getString("Password");
                    replacedCustName = rs.getString("CustName");
                    replacedPhoneNumber = rs.getString("PhoneNumber");
                }
            }catch(SQLException ex) {
                ex.printStackTrace();
            }
            
        }
        System.out.println("Enter Your Email:"+replacedEmail);
        Cust.setEmail(replacedEmail);
        System.out.println("Enter Your Password:"+replacedPassword);
        Cust.setPassword(replacedPassword);

        System.out.println("Enter Your Name:"+replacedCustName);
        Cust.setCustName(replacedCustName);

        System.out.print("Enter Your Address:");
        String Address = s1.nextLine();
        Cust.setAddress(Address);
        System.out.print("Enter Your Address States:");
        String States = s1.nextLine();
        Cust.setStates(States);
        System.out.println("Enter Your Phone Number:"+replacedPhoneNumber);
        Cust.setPhoneNo(replacedPhoneNumber);
        System.out.print("Enter Your Date Of Birth(YYYY-MM-DD):");
        String DateOfBirth = s1.nextLine();
        Cust.setDateOfBirth(DateOfBirth);

    }
}
