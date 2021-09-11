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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author User
 */
public class Purchase {
     private static HashMap<String, Integer> Product = new HashMap<String, Integer>();
      public static void purchaseInsert(){
       
       PurchasingClass ps = new PurchasingClass();
        Connection myConObj = null;
        Statement mystatObj = null;
        ResultSet myResObj = null;
            try{
            myConObj = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/computershop", "ngphengloong", "lolhaha123");
            }catch (SQLException e) {
            e.printStackTrace();
            }
            String url = "";
          
            Scanner s1 = new Scanner(System.in); 
            Scanner s2 = new Scanner(System.in); 
            System.out.println("***************************************************************");
            System.out.println("*                     4:3 Screen - RM 100                     *");
            System.out.println("*                     16:9 Screen - RM 200                    *");
            System.out.println("*                     21:9 Screen - RM 400                    *");
            System.out.println("***************************************************************");
            System.out.println("*                     Standard-ATX - RM 125                   *");
            System.out.println("*                     Mirco-ATX - RM 100                      *");
            System.out.println("*                     Mini-TX - RM 85                         *");
            System.out.println("***************************************************************");
            System.out.println("*                     CPU - Intel i5 - RM 350                 *");
            System.out.println("*                     CPU - Intel i7 - RM 450                 *");
            System.out.println("*                     CPU - AMD RYZEN 5 - RM 380              *");
            System.out.println("*                     CPU - AMD RYZEN 7 - RM 480              *");
            System.out.println("***************************************************************");
            System.out.println("*                     HDD - 4RAM - RM 200                     *");
            System.out.println("*                     HDD - 8RAM - RM 400                     *");
            System.out.println("***************************************************************");
            System.out.println("*                     SSD - 4RAM - RM 300                     *");
            System.out.println("*                     SSD - 8RAM - RM 400                     *");
            System.out.println("***************************************************************");
            System.out.println("");
            ps.setVendor("L&L Supplier");
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
                            System.out.println("Invoices "+ random1);
                            random = Integer.toString(random1);
                            ps.setPurchasesID(random);
                            System.out.println("===============================");
                        }
                         //TODO to Enter the Date Delivery
                      Date date = Calendar.getInstance().getTime();  
                      DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");  
                      String strDate = dateFormat.format(date);  
                      System.out.println("Today Date: " + strDate);  
                      ps.setPurchasesDate(strDate);
                      
            System.out.print("Enter the Product Name : ");
            String name = s2.nextLine();
            ps.setProductList(name);
            System.out.print("Please enter the price : ");
            int prices = s1.nextInt(); 
            
            ps.setPrice(prices);
            System.out.print("Please enter quantity : ");
            int quantity = s1.nextInt();
            Product.put(name,quantity);
            ps.setQuantity(quantity);
            PurchasingClass.addTotalPrice(prices, quantity);
            ps.setPaymentMethod("Unpaid");
            Purchase.viewStock(myConObj,Product);
           
