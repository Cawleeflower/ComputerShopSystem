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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Scanner;
import java.time.LocalDateTime; 
//import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;  
/**
 *
 * @author User
 */
public class Staff {

    /**
     * @param args the command line arguments
     */
   public static void printStaffInformation(int optional){
        UpdateStaff upstf = new UpdateStaff();
       Connection myConObj = null;
       Statement mystatObj = null;
       ResultSet myResObj = null;
       try{
            myConObj = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/computershop", "ngphengloong", "lolhaha123");
       }catch (SQLException e) {
            e.printStackTrace();
       }
      
       //String query = "Select * from computershop.Staff";
       String url = "";
       Scanner s1 = new Scanner(System.in); 
       Scanner s2 = new Scanner(System.in); 
      
       
       if (optional == 1){
          try {  
            System.out.println("StaffID :");
            String StaffID = s1.nextLine();
            System.out.println("");
            System.out.println("Staff Information for : " + StaffID);
            String query = "Select * from Staff WHERE StaffID='" + StaffID + "'";
            mystatObj = myConObj.createStatement();
            ResultSet rs = mystatObj.executeQuery("select * from Staff");
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int count = rsMetaData.getColumnCount();
            myResObj = mystatObj.executeQuery(query);
            if(myResObj.next()){
               for (int i = 1; i <= count; i++) {
                    String columnValue = myResObj.getString(i);
                    System.out.println("---------------------------------------");
                    System.out.print(rsMetaData.getColumnName(i)+" :/n "+ columnValue);
                    System.out.println("---------------------------------------");
                }
               int opt = Staff.subMenu();
               printStaffInformation(opt);
               
            }else
               System.out.println("Not match account found. Please contact the HR department");
               System.out.println("==========================================================");
               int opt = Staff.subMenu();
               printStaffInformation(opt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
           
         }
         if(optional == 2){
             System.out.print("Please Enter Your Staff ID:");
             String StaffID = s1.next();
             System.out.print("Please Enter Your New Email");
             String Email = s1.next();
             System.out.print("Please Enter Your New Contact Number");
             int ContactNumber = s2.nextInt();
             
            upstf.updateStaff(myConObj,"ContactNumber", ContactNumber, StaffID, Email);
            int opt = Staff.subMenu();
            printStaffInformation(opt);
        }           
         if(optional == 3){
            int opt = Staff.mainPage();
            printStaffInformation(opt);
         }
         
           
        
        
   }
   public static int mainPage(){
        Scanner s3 = new Scanner(System.in); 
        int optional = 0;
        System.out.println("***************************************************************");
        System.out.println("*                     1 Staff Add                             *");
        System.out.println("*                     2 Staff Information                     *");
        System.out.println("*                     3 Delivery Status                       *");
        System.out.println("*                     4 Purchasing                            *");
        System.out.println("*                     5 Staff                                 *");
        System.out.println("*                     6 Logout                                *");
        System.out.println("***************************************************************");
        System.out.print("Please Enter Your Option(1,2,3,4,5,6) : ");
        optional = s3.nextInt();
        return optional;
   }
   public static int subMenu(){
       Scanner s3 = new Scanner(System.in); 
       int optional = 0;
       System.out.println("***************************************************************");
       System.out.println("*                    1 Staff Information                      *");
       System.out.println("*                    2 Staff Update                           *");
       System.out.println("*                    3 Back To Staff Main Page                *");
       System.out.println("***************************************************************");
       System.out.print("Please Enter Your Option(1,2,3) : ");
       optional = s3.nextInt();
       return optional;
       
   }
   public static void addStaff(){
        Validation valid = new Validation();
        StaffClass sc = new StaffClass();
        Scanner s1 = new Scanner(System.in);
        Scanner s2 = new Scanner(System.in); 
        
         Connection myConObj = null;
            try{
            myConObj = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/computershop", "ngphengloong", "lolhaha123");
            }catch (SQLException e) {
            e.printStackTrace();
            }
            String url = "";
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
                LocalDateTime now = LocalDateTime.now(); 
                Timestamp timestamp = Timestamp.valueOf(now);

                System.out.println(dtf.format(now));  

                System.out.print("Plases Enter New Staff ID : ");
                String StaffID = s1.nextLine();
                StaffID = mStaff(StaffID);
                sc.setStaffID(StaffID);
                System.out.print("Plaese Enter New Your Name : ");
                String Name = s2.nextLine();
                Name = mName(Name);
                sc.setName(Name);
                System.out.print("Plases Enter Your Age : ");
                int Age = s1.nextInt();
                Age = valid.validAge(Age);
                sc.setAge(Age);
                System.out.print("Plases Enter Your Email : ");
                String Email = s2.nextLine();
                Email = valid.validEmail(Email);
                sc.setEmail(Email);
                System.out.print("Plases Enter Your Contact Number : ");
                String Phone = s2.nextLine();
                Phone = valid.validContact(Phone);
                sc.setContactNumber(Phone);
                System.out.print("Plases Enter Your Address : ");
                String Address = s2.nextLine();
                sc.setAddress(Address);
                System.out.print("Plases Enter Your Password : ");
                String Password = s2.nextLine();
                Password = valid.validPass(Password);
                sc.setPassword(Password);
                StaffClass.insertStaff(myConObj, sc.getStaffID(), sc.getName(), sc.getAge(), sc.getEmail(), null , null , sc.getContactNumber(), sc.getAddress(), sc.getPassword());
         
   }
   public static String mStaff(String StaffID){
       Connection myConObj = null;
       Statement mystatObj = null;
       ResultSet myResObj = null;
       Scanner s1 = new Scanner(System.in);
            try{
                myConObj = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/computershop", "ngphengloong", "lolhaha123");
                }catch (SQLException e) {
                 e.printStackTrace();
                 }
            String url = "";
            try{
                mystatObj = myConObj.createStatement();
                String query = "Select * from Staff WHERE StaffID='" + StaffID + "'";
                ResultSet rs = mystatObj.executeQuery(query);
              if(!rs.next()) {
                   //StaffID = rs.getString("StaffID");
                   return StaffID;
               } else {
                   System.out.println("This Staff ID Already Exits, Plaese Enter The New Staff");
                   StaffID = "";
                   System.out.print("Plases Enter New Staff ID : ");
                   StaffID = s1.nextLine();
                   return StaffID;
               }
               
            } catch (SQLException e) {
            e.printStackTrace();
            }
           return StaffID;
   }
   public static String mName(String Name){
        Connection myConObj = null;
        Statement mystatObj = null;
         Scanner s1 = new Scanner(System.in);
            try{
                myConObj = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/computershop", "ngphengloong", "lolhaha123");
                }catch (SQLException e) {
                 e.printStackTrace();
                 }
            String url = "";
            try{
                mystatObj = myConObj.createStatement();
                String query = "Select * from Staff WHERE Name='" + Name + "'";
                ResultSet rs = mystatObj.executeQuery(query);
              if(!rs.next()) {
                   //StaffID = rs.getString("StaffID");
                   return Name;
               } else {
                   System.out.println("This Name Already Exits, Plaese Enter The New Staff");
                   Name = "";
                   System.out.print("Plases Enter New Name : ");
                   Name = s1.nextLine();
                   return Name;
               }
               
            } catch (SQLException e) {
            e.printStackTrace();
            }
           return Name;
   }
   }


