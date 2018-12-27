/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.uni_pannon.mik.controller;

import hu.uni_pannon.mik.db_transactions.DBConnectSingleton;
import static hu.uni_pannon.mik.hash.HashPassword.hashPassword;
import hu.uni_pannon.mik.model.UsersModel;
import hu.uni_pannon.mik.view.RegisterFrame;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author zalchege
 */
public class RegisterController {

    private RegisterFrame registerFrame;
    private UsersModel users;

    public RegisterController(RegisterFrame registerFrame) {
        this.registerFrame = registerFrame;
        users = UsersModel.getInstance();
    }

    public boolean checkUserExists() throws SQLException {
        String query = "SELECT * FROM users WHERE username = '" + users.getUsername() + "'";
        ResultSet rs = DBConnectSingleton.getStatement().executeQuery(query);
        return rs.isBeforeFirst();
    }

    public void showUserExists() throws SQLException {
        JOptionPane.showMessageDialog(new JFrame(), "Entered user already exists in the database!", "Dialog",
                JOptionPane.ERROR_MESSAGE);
    }

    public void insertUserIntoDb() throws SQLException, NoSuchAlgorithmException {
        String query = "INSERT INTO users (firstname, lastname, username, password) "
                + "VALUES('" + users.getFirstname() + "', '" + users.getLastname() + "',"
                + " '" + users.getUsername() + "', '" + hashPassword(users.getPassword()) + "')";
        System.out.println(query);
        DBConnectSingleton.getStatement().executeUpdate(query);
    }

    public boolean isNotFilled() {
        System.out.println();
        return users.getFirstname().isEmpty() || users.getLastname().isEmpty()
                || users.getPassword().isEmpty() || users.getUsername().isEmpty();
    }

    public void showNotFilled() {
        JOptionPane.showMessageDialog(new JFrame(), "Not all of the fields are filled!", "Dialog",
                JOptionPane.ERROR_MESSAGE);
    }

    public void showSuccess() {
        JOptionPane.showMessageDialog(new JFrame(), "Registration was successful, you may log in now.", "Dialog",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public boolean isPasswordSame(String password1, String password2) {
        return password1.equals(password2);
    }

    public void showNotSamePassword() {
        JOptionPane.showMessageDialog(new JFrame(), "Entered passwords don't match!", "Dialog",
                JOptionPane.ERROR_MESSAGE);
    }
}
