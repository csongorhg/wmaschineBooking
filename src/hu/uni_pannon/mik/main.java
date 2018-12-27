/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.uni_pannon.mik;

import hu.uni_pannon.mik.db_transactions.DBConnectSingleton;
import static hu.uni_pannon.mik.view.InitJOptionPane.init;
import hu.uni_pannon.mik.view.LoginFrame;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import org.w3c.dom.css.RGBColor;

/**
 *
 * @author zalchege
 */
public class main {

    /**
     * @param args the command line arguments
     */
    InputStream inputStream;

    public static void main(String[] args) {

        DBConnectSingleton dBConnectSingleton = DBConnectSingleton.getInstance();

        dBConnectSingleton.setUsername("washmaschines");
        dBConnectSingleton.setPassword("Hr2KJF2wl?Q?");
        dBConnectSingleton.setDriver("com.mysql.jdbc.Driver");
        dBConnectSingleton.setDb_url("jdbc:mysql://den1.mysql5.gear.host/washmaschines?useSSL=false");

        // Set JOption pane custom properties
        init();

        dBConnectSingleton.start();
        
        try {
            dBConnectSingleton.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        LoginFrame loginForm = new LoginFrame();
        loginForm.setVisible(true);

    }

}
