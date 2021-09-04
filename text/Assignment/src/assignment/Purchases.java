/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;
   import java.util.Scanner;
/**
 *
 * @author User
 */
public class Purchases {
    
       public static void purchasesInsert(int opt){
      
       PurchasingClass ps = new PurchasingClass();
       Scanner s1 = new Scanner(System.in); 
       Scanner s2 = new Scanner(System.in); 
       
       if (opt ==1){
           purchaseDesktop();
       }
       if (opt ==2){
          purchasesMotherboard();
       }
       if(opt ==3){
        purchasesComponent();
           
       }
       
       //for (int i = 0 ; i<; i++){
           
       //}
            System.out.println("");
            System.out.println("");
            System.out.println("Total of Purchases : RM " + PurchasingClass.getTotalPrice());
            System.out.println("Services Tax       : RM " + PurchasingClass.getServicesTax());
            System.out.println("Total              : RM " + ps.calculateTotal(PurchasingClass.getTotalPrice()));
            System.out.println("");
            
            Purchases.purchasesMenu();
      }
   
    public static int purchaseDesktop(){
        Scanner s1 = new Scanner(System.in); 
        int optiona = 0;
            System.out.println("***************************************************************");
            System.out.println("*                    1 4:3 Screen - RM 100                    *");
            System.out.println("*                    2 16:9 Screen - RM 200                   *");
            System.out.println("*                    3 21:9 Screen - RM 400                   *");
            System.out.println("***************************************************************");
            System.out.println("Please Enter Your Option(1,2,3) : ");
            int pd = s1.nextInt();
            System.out.println("");
             if(pd == 1){
                     Purchases.EnterPriceDesktop();
                }
                  if(pd == 2){
                     Purchases.EnterPriceDesktop();
                }
                    if(pd == 3){
                     Purchases.EnterPriceDesktop();
                }
            return optiona;
       }
       