            System.out.println("");
            System.out.println("Total of Purchases : RM " + PurchasingClass.getTotalPrice());
            System.out.println("Services Tax       : RM " + PurchasingClass.getServicesTax());
            System.out.println("Total              : RM " + ps.calculateTotal(PurchasingClass.getTotalPrice()));
            PurchasingClass.insertPurchases(myConObj, ps.getPurchasesID(), ps.getVendor(), ps.getPurchasesDate(), ps.getProductList(),ps.calculateTotal(PurchasingClass.getTotalPrice()),ps.getPrice(),ps.getQuantity(),ps.getPaymentMethod());
            System.out.println("");
            System.out.println("Did you want to continue purchases Yes/No :  ");
            String comfirm = s1.next();
            comfirm.toLowerCase();
            if(  comfirm.toLowerCase().equals("yes")){
                 Purchase.purchaseInsert();
            } else{
                 System.out.println("Plaeses Make Payment In 14 Days Before");
                 Purchase.purchasesMenu();
                 
      }
            
           
      }
     public static int purchasesMenu(){
       Scanner s1 = new Scanner(System.in); 
       Connection myConObj = null;
         try{
            myConObj = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/computershop", "ngphengloong", "lolhaha123");
            }catch (SQLException e) {
            e.printStackTrace();
            }
            String url = "";
       int option = 0;
            System.out.println("***************************************************************");
            System.out.println("*                    1 Purchasing                             *");
            System.out.println("*                    2 Warehouses Stock                       *");
            System.out.println("*                    3 Make Payemnt                           *");
            System.out.println("*                    4 Back To Main Menu                      *");
            System.out.println("***************************************************************");
            System.out.print("Please Enter Your Option(1,2,3,4) : ");
            option = s1.nextInt();

       if(option ==1){
            Purchase.purchaseInsert();
        }
       if(option ==2){
                System.out.println("***************************************************************");
                System.out.println("*                    1 Total stock product                    *"); 
                System.out.println("*                    2 Back                                   *");
                System.out.println("***************************************************************");
                System.out.print("Please Enter Your Option(1,2) : ");
                int opt = s1.nextInt();
        if(opt ==1){
                Purchase.viewStock(myConObj,Product);
                }
        if(opt ==2){
                 Purchase.purchasesMenu();
        }
        else
                System.out.println("Error!");
                Purchase.purchasesMenu();
        
       }
       if(option ==3){
          
                Purchase.makePurchase();
       }
       if(option ==4){
                Staff.mainPage();
       }
       return option;
 }
       public static void viewStock(Connection con, HashMap<String, Integer> Product ){
          Iterator hmIterator = Product.entrySet().iterator();
          int storeQuantity =0;
          while (hmIterator.hasNext()) {
              Map.Entry mapElement = (Map.Entry)hmIterator.next();
              try{ 
                    Connection myConObj = null;
                    myConObj = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/computershop", "ngphengloong", "lolhaha123");
                    Statement stmt = myConObj.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT Quantity FROM Warehouses WHERE NameProduct= '"+ mapElement.getKey() + "'");
                    if(rs.next()) {
                      storeQuantity = rs.getInt(1);
                    }
                     else{
                            int marks = ((int)mapElement.getValue());
                            String insertNewUserSQL = "INSERT INTO computershop.Warehouse(NameProduct, Quantity )" + " VALUES (?,?)";
                        try {
                            PreparedStatement pstmt = myConObj.prepareStatement(insertNewUserSQL);
                            pstmt.setString(1, mapElement.getKey().toString());
                            pstmt.setInt(2, marks);

                            pstmt.execute(insertNewUserSQL);
                       }    catch (Exception ex) {
                            System.out.println(ex);
                       }
                       }
            } catch (Exception ex) {
            System.out.println(ex);
        }
            int marks = ((int)mapElement.getValue()+ storeQuantity);
            String updateSql = "UPDATE computershop.Warehouses SET " + "Quantity"+ "=" +"'"+ marks +"'"+ " WHERE NameProduct = '" + mapElement.getKey() +"'";
        try {
            Statement mystatObj = con.createStatement();
            mystatObj.execute(updateSql);
        }   catch (Exception ex) {
            System.out.println(ex);
        }
        }
        
     }
       public static void makePurchase(){
           PurchasingClass ps = new PurchasingClass();
           Validation vd = new Validation();
           Connection myConObj = null;
           Statement mystatObj = null;
           ResultSet myResObj = null;
           Scanner s1 = new Scanner(System.in); 
           Scanner s2 = new Scanner(System.in); 
           Scanner s3 = new Scanner(System.in); 
           try{
                myConObj = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/computershop", "ngphengloong", "lolhaha123");
             }catch (SQLException e) {
            e.printStackTrace();
       }
             String url = ""; 
           try {  
                String query = "Select * from computershop.Purchase";
                mystatObj = myConObj.createStatement();
                ResultSet rs = mystatObj.executeQuery("select * from Purchase");
                ResultSetMetaData rsMetaData = rs.getMetaData();
                int count = rsMetaData.getColumnCount();
                myResObj = mystatObj.executeQuery(query);
            if(myResObj.next()){
               for (int i = 1; i <= count; i++) {
                    String columnValue = myResObj.getString(i);
                    System.out.println("---------------------------------------");
                    System.out.println(rsMetaData.getColumnName(i)+" : "+ columnValue);
                    
                }
            }
               } catch (SQLException e) {
               e.printStackTrace();
        }
           System.out.println("*******************");
           System.out.println("*  Credit Card    *");
           System.out.println("*  Debit Card     *");
           System.out.println("*  Cash           *");
           System.out.println("*******************");
           System.out.print("Please Enter Your Payment Method : ");
           String Method = s2.nextLine();
           ps.setPaymentMethod(Method);
           if (Method.equals("Credit Card")){
                System.out.println(" Visa");
                System.out.println(" Master");
                System.out.print("Plases Enter Visa or Master : ");
                String type = s1.next();
                ps.setCreditType(type);
                System.out.print("Please Enter You Want To Purchase The Invoices : ");
                String inv = s1.next();
                System.out.print("Plaese Enter Your Credit Card Name/ Bank : ");
                String card = s1.next();
                ps.setCreditCard(card);
                System.out.print("Please Enter Your Credit Card Number : ");
                String num = s2.nextLine();
                num = vd.validCard(num, type);
                ps.setPaymentNumber(num);
                System.out.print("Please Enter Expiration Date : ");
                String ed =  s2.nextLine();
                System.out.print("Please Enter CVV Number : ");
                int cvv =  s3.nextInt();
                
                Purchase.upadatePayment(myConObj,Method, card, num, inv, type);
                 
            }
            if (Method.equals("Debit Card")){
                System.out.println("Visa");
                System.out.println("Master");
                System.out.print("Plases Enter Visa or Master : ");
                String type = s1.next();
                ps.setCreditType(type);
                System.out.print("Please Enter You Want To Purchase The Invoices : ");
                String inv = s1.next();
                System.out.print("Plaese Enter Your Credit Card Name/ Bank : ");
                String card = s1.next();
                ps.setCreditCard(card);
                System.out.print("Please Enter Your Credit Card Number : ");
                String num = s2.nextLine();
                ps.setPaymentNumber(num);
                System.out.print("Please Enter Expiration Date : ");
                String ed =  s2.nextLine();
                System.out.print("Please Enter CVV Number : ");
                int cvv =  s3.nextInt();
                Purchase.upadatePayment(myConObj,Method, card, num, inv, type);
            }
               
           if(Method.equals("Cash")){
               System.out.println("Using Cash Payment For This Invoices.");
               System.out.print("Please Enter Number You Want To Purchase The Invoices : ");
               String inv = s1.next();
               ps.setPaymentMethod("Cash");
               Purchase.upadatePayment(myConObj,Method, null, null, inv, null);
               Purchase.purchasesMenu();
           } 
           else{
               System.out.print("Error!");
               Purchase.purchasesMenu();
           }
           
       }
       public static void upadatePayment(Connection myConObj,String Method, String card, String num, String inv, String type){
              try{
            myConObj = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/computershop", "ngphengloong", "lolhaha123");
         }catch (SQLException e) {
            e.printStackTrace();
       }
              String updateSql = "UPDATE Purchase SET " + "PaymentMethod" + "='" + Method + "'" + ",CreditName = '" + card + "'" + ",CreditNumber = '" + num + "'" +",CardType ='"+ type +"'"+ " WHERE PurchaseID = '" + inv +"'";
                try {
           Statement mystatObj = myConObj.createStatement();
                mystatObj.execute(updateSql);
                Purchase.purchasesMenu();
                }catch (Exception ex) {
                System.out.println(ex);
                }
       }
        public static String compareProduct(String name){
            //TO Do make sure the product true
            if (name.equals("4:3 Screen")){
                
            }
            else 
                System.out.println("This Product Did not sell by the supplier");
                
            return name ;
           
        }
        
}
