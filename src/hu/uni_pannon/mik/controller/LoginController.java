/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.uni_pannon.mik.controller;

import hu.uni_pannon.mik.db_transactions.DBConnectSingleton;
import static hu.uni_pannon.mik.hash.HashPassword.hashPassword;
import hu.uni_pannon.mik.model.UsersModel;
import hu.uni_pannon.mik.view.LoginFrame;
import hu.uni_pannon.mik.view.MenuFrame;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author zalchege
 */
public class LoginController {

    private LoginFrame loginForm;
    private UsersModel users;

    public LoginController(LoginFrame loginForm) {
        this.loginForm = loginForm;
        users = UsersModel.getInstance();
    }

    public boolean checkUserExists() throws SQLException, NoSuchAlgorithmException {
        String query = "SELECT * FROM users WHERE username = '" + users.getUsername() + "' "
                + "AND password = '" + hashPassword(users.getPassword()) + "'";
        System.out.println(query);
        ResultSet rs = DBConnectSingleton.getStatement().executeQuery(query);
        return rs.isBeforeFirst();
    }
    
    public void showBadUsernameAndPassword() {
        JOptionPane.showMessageDialog(new JFrame(), "Wrong username and password comibation!", "Dialog",
                        JOptionPane.ERROR_MESSAGE);
    }
    
    public void showMenuFrame() {
        MenuFrame menuFrame = new MenuFrame();
        menuFrame.setVisible(true);
    }
    
    public boolean isNotFilled() {
        return users.getPassword().isEmpty() || users.getUsername().isEmpty();
    }

    public void showNotFilled() {
        JOptionPane.showMessageDialog(new JFrame(), "Input fields are not filled!", "Dialog",
                JOptionPane.ERROR_MESSAGE);
    }

}