    public static int purchasesMotherboard(){
        Scanner s1 = new Scanner(System.in); 
        int option = 0;
                System.out.println("***************************************************************");
                System.out.println("*                    1 Standard-ATX - RM 125                  *");
                System.out.println("*                    2 Mirco-ATX - RM 100                     *");
                System.out.println("*                    3 Mini-TX - RM 85                        *");
                System.out.println("***************************************************************");
                System.out.println("Please Enter Your Option(1,2,3) : ");
                int pm = s1.nextInt();
                System.out.println("");
                if(pm == 1){
                     Purchases.EnterPriceMotherboard();
                }
                  if(pm == 2){
                     Purchases.EnterPriceMotherboard();
                }
                    if(pm == 3){
                     Purchases.EnterPriceMotherboard();
                }
                return option;       
    }
    public static int purchasesComponent(){
       Scanner s1 = new Scanner(System.in); 
       //Scanner s2 = new Scanner(System.in);
       int selected = 0; 
                System.out.println("***************************************************************");
                System.out.println("*                    1 CPU                                    *");
                System.out.println("*                    2 HDD                                    *");
                System.out.println("*                    3 SSD                                    *");
                System.out.println("***************************************************************");
                System.out.println("Please Enter Your Option(1,2,3) : ");
                selected = s1.nextInt();
                System.out.println("");
               
                
                if ( selected == 1){
                    System.out.println("***************************************************************");
                    System.out.println("*                    1 CPU - Intel i5 - RM 350                *");
                    System.out.println("*                    2 CPU - Intel i7 - RM 450                *");
                    System.out.println("*                    3 CPU - AMD RYZEN 5 - RM 380             *");
                    System.out.println("*                    4 CPU - AMD RYZEN 7 - RM 480             *");
                    System.out.println("***************************************************************");
                    System.out.println("Please Enter Your Option(1,2,3,4) : ");
                    int opt = s1.nextInt();
                    System.out.println("");
                     if(opt == 1){
                         Purchases.EnterPrice();
                     }  
                      if(opt == 2){
                       Purchases.EnterPrice();
                     }
                       if(opt == 3){
                        Purchases.EnterPrice();
                     } 
                       if(opt == 4){
                        Purchases.EnterPrice();
                     }
                }
                if( selected == 2){
                    System.out.println("***************************************************************");
                    System.out.println("*                    1 HDD - 4RAM - RM 200                    *");
                    System.out.println("*                    2 HDD - 8RAM - RM 400                    *");
                    System.out.println("***************************************************************");
                    System.out.println("Please Enter Your Option(1,2) : ");
                    int numberHddType = s1.nextInt();
                     if( numberHddType == 1){
                       Purchases.EnterPrice();
                     }
                     if( numberHddType == 2){
                       Purchases.EnterPrice();
                     }
                     if( numberHddType == 2){
                         Purchases.EnterPrice();
                     }
                }
                if( selected == 3){
                    System.out.println("***************************************************************");
                    System.out.println("*                    1 SSD - 4RAM - RM 300                    *");
                    System.out.println("*                    2 SSD - 8RAM - RM 400                    *");
                    System.out.println("***************************************************************");
                    System.out.println("Please Enter Your Option(1,2) : ");
                    int numberSsdType = s1.nextInt();
                    System.out.println("");
                     if( numberSsdType == 1){
                       Purchases.EnterPrice();
                     }
                    if( numberSsdType == 2){
                       Purchases.EnterPrice();
                }
                }
                  return selected;
    }
    public static int purchasesMenu(){
       Scanner s1 = new Scanner(System.in); 
       int option = 0;
       System.out.println("***************************************************************");
       System.out.println("*                    1 Purchasing                             *");
       System.out.println("*                    2 Warehouses Stock                       *");
       System.out.println("*                    3 Back To Main Menu                      *");
       System.out.println("***************************************************************");
       System.out.println("Please Enter Your Option(1,2,3) : ");
       option = s1.nextInt();
       System.out.println("");
       
       if(option ==1){
        System.out.println("***************************************************************");
        System.out.println("*                    1 Desktop screen                         *");
        System.out.println("*                    2 Motherboard                            *");
        System.out.println("*                    3 Component                              *");
        System.out.println("***************************************************************");
        System.out.println("Please Enter Your Option(1,2,3) : ");
        int opt = s1.nextInt();
        Purchases.purchasesInsert(opt);
        System.out.println("");
    }
       if(option ==2){
        System.out.println("***************************************************************");
        System.out.println("*                    1 Total of product                       *"); 
        System.out.println("*                    2 Back                                   *");
        System.out.println("***************************************************************");
        System.out.println("Please Enter Your Option(1,2) : ");
        int opt = s1.nextInt();
        System.out.println("");
        
       }
       if(option ==3){
           Staff.printStaffInformation(option);
       }
       return option;
 }
    public static void EnterPrice(){
        Scanner s1 = new Scanner(System.in); 
        Scanner s2 = new Scanner(System.in);
           System.out.println("Enter the Product Name :");
           String name = s2.next();
           System.out.println("Please enter the price :");
           int prices = s1.nextInt(); 
           System.out.println("Please enter quantity :");
           int quantity = s1.nextInt();
           PurchasingClass.addTotalPrice(prices, quantity);
           System.out.println("Did you want to continue purchases : Yes/No");
           String comfirm = s1.next();
           comfirm.toLowerCase();
           if(  comfirm.toLowerCase().equals("yes")){
                 System.out.println("***************************************************************");
                System.out.println("*                    1 Desktop screen                         *");
                System.out.println("*                    2 Motherboard                            *");
                System.out.println("*                    3 Component                              *");
                System.out.println("***************************************************************");
                  System.out.println("Please enter the purchase item");
                  System.out.println("");
                  int item = s1.nextInt();
                  if(item == 1){
                      Purchases.purchaseDesktop();
                  }
                  if (item == 2){
                      Purchases.purchasesMotherboard();
                  } 
                  if (item == 2){
                      Purchases.purchasesComponent();
                  }
           }
           else{
                           
           }
           }
    public static void EnterPriceDesktop(){
         Scanner s1 = new Scanner(System.in);
         Scanner s2 = new Scanner(System.in);
          System.out.println("Enter the Product Name :");
           String name = s2.next();
           System.out.println("Please enter the price :");
           int prices = s1.nextInt(); 
           System.out.println("Please enter quantity :");
           int quantity = s1.nextInt();
           PurchasingClass.addTotalPrice(prices, quantity);
           System.out.println("Did you want to continue purchases : Yes/No");
           String comfirm = s1.next();
           comfirm.toLowerCase();
           if(  comfirm.toLowerCase().equals("yes")){
                   System.out.println("***************************************************************");
                System.out.println("*                    1 Desktop screen                         *");
                System.out.println("*                    2 Motherboard                            *");
                System.out.println("*                    3 Component                              *");
                System.out.println("***************************************************************");
                  System.out.println("Please enter the purchase item");
                  System.out.println("");
                  int item = s1.nextInt();
                  if(item == 1){
                      Purchases.purchaseDesktop();
                  }
                  if (item == 2){
                      Purchases.purchasesMotherboard();
                  } 
                  if (item == 2){
                      Purchases.purchasesComponent();
                  }
           }
           else{
                        
           }
           }
     public static void EnterPriceMotherboard(){
          Scanner s1 = new Scanner(System.in); 
          Scanner s2 = new Scanner(System.in);
           System.out.println("Enter the Product Name :");
           String name = s2.next();
           System.out.println("Please enter the price :");
           int prices = s1.nextInt(); 
           System.out.println("Please enter quantity :");
           int quantity = s1.nextInt();
           PurchasingClass.addTotalPrice(prices, quantity);
           System.out.println("Did you want to continue purchases : Yes/No");
           String comfirm = s1.next();
           comfirm.toLowerCase();
           if(  comfirm.toLowerCase().equals("yes")){
                System.out.println("***************************************************************");
                System.out.println("*                    1 Desktop screen                         *");
                System.out.println("*                    2 Motherboard                            *");
                System.out.println("*                    3 Component                              *");
                System.out.println("***************************************************************");
                  System.out.println("Please enter the purchase item");
                  System.out.println("");
                  int item = s1.nextInt();
                  if(item == 1){
                      Purchases.purchaseDesktop();
                  }
                  if (item == 2){
                      Purchases.purchasesMotherboard();
                  } 
                  if (item == 2){
                      Purchases.purchasesComponent();
                  }
                  
           }
           else{
                 
           }
           }
    }
   
    



    

