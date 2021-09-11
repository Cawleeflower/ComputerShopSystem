/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
/**
 *
 * @author User
 */
public class Delivery {
    private static HashMap<String, Integer> Product = new HashMap<String, Integer>();
    public static void delivery(){
        Scanner s1 = new Scanner(System.in);
        Scanner s2 = new Scanner(System.in);
        DeliveryClass dc = new DeliveryClass();
        Connection myConObj = null;
        Statement mystatObj = null;
        ResultSet myResObj = null;
            try{
            myConObj = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/computershop", "ngphengloong", "lolhaha123");
            }catch (SQLException e) {
            e.printStackTrace();
            }
       String url = "";
       int opt=0;
       System.out.println("1. Create Delivery Information");
       System.out.println("2. Update Delivery Information");
       System.out.println("3. Update Stock Warehouse     ");
       System.out.println("4. Back To Main Menu          ");
       System.out.println("Pleases Enter Your Option(1,2,3,4):");
       opt = s1.nextInt();
       
      if(opt == 1){
                
                  try{
                         //TODO Generate Number Of Delivery Number
                        Random rd=new Random();
                        HashSet<Integer> set= new HashSet<Integer>();
                        while(set.size()<1)
                        {
                            int ran=rd.nextInt(99999)+100000;
                            set.add(ran);
                        }
                        int len = 6;
                        String random=String.valueOf(len);
                        for(int  random1:set)
                        {
                            System.out.println("Delivery ID : " + random1);
                            random = Integer.toString(random1);
                            dc.setDeliveryID(random);
                            System.out.println("===============================");
                        }
                        
                        //Add 2 week to the current date
                         LocalDate today = LocalDate.now();
                         LocalDate next2Week = today.plus(2, ChronoUnit.WEEKS);
                         System.out.println("Next week: " + next2Week.toString());
                         dc.setDateDelivery(next2Week.toString());
                         System.out.println("Delivery will be send in 14 days");
                         System.out.println("===============================");
                  
                       System.out.print("Customer name : ");
                       String name = s2.nextLine();
                       String query = "Select * from Customer WHERE CustName ='" + name + "'";
                       mystatObj = myConObj.createStatement();
                       ResultSet rs = mystatObj.executeQuery("select * from Customer" );
                       ResultSetMetaData rsMetaData = rs.getMetaData();
                       int count = rsMetaData.getColumnCount();
                       myResObj = mystatObj.executeQuery(query);
                       
                       if(myResObj.next()){
                          String Address = myResObj.getString("Address");
                          String States = myResObj.getString("States");
                          String CustomerID = myResObj.getString("CustID");
                          System.out.println("Customer ID : "+ CustomerID);
                          dc.setCustID(CustomerID);
                          System.out.println("Address : "+ Address);
                          dc.setAddress(Address);
                          System.out.println("States : "+ States);
                          dc.setStates(States);
                          double deliveryFees = dc.calculateState();
                          dc.setDeliveryFees(deliveryFees);
                          System.out.println("-----------------------------");
                         }
                           }catch (SQLException e) {
                           e.printStackTrace();
                           }
                       //TODO to Optional Delivery Company
                       System.out.println("===============================");
                       System.out.println("Delivery Company Name");
                       System.out.println("PosLaju");
                       System.out.println("Skynet");
                       System.out.println("Gdex");
                       System.out.println("J&T Express");
                       System.out.println("");
                       System.out.print("Please Enter The Company Name :");
                       String DeliveryComapany = s2.next();
                       dc.setDeliveryCompany(DeliveryComapany);
                       System.out.println("===============================");
                       System.out.print("Details : ");
                       String Details = s1.next();
                       dc.setDetails(Details);
                       dc.setStatus("Shipping");
                       System.out.println("===============================");
                       
                       DeliveryClass.insertDelivery(myConObj, dc.getDeliveryID(), dc.getDateDelivery(), dc.getDeliveryCompany(), dc.getDetails(), dc.getAddress(), dc.getStates(), dc.getDeliveryFees(), dc.getStatus(), dc.getCustID());
      }
      if(opt ==2) {
            System.out.print("Enter You Delivery ID : ");
            String deliveryID = s2.nextLine();
            System.out.print("Please Enter You New Details : ");
            String details = s2.nextLine();
            String status = "Recived";
            DeliveryClass.updateDelivery(myConObj, deliveryID, details, status);
      }
      if(opt == 3){
          Delivery.enterProduct();
      }
      if(opt ==4){
          Staff.mainPage();
      }
   
}
        public static void reduceProduct(String CustID,Connection con){
            DeliveryClass dc = new DeliveryClass();
            Scanner s1 = new Scanner(System.in);
            Connection myConObj = null;
            Statement mystatObj = null;
            ResultSet myResObj = null;
               try{
            myConObj = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/computershop", "ngphengloong", "lolhaha123");
            }catch (SQLException e) {
            e.printStackTrace();
            }
            String url = "";
            int storeQuantity =0;
                try{
                    String product = "Select * from History WHERE CustID ='" + CustID + "'";
                    mystatObj = myConObj.createStatement();
                    ResultSet ss = mystatObj.executeQuery("select *from History");
                    ResultSetMetaData ssMetaData = ss.getMetaData();
                    int counts = ssMetaData.getColumnCount();
                    myResObj = mystatObj.executeQuery(product);
                        if(myResObj.next()){
                                        String Name = myResObj.getString("ProductName");
                                        String Quantity = myResObj.getString("Quantity");
                                        System.out.print("Product : "+ Name);
                                        System.out.println("Quantity : "+ Quantity);
                                        System.out.println("-----------------------------");
                                      }
                        }catch (SQLException e) {
                            e.printStackTrace();
                        }
        }
     
        public static void upstock(Connection con ,HashMap<String, Integer> Product){
        Iterator hmIterator = Product.entrySet().iterator();
          int storeQuantity =0;
          Connection myConObj = null;
            while (hmIterator.hasNext()) {
                Map.Entry mapElement = (Map.Entry)hmIterator.next();

               try{
                myConObj = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/computershop", "ngphengloong", "lolhaha123");
                }catch (SQLException e) {
                e.printStackTrace();
                }
                String url = "";
                    try{
                        Statement stmt = myConObj.createStatement();
                        ResultSet rs = stmt.executeQuery("SELECT Quantity FROM Warehouses WHERE NameProduct= '"+ mapElement.getKey() + "'");
                    if(rs.next()) {
                        storeQuantity = rs.getInt(1);
                    }
                    } catch (Exception ex) {
                      System.out.println(ex);
                    }
                        int marks = (storeQuantity - (int)mapElement.getValue());
                        String updateSql = "UPDATE computershop.Warehouses SET " + "Quantity"+ "=" +"'"+ marks +"'"+ " WHERE NameProduct = '" + mapElement.getKey() +"'";
                 try {
                        Statement mystatObj = con.createStatement();

                        mystatObj.execute(updateSql);
                    }catch (Exception ex) {
                    System.out.println(ex);
                    }
        }
      
}
        public static void enterProduct(){
            //TO DO The Reduce Stock
            Scanner s1 = new Scanner(System.in);
            Connection myConObj = null;
              try{
                    myConObj = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/computershop", "ngphengloong", "lolhaha123");
            }catch (SQLException e) {
            e.printStackTrace();
            }
            String url = "";
            System.out.println("Please Enter the Customer ID");
            String CustID = s1.nextLine();
            Delivery.reduceProduct(CustID, myConObj);
            System.out.print("Enter The Product To Reduce In Warehouses ");
            System.out.print("Enter Product : ");
            String name = s1.nextLine();
            System.out.print("Enter Quantity : ");
            int quantity = s1.nextInt();
            Product.put(name,quantity);
            Delivery.upstock(myConObj,Product);
        }
        }
 

     
          

