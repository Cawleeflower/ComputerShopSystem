/*1
 * To1 change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Ng Pheng Loong
 */
public class ComputerShopMainClass {
    public static void main(String[] args){
        Login log = new Login();
        Register reg = new Register();
        
       Scanner s1 = new Scanner(System.in); 
       Connection myConObj = null;
         try{
            myConObj = DriverManager.getConnection("jdbc:derby://localhost:1527/test", "ngphengloong", "123");
            }catch (SQLException e) {
            e.printStackTrace();
            }
         
            while(true){
                int optional = Login.mainPage();
        
                if (optional == 1){
                    
                Database database = new Database();

                String Email="",Password="",CustName="",Address="",States="",DateOfBirth="";
                String phoneNo="";

                System.out.println("--------------------------------");
                System.out.println("1. Customer Login               ");
                System.out.println("2. Staff Login                  ");
                System.out.println("Select your choose :");
                int choose = s1.nextInt();

                if(choose == 1){
                    Email = Customer.CustLogin();
                    System.out.println("Login Successfully!");
                    System.out.println("Welcome to computer shop!!");
                    optional = Customer.mainPage();

                    if (optional == 1){
                        Customer.DisplayCustomerMenu(Email,reg);    
                    } else if (optional == 2) {



                    }
                    else if (optional == 3) {

                       Database dbComputerShop = new Database();
                       Order order= new Order();
                       Payment payment= new Payment();
                       PaymentMethod payMethod= new PaymentMethod();
                       Register register= new Register();
                       CustomerClass customer= new CustomerClass();
                       DeliveryClass delivery= new DeliveryClass();
                       Cart cart = new Cart();
                       OrderDetail orderDetail= new OrderDetail();
                       OrderCustomerDetail orderCustomerDetail= new OrderCustomerDetail();
                       CancelOrder cancel= new CancelOrder();

                       orderCustomerDetail.CustomerDetail(customer);
                       String OrderId=null;
                       order.autoCreateOrderId(order,OrderId);
                       cart.setCartId("b@gmail.com");
                       orderDetail.setCart(cart);
                       orderDetail.cartList(cart,dbComputerShop.getCon());
                       customer.setStates("Johor");
                       delivery.setStates("Johor");
                       order.CalSubTotal(0, cart, order, customer, delivery, cart.toString(dbComputerShop.getCon()));
                       Confirm();

                   }
                   }else if (choose == 2) {
                       Staff staff = new Staff();
                       Purchase pch = new Purchase();

                       StaffClass sc = new StaffClass();
                       myConObj = null;
                         try{
                            myConObj = DriverManager.getConnection("jdbc:derby://localhost:1527/test", "ngphengloong", "123");
                       }catch (SQLException e) {
                            e.printStackTrace();
                       }

                       optional = Staff.mainPage();

                       if(optional ==1){
                           Staff.addStaff();
                       }
                        if (optional == 2){
                            int opt = Staff.subMenu();
                            Staff.printStaffInformation(opt);
                        }
                        if (optional == 3 ){
                            Delivery.delivery();

                        }
                        if(optional == 4){
                            Purchase.purchasesMenu();
                        }
                         if(optional == 5){
                            sc.displayDataStaff();
                        }
                        if (optional == 6) {
                            boolean notExisted = true;
                            int indexOfProduct = 0;
                            boolean insertAgain = true;
                            String productChoice;
                            String operationChoice;
                            Product[] product = new Product[100];

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
                        if(optional == 7){
                           while(true){
                           optional = Login.mainPage();

                           if (optional == 1){
                               Login.DisplayMenu(reg);   
                           }
                           else if(optional == 2){
                               String random = "";
                               reg = Register.CustRegister(random);
                           }
                        }
                          //UpdateStaff.updateLogoutTime(myConObj, StaffID);
                      }
                }
            } 
                
                else if(optional == 2){
                    String random  = "";
                    reg = Register.CustRegister(random);
                }
              }
    }
}
}
                       /UpdateStaff.updateLogoutTime(myConObj, StaffID);
                        