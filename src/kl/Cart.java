/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kl;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import wagu.Board;
import wagu.Table;

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
   
    
    public void addToCart(Connection con, String newProductName, int newQuantity) {
        clearValue(this.productName, this.quantity, this.totalPrice);
        ArrayList<String> testPrice = new ArrayList<String>();
        ArrayList<String> testQuantity = new ArrayList<String>();
        double productPrice;
        int index = 0;
        String[] strSplit;
        double[] dblSplit;
        int[] intSplit;
        String stringProductName;
        String stringTotalPrice;
        String stringQuantity = "";
        String filterColumn = "ProductName";
        boolean sameProduct = false;
        boolean existingRow = false;
        productPrice = getProductPrice(con, newProductName) * newQuantity;
        try {
            ResultSet rs = getCartData(con);
            ResultSetMetaData rsMetaData = getCartMetaData(rs);

            int count = rsMetaData.getColumnCount();
            
            while (rs.next()) {
                System.out.println("Entered Product");
                for (int i = 1; i<count; i++) {
                    System.out.println("Counting");
                    String columnValue = rs.getString(i);
                    if (rsMetaData.getColumnName(i).toLowerCase().equals("productname")) {
                        System.out.println("Entered ProductName");
                        if (columnValue.toLowerCase().contains(newProductName.toLowerCase())) {
                            sameProduct =  true;
                            index = getProductPositionInCart(columnValue,newProductName);
                            appendCartProductNames(columnValue, index, newProductName);
                        }
                            
                        else {
                            this.productName.add(columnValue + "," + newProductName);
                        } 
                            
                    } else if (rsMetaData.getColumnName(i).toLowerCase().equals("totalprice")) {
                        System.out.println("SameProduct: " + sameProduct + "TestPrice Before:" + testPrice + "Index:" + index);
                        if (sameProduct) {
                            testPrice = updateCartPrice(columnValue, index, productPrice);
                            System.out.println("TestPrice Before:" + testPrice + "ProductPrice: " + productPrice);
                        }
                            
                        else {
                            strSplit = columnValue.split(",");
                            dblSplit = Arrays.stream(strSplit).mapToDouble(Double::parseDouble).toArray();
                            for (double s : dblSplit) {
                                this.totalPrice.add(s);
                            }
                            this.totalPrice.add(productPrice);
                            testPrice.add(columnValue + "," + String.valueOf(productPrice));
                        }
                            
                    } else if (rsMetaData.getColumnName(i).toLowerCase().equals("quantity")) {
                        if (sameProduct) {
                            testQuantity = updateCartQuantity(columnValue, index, newQuantity);
                        }
                            
                        else {
                            testQuantity.add(columnValue + "," + String.valueOf(newQuantity));
                        }
                    }
                }
                existingRow = true;
            }
            
            calculateSubTotalPrice();
            if (existingRow) {
                
                stringProductName = String.join(",", productName);
                stringTotalPrice = testPrice.stream().map(Object::toString).collect(Collectors.joining(","));
                stringQuantity = testQuantity.stream().map(Object::toString).collect(Collectors.joining(","));
                runUpdateCartQuery(con, stringProductName, stringTotalPrice, stringQuantity);
            } else {
                setSubTotalPrice(productPrice);
                runInsertCartQuery(con, newProductName, productPrice, newQuantity);
            }
 

      } catch(SQLException ex) {
          System.out.println(ex);
      }
    }
    
    public void removeFromCart(Connection con, String newProductName) {
            
        
        String replacedString = "";
        String replacedTotalPrice = "";
        String replacedQuantity = "";
        boolean productExists = false;
        int index = 0;
        try {
           ResultSet rs = getCartData(con);
           ResultSetMetaData rsMetaData = getCartMetaData(rs);
           int count = rsMetaData.getColumnCount();
           while(rs.next()) {
               for (int i = 1; i<count; i++) {
                String columnValue = rs.getString(i);
                if (rsMetaData.getColumnName(i).toLowerCase().equals("productname")) {
                    if (columnValue.toLowerCase().contains(newProductName.toLowerCase())) {
                        productExists = true;
                        this.productName.clear();
                        index = getProductPositionInCart(columnValue, newProductName);
                        
                        replacedString = removeProductNameFromCart(columnValue, index);
                    } else
                        System.out.println("Product does not exist in this cart!");
                        break;
                    
               }else if (rsMetaData.getColumnName(i).toLowerCase().equals("totalprice")) {

                        this.totalPrice.clear();
                        replacedTotalPrice = removePriceFromCart(columnValue, index);
                        calculateSubTotalPrice();
                        
               }else if (rsMetaData.getColumnName(i).toLowerCase().equals("quantity")) {
                   this.quantity.clear();
                   replacedQuantity = removeQuantityFromCart(columnValue, index);
               }
            } 
            }
           if (productExists) 
            runUpdateCartQuery(con, replacedString, replacedTotalPrice, replacedQuantity);
          
        } catch(SQLException ex) {
                System.out.println(ex);
            }
    }
    
    public void removeAllFromCart(Connection con) {
        String updateSql = "UPDATE Cart SET ProductName='', TotalPrice='', Quantity='' WHERE CartId='" + this.cartId + "'";
        try {
            Statement stmt = con.createStatement();
            stmt.execute(updateSql);
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        
    }
    
    public void changeQuantity(Connection con, String productName, int newQuantity) {
        clearValue(this.productName, this.quantity, this.totalPrice);
        int index = 0;
        ArrayList<String> testQuantity = new ArrayList<String>();
        ArrayList<String> testTotalPrice = new ArrayList<String>();
        double totalPrice;
        String stringQuantity;
        String stringTotalPrice;
        String[] strSplit;
        double[] dblSplit;
        int[] intSplit;
        try {
            ResultSet rs = getCartData(con);
            ResultSetMetaData rsMetaData = getCartMetaData(rs);

            int count = rsMetaData.getColumnCount();
            
            while (rs.next()) {
                for (int i = 1; i<count; i++) {
                    String columnValue = rs.getString(i);
                    if (rsMetaData.getColumnName(i).equals("ProductName")) {
                        if (columnValue.toLowerCase().contains(productName.toLowerCase())) {
                            index = getProductPositionInCart(columnValue,productName);
                            if (index == -1) {
                                System.out.println("stopped loop");
                                break;
                            }
                        }
                            
                    } else if (rsMetaData.getColumnName(i).equals("TotalPrice")) {
                        System.out.println("Run loop?");
                        totalPrice = getProductPrice(con, productName);
                        strSplit = columnValue.split(",");
                        dblSplit = Arrays.stream(strSplit).mapToDouble(Double::parseDouble).toArray();
                        dblSplit[index] = totalPrice;
                        for (double s : dblSplit) {
                            testTotalPrice.add(String.valueOf(s));
                            this.totalPrice.add(s);
                        }
                        
                        
                    } else if (rsMetaData.getColumnName(i).equals("Quantity")) {
                        
                        strSplit = columnValue.split(",");
                        intSplit = Arrays.stream(strSplit).mapToInt(Integer::parseInt).toArray();
                        intSplit[index] = newQuantity;
                        for (int s : intSplit) {
                            testQuantity.add(String.valueOf(s));
                            this.quantity.add(s);
                        }
                    }
                }
            }
            
            calculateSubTotalPrice();
            
            stringTotalPrice = testTotalPrice.stream().map(Object::toString).collect(Collectors.joining(","));
            stringQuantity = testQuantity.stream().map(Object::toString).collect(Collectors.joining(","));
            String updateSql = "UPDATE Cart SET Quantity='" + stringQuantity + ", TotalPrice='" + stringTotalPrice + "'" + ", SubTotalPrice=" + this.subTotalPrice + " WHERE CartId='" + this.cartId + "'";
            Statement stmt = con.createStatement();
            stmt.execute(updateSql);
 

      } catch(SQLException ex) {
          System.out.println(ex);
      }
    }
    
    public void calculateSubTotalPrice() {
        this.subTotalPrice = 0;
        for (double s : this.totalPrice) {
            this.subTotalPrice += s;
        }
    }
    
    public double getProductPrice(Connection con, String productName) {
        String getSql = "SELECT Price From Product WHERE ProductName='" + productName + "'";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(getSql);
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0.0;
    }
    
    public void appendCartProductNames(String columnValue, int index, String newProductName) {
        String[] strSplit;
        strSplit = columnValue.split(",");
        ArrayList<String> strList = new ArrayList<String>(Arrays.asList(strSplit));
        index = getProductPositionInCart(columnValue,newProductName);
        this.productName.add(columnValue);
    }
    
    public int getProductPositionInCart(String columnValue, String newProductName) {
        String[] strSplit;
        int index;
        strSplit = columnValue.split(",");
        ArrayList<String> strList = new ArrayList<String>(Arrays.asList(strSplit));
        if (strList.contains(newProductName)) {
            System.out.println("Entered");
            index = strList.indexOf(newProductName);
            return index;
        }
        return -1;
    }
    
    public ArrayList<String> updateCartPrice(String columnValue, int index, double newTotalPrice) {
        String[] strSplit;
        double[] dblSplit;
        ArrayList<String> testPrice = new ArrayList<String>();
        strSplit = columnValue.split(",");
        dblSplit = Arrays.stream(strSplit).mapToDouble(Double::parseDouble).toArray();
        dblSplit[index] = dblSplit[index] + newTotalPrice;
        for (double s : dblSplit) {
            this.totalPrice.add(s);
            testPrice.add(String.valueOf(s));
        }
        return testPrice;
    }
    
    public ArrayList<String> updateCartQuantity(String columnValue, int index, int newQuantity) {
        String[] strSplit;
        int[] intSplit;
        ArrayList<String> testQuantity = new ArrayList<String>();
        strSplit = columnValue.split(",");
        intSplit = Arrays.stream(strSplit).mapToInt(Integer::parseInt).toArray();
        intSplit[index] = intSplit[index] + newQuantity;
        System.out.println(testQuantity);
        for (int s : intSplit) {
            testQuantity.add(String.valueOf(s));
            this.quantity.add(s);
        }
        System.out.println(testQuantity);
        return testQuantity;
    }
    
    public void runUpdateCartQuery(Connection con, String productNames, String totalPrices, String quantities) {
        try {
            String updateSql = "UPDATE Cart SET ProductName='" + productNames + "', TotalPrice='" + totalPrices + "',Quantity='" + quantities + "', SubTotalPrice='" + subTotalPrice + "' WHERE cartId='" + this.cartId +"'";
            Statement updateStmt = con.createStatement();
            updateStmt.execute(updateSql);
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
    public void runInsertCartQuery(Connection con, String newProductName, double newTotalPrice, int newQuantity) {
        this.productName.add(newProductName);
        this.totalPrice.add(newTotalPrice);
        this.quantity.add(newQuantity);
        String stringProductName;
        String stringTotalPrice;
        String stringQuantity; 
        stringProductName = String.join(",", this.productName);
        stringTotalPrice = totalPrice.stream().map(Object::toString).collect(Collectors.joining(","));
        stringQuantity = quantity.stream().map(Object::toString).collect(Collectors.joining(","));
        String insertNewSql = "INSERT INTO Cart(CartId, ProductName, TotalPrice, Quantity, SubTotalPrice)" + "VALUES (?,?,?,?,?)";
        try {
        Statement insertStmt = con.createStatement();
        PreparedStatement preparedStatement = con.prepareStatement(insertNewSql);   
        preparedStatement.setString(1, this.cartId);
        preparedStatement.setString(2, stringProductName);
        preparedStatement.setString(3, stringTotalPrice);
        preparedStatement.setString(4, stringQuantity);
        preparedStatement.setDouble(5, subTotalPrice);
        preparedStatement.executeUpdate();   
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public String removeProductNameFromCart(String columnValue, int index) {
        String[] strSplit;
        strSplit = columnValue.split(",");
        int index1 = 1;
        StringBuffer sb = new StringBuffer();
        String replacedString = "";
        ArrayList<String> strList = new ArrayList<String>(Arrays.asList(strSplit));
        strList.remove(index);
        for (String s : strList) {
            if (index1 == strList.size())
                 sb.append(s);
            else {
                sb.append(s);
                sb.append(",");
            }
            
        index1 += 1;
        }
        return sb.toString();
    }
    
    public String removePriceFromCart(String columnValue, int index) {
        String[] strSplit;
        strSplit = columnValue.split(",");
        ArrayList<String> strList = new ArrayList<String>(Arrays.asList(strSplit));
        strList.remove(index);
        for (String s : strList) {
            this.totalPrice.add(Double.parseDouble(s));
        }
        return String.join(",", strList);
    }
    
    public String removeQuantityFromCart(String columnValue, int index) {
        String[] strSplit;
        strSplit = columnValue.split(",");
        ArrayList<String> strList = new ArrayList<String>(Arrays.asList(strSplit));
        strList.remove(index);
        return String.join(",", strList);
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
    
    public ResultSet getCartData(Connection con) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Cart where CartId='" + this.cartId + "'");
            
            return rs;
        } catch(SQLException ex) {
            System.out.println(ex);
        }
       return null;
    }
    
    public String showCartTable(Connection con) {
        String test = "SELECT * FROM Cart WHERE CartId='" + this.cartId + "'";
        String[] cartItem;
        List<String> headersList = Arrays.asList("PRODUCTNAME", "TOTALPRICE", "QUANTITY");
        List<List<String>> rowsLists;
        List<List<String>> rowsList = Arrays.asList();
        List<List<String>> listOfLists = new ArrayList<>();
        List<String> a = new ArrayList<>();
        ArrayList<String> b = new ArrayList<String>();
        ArrayList<String> c = new ArrayList<String>();
        int indexInProduct = 0;
        int indexInDatabaseProduct = 0;
        String[] productAppended = new String[3];
        String[] strSplit;
        String columnValue;
        int[] intSplit;
        double[] doubleSplit;
        
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(test);
            ResultSetMetaData rsmd = rs.getMetaData();
            int count = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i=1;i<count;i++) {
                    columnValue = rs.getString(i);
                    if (rsmd.getColumnName(i).toLowerCase().equals("productname")) {
                        strSplit = columnValue.split(",");
                        for (String s : strSplit) {
                            a.add(strSplit[indexInDatabaseProduct]);
                            indexInDatabaseProduct += 1;
                        }
                        indexInDatabaseProduct = 0;
                    }
                    else if ((rsmd.getColumnName(i).toLowerCase().equals("totalprice"))) {
                        strSplit = columnValue.split(",");
                        doubleSplit = Arrays.stream(strSplit).mapToDouble(Double::parseDouble).toArray();
                        for (String s : strSplit) {
                            b.add(strSplit[indexInDatabaseProduct]);
                            indexInDatabaseProduct += 1;
                        }
                        indexInDatabaseProduct = 0;
                    }
                    else if ((rsmd.getColumnName(i).toLowerCase().equals("quantity"))) {
                        strSplit = columnValue.split(",");
                        intSplit = Arrays.stream(strSplit).mapToInt(Integer::parseInt).toArray();
                        for (String s : strSplit) {
                            c.add(strSplit[indexInDatabaseProduct]);
                            indexInDatabaseProduct += 1;
                        }
                        indexInDatabaseProduct = 0;
                    }
                    
                    Iterator<String> it1 = a.iterator();
                    Iterator<String> it2 = b.iterator();
                    Iterator<String> it3 = c.iterator();
                    while (it1.hasNext() && it2.hasNext() && it3.hasNext()) {
                        List<String> asd = new ArrayList<>();
                        asd.add(it1.next());
                        asd.add(it2.next());
                        asd.add(it3.next());
                        listOfLists.add(asd);
                    }
                }
        }
            
        if (!listOfLists.isEmpty()) {
            Board board = new Board(75);
            String tableString1 = board.setInitialBlock(new Table(board, 75, headersList, listOfLists).tableToBlocks()).build().getPreview();
            return tableString1;
        }
        
    } catch(SQLException ex) {
        ex.printStackTrace();
    }
        return "\nYou have no items in your cart!\n";
    }

    public String toString(Connection con) {
        String test = "SELECT * FROM Cart WHERE CartId='" + this.cartId + "'";
        try {
            Statement stmt = con.createStatement();
            ResultSet set = stmt.executeQuery(test);
            ResultSetMetaData rsmd = set.getMetaData();
            ArrayList<String> a = new ArrayList<String>();
            ArrayList<String> b = new ArrayList<String>();
            ArrayList<String> c = new ArrayList<String>();
            int indexInProduct = 0;
            int indexInDatabaseProduct = 0;
            String[] productAppended = new String[] {};
            String[] strSplit;
            String columnValue;
            int[] intSplit;
            double[] doubleSplit;
            int count = rsmd.getColumnCount();
            if (set.next()) {
                
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return "";
    }
}