/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Boris
 */
public class DBBroker {
    private Connection konekcija;
    private static DBBroker instance;

    private DBBroker() {
        try {
            konekcija=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel","root","");
            konekcija.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    public static DBBroker getInstance() {
        if(instance==null)
            instance=new DBBroker();
        return instance;
    }
    

    public Connection getKonekcija() {
        return konekcija;
    }
    
}
