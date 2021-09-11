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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class StaffClass {
    private String staffID;
    private String name;
    private int age;
    private String email;
    private String contactNumber;
    private String password;
    private String address;
    

    public StaffClass() {
       this("","",0,"","","","");
    }
    
    public StaffClass( String staffID, String name, int age, String email, String contactNumber, String password, String address) {
        this.staffID = staffID;
        this.name = name;
        this.age = age;
        this.email = email;
        this.contactNumber = contactNumber;
        this.password = password;
        this.address = address;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

   
   
    public static void insertStaff(Connection myConObj,String StaffID, String Name, int Age, String Email, String LogInTime, String LogOutTime, String ContatcNumber, String Address, String Password ){
            Scanner s1 = new Scanner(System.in);
     try{
            myConObj = DriverManager.getConnection("jdbc:derby://localhost:1527/test", "ngphengloong", "123");
            String insertNewUserSQL = "INSERT INTO NGPHENGLOONG.STAFF(STAFFID, NAME, AGE, EMAIL, LOGINTIME,LOGOUTTIME, CONTACTNUMBER,ADDRESS, PASSWORD  )" + " VALUES (?,?,?,?,?,?,?,?,?)";
            //String updateUserSQL = "UPDATE computershop.Delivery SET DeliveryID = ?" + " WHERE DeliveryID = ?";
            PreparedStatement pstmt = myConObj.prepareStatement(insertNewUserSQL);
            pstmt.setString(1, StaffID);
            pstmt.setString(2, Name);
            pstmt.setInt(3, Age);
            pstmt.setString(4, Email);
            pstmt.setString(5, LogInTime);
            pstmt.setString(6, LogOutTime);
            pstmt.setString(7, ContatcNumber);
            pstmt.setString(8, Address);
            pstmt.setString(9, Password);
            
           
            pstmt.executeUpdate();
            System.out.println("Successfull");
            Purchase.purchasesMenu();
           }catch (SQLException e) {
            e.printStackTrace();
            
        }    
    }
    public void displayDataStaff(){
        Scanner Scan1 = new Scanner(System.in);
        Connection myConObj = null;
        Statement mystatObj = null;
        ResultSet myResObj = null;
        String query = "SELECT * FROM STAFF";
        String url = "";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now(); 
        Timestamp timestamp = Timestamp.valueOf(now);

        System.out.println(dtf.format(now));  
                System.out.print("| Staff ID :        Name        :         Email         :     Loging Time     :     Logout Time     :   Contact Number     :         Address                  |\n");
                System.out.print("|----------:--------------------:-----------------------:---------------------:---------------------:----------------------:---------------------------------|\n");
        try {
            myConObj = DriverManager.getConnection("jdbc:derby://localhost:1527/test", "ngphengloong", "123");
            mystatObj = myConObj.createStatement();
            myResObj = mystatObj.executeQuery(query);
            while(myResObj.next()){
                String StaffID = myResObj.getString("STAFFID");
                String Name = myResObj.getString("NAME");
                String Email = myResObj.getString("EMAIL");
                String LogInTime = myResObj.getString("LOGINTIME");
                String LogOutTime = myResObj.getString("LOGOUTTIME");
                String ContactNumber = myResObj.getString("CONTACTNUMBER");
                String Address = myResObj.getString("Address");
                System.out.println("|"+StaffID  +"    \t: "+ Name + "        \t: " + Email + "   \t: " + LogInTime +"\t:" + LogOutTime  + "              \t:" + ContactNumber  + "       \t:" + Address + "          \t\t |");
                System.out.print("|-------------------------------------------------------------------------------------------------------------------------------------------------------------|\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
                
    }
    
}
