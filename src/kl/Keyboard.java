/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kl;

import java.util.Scanner;

/**
 *
 * @author Ng Pheng Loong
 */
public class Keyboard extends Product{
    private String gamingFocused;
    private String connectionType;

    public Keyboard() {
        this("","","","",0.00,"","");
    }

    public Keyboard(String gamingFocused, String connectionType, String productName, String warranty, double price, String dateOfManufacture, String origin) {
        super(productName, warranty, price, dateOfManufacture, origin);
        this.gamingFocused = gamingFocused;
        this.connectionType = connectionType;
    }

    public String getGamingFocused() {
        return gamingFocused;
    }

    public void setGamingFocused(String gamingFocused) {
        this.gamingFocused = gamingFocused;
    }

    public String getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }
    
    public void getProductDetailsFromUser(Keyboard product) {
        
        Scanner scan = new Scanner(System.in);
        Scanner scan1 = new Scanner(System.in);
        
        super.getProductDetailsFromUser(product);
        
        //Get and as input and set product name
        System.out.println("Please enter whether keyboard is gaming focused");
        String gamingFocused = scan.nextLine();
        product.setProductName(gamingFocused);
        
        //Get and as input and set product detail
        System.out.println("Please enter connectionType (Wireless/Wired)");
        String connectionType = scan.nextLine();
        product.setWarranty(connectionType);
        
        
    }
    
}
