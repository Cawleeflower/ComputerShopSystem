/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ng Pheng Loong
 */
public class Database {
    private Connection con;
    
    public Database() {
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/test", "ngphengloong", "123");
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        
    }

    public Connection getCon() {
        return con;
    }
    
    
}
