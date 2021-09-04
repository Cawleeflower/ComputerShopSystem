/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;

import java.util.HashSet;
import java.util.Random;

/**
 *
 * @author User
 */
public abstract class PurchasesIn {
    protected static String purchasesID;
    protected String vendor;

    protected  PurchasesIn() {
        this("","");
    }
     protected  PurchasesIn(String purchasesID, String vendor) {

        this.purchasesID = purchasesID;
        this.vendor = vendor;
    }
    public String getPurchasesID() {
        return purchasesID;
    }

    public void setPurchasesID(String purchasesID) {
        this.purchasesID = purchasesID;
    }

    public String getWarehouses() {
        return vendor;
    }

    public void setWarehouses(String vendor) {
        this.vendor = vendor;
    }

   public abstract double calculateTotal(double TotalPrice);
       
   public static void createID(){
        Random rd=new Random();
                        HashSet<Integer> set= new HashSet<Integer>();
                        while(set.size()<1)
                        {
                            int ran=rd.nextInt(9999)+100000;
                            set.add(ran);
                        }
                        int len = 6;
                        String random=String.valueOf(len);
                        for(int  random1:set)
                        {
                            System.out.println(random1);
                            random=Integer.toString(random1);
                            String ramdom1 = PurchasesIn.purchasesID;
                            System.out.println("===============================");
                        }
   }
    
    
}
