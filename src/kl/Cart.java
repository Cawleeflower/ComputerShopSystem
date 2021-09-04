/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 *
 * @author Ng Pheng Loong
 */
public class Cart {
    private static String cartId;
    private ArrayList<String> productName = new ArrayList<String>();
    private ArrayList<Double> totalPrice = new ArrayList<Double>();
    private ArrayList<Integer> quantity = new ArrayList<Integer>();
    private double subTotalPrice;

    public Cart() {
        this("","",0.0,0,0.0);
    }

    public Cart(String cartId, String productName, double totalPrice, int quantity, double subTotalPrice) {
        this.cartId = cartId;
        this.subTotalPrice = subTotalPrice;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public double getSubTotalPrice() {
        return subTotalPrice;
    }

    public void setSubTotalPrice(double subTotalPrice) {
        this.subTotalPrice = subTotalPrice;
    }
    
    public void clearValue(ArrayList<String> productName, ArrayList<Integer> quantity, ArrayList<Double> totalPrice){
        productName.clear();
        quantity.clear();
        totalPrice.clear();
    }
    
    public void addToCart(Connection con, String customerId, String newProductName, double newTotalPrice, int newQuantity) {
        clearValue(this.productName, this.quantity, this.totalPrice);
        ArrayList<String> testPrice = new ArrayList<String>();
        ArrayList<String> testQuantity = new ArrayList<String>();
        String[] strSplit;
        double[] dblSplit;
        int[] intSplit;
        int index = 0;
        String stringProductName;
        String stringTotalPrice;
        String stringQuantity;
        String filterColumn = "ProductName";
        boolean sameProduct = false;
        boolean existingRow = false;
        try {
            ResultSet rs = getCartData(con,customerId);
            ResultSetMetaData rsMetaData = getCartMetaData(rs);

            int count = rsMetaData.getColumnCount();
            
            while (rs.next()) {
                for (int i = 1; i<count; i++) {
                    String columnValue = rs.getString(i);
                    if (rsMetaData.getColumnName(i).equals("ProductName")) {
                        System.out.println("Enters product name");
                        if (columnValue.toLowerCase().contains(newProductName.toLowerCase())) {
                            sameProduct =  true;
                            strSplit = columnValue.split(",");
                            ArrayList<String> strList = new ArrayList<String>(Arrays.asList(strSplit));
                            index = strList.indexOf(newProductName) + 1;
                            this.productName.add(columnValue);
                        }
                            
                        else {
                            this.productName.add(columnValue + "," + newProductName);
                        } 
                            
                    } else if (rsMetaData.getColumnName(i).equals("TotalPrice")) {
                        if (sameProduct) {
                            strSplit = columnValue.split(",");
                            dblSplit = Arrays.stream(strSplit).mapToDouble(Double::parseDouble).toArray();
                            ArrayList<String> strList = new ArrayList<String>(Arrays.asList(strSplit));
                            index = strList.indexOf(newProductName) + 1;
                            dblSplit[index] = dblSplit[index] + newTotalPrice;
                            for (double s : dblSplit) {
                                testPrice.add(String.valueOf(s));
                            }
                        }
                            
                        else
                            testPrice.add(columnValue + "," + String.valueOf(newTotalPrice));
                    } else if (rsMetaData.getColumnName(i).equals("Quantity")) {
                        if (sameProduct) {
                            strSplit = columnValue.split(",");
                            intSplit = Arrays.stream(strSplit).mapToInt(Integer::parseInt).toArray();
                            ArrayList<String> strList = new ArrayList<String>(Arrays.asList(strSplit));
                            index = strList.indexOf(newProductName) + 1;
                            intSplit[index] = intSplit[index] + newQuantity;
                            for (int s : intSplit) {
                                testQuantity.add(String.valueOf(s));
                            }
                        }
                            
                        else
                            testQuantity.add(columnValue + "," + String.valueOf(newQuantity));
                    }
                }
                existingRow = true;
            }
            if (existingRow) {
                stringProductName = String.join(",", this.productName);
                stringTotalPrice = testPrice.stream().map(Object::toString).collect(Collectors.joining(","));
                stringQuantity = testQuantity.stream().map(Object::toString).collect(Collectors.joining(","));
                String updateSql = "UPDATE Cart SET ProductName='" + stringProductName + "', TotalPrice='" + stringTotalPrice + "',Quantity='" + stringQuantity + "', SubTotalPrice='" + subTotalPrice + "' WHERE cartId='" + customerId +"'";
                Statement updateStmt = con.createStatement();
                updateStmt.execute(updateSql);
            } else {
                this.productName.add(newProductName);
                this.totalPrice.add(newTotalPrice);
                this.quantity.add(newQuantity);
                stringProductName = String.join(",", this.productName);
                stringTotalPrice = totalPrice.stream().map(Object::toString).collect(Collectors.joining(","));
                stringQuantity = quantity.stream().map(Object::toString).collect(Collectors.joining(","));
                String insertNewSql = "INSERT INTO Cart(CartId, ProductName, TotalPrice, Quantity, SubTotalPrice)" + "VALUES (?,?,?,?,?)";
                Statement insertStmt = con.createStatement();
                PreparedStatement preparedStatement = con.prepareStatement(insertNewSql);   
                preparedStatement.setString(1, customerId);
                preparedStatement.setString(2, stringProductName);
                preparedStatement.setString(3, stringTotalPrice);
                preparedStatement.setString(4, stringQuantity);
                preparedStatement.setDouble(5, subTotalPrice);
                preparedStatement.executeUpdate();
            }
 

      } catch(SQLException ex) {
          System.out.println(ex);
      }
    }
    
    public void runUpdateCartQuery(Connection con, ArrayList<String> productNames, ArrayList<String> totalPrices, ArrayList<String> quantities) {
        try {
            String stringProductName = String.join(",", productNames);
            String stringTotalPrice = totalPrices.stream().map(Object::toString).collect(Collectors.joining(","));
            String stringQuantity = quantities.stream().map(Object::toString).collect(Collectors.joining(","));
            String updateSql = "UPDATE Cart SET ProductName='" + stringProductName + "', TotalPrice='" + stringTotalPrice + "',Quantity='" + stringQuantity + "', SubTotalPrice='" + subTotalPrice + "' WHERE cartId='" + customerId +"'";
            Statement updateStmt = con.createStatement();
            updateStmt.execute(updateSql);
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
    public void removeFromCart(Connection con, String customerId, String newProductName) {
            
        
        String replacedString = "";
        StringBuffer sb = new StringBuffer();
        String replacedTotalPrice = "";
        String replacedQuantity = "";
        int index = 0;
        int index1 = 1;
        String[] strSplit;
        try {
           ResultSet rs = getCartData(con,customerId);
           ResultSetMetaData rsMetaData = getCartMetaData(rs);
           int count = rsMetaData.getColumnCount();
           while(rs.next()) {
               for (int i = 1; i<count; i++) {
                String columnValue = rs.getString("ProductName");
                if (rsMetaData.getColumnName(i).equals("ProductName")) {
                    if (columnValue.toLowerCase().contains(newProductName.toLowerCase())) {
                        this.productName.clear();
                        strSplit = columnValue.split(",");
                        ArrayList<String> strList = new ArrayList<String>(Arrays.asList(strSplit));
                        System.out.println(strList);
                        index = strList.indexOf(newProductName);
                        
                        strList.remove(index);
                        System.out.println("Removed strList: " + strList);
                        for (String s : strList) {
                            if (index1 == strList.size())
                                sb.append(s);
                            else {
                                sb.append(s);
                                sb.append(",");
                            }
                        
                        index1 += 1;
                            System.out.println(index1 + strList.size());
                        }
                        replacedString = sb.toString();
                        System.out.println(replacedString);
                    }
                    else if (rsMetaData.getColumnName(i).equals("TotalPrice")) {

                        this.totalPrice.clear();
                        strSplit = columnValue.split(",");
                        ArrayList<String> strList = new ArrayList<String>(Arrays.asList(strSplit));
                        strList.remove(index);
                        replacedTotalPrice = String.join(", ", strList);
                        System.out.println(replacedTotalPrice);
                    }else if (rsMetaData.getColumnName(i).equals("Quantity")) {
                            this.quantity.clear();
                            strSplit = columnValue.split(",");
                            ArrayList<String> strList = new ArrayList<String>(Arrays.asList(strSplit));
                            strList.remove(index);
                            replacedQuantity = String.join(", ", strList);
                        }
               }
            } 
               String stringTotalPrice = totalPrice.stream().map(Object::toString).collect(Collectors.joining(", "));
               String stringQuantity = quantity.stream().map(Object::toString).collect(Collectors.joining(", "));
               String deleteSql = "UPDATE Cart SET ProductName='" + replacedString + "', TotalPrice='" + replacedTotalPrice + "', Quantity='" + replacedQuantity + "'" + "WHERE CartId='" + customerId + "'";
               Statement stmt = con.createStatement();
               stmt.execute(deleteSql);
            }
          
        } catch(SQLException ex) {
                System.out.println(ex);
            }
    }
    
    public ResultSetMetaData getCartMetaData(ResultSet rs) {
        try {
            ResultSetMetaData rsMetaData = rs.getMetaData();
            
            return rsMetaData;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
    
    public ResultSet getCartData(Connection con, String customerId) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Cart where CartId='" + customerId + "'");
            
            return rs;
        } catch(SQLException ex) {
            System.out.println(ex);
        }
       return null;
    }
    
    public void checkout() {
        
    }
    
    public void removeAllFromCart() {
        this.productName.clear();
        this.totalPrice.clear();
        this.quantity.clear();
    }
    
    public void changeQuantity(String productName, int newQuantity) {
        int index = this.productName.indexOf(productName);
        this.quantity.set(index, newQuantity);
    }

    @Override
    public String toString() {
        return "CartId=" + cartId + "\nproductName=" + productName + "\ntotalPrice=" + totalPrice + "\nquantity=" + quantity + "\nsubTotalPrice=" + subTotalPrice;
    }
    
}