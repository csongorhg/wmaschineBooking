/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.uni_pannon.mik.controller;

import hu.uni_pannon.mik.db_transactions.DBConnectSingleton;
import static hu.uni_pannon.mik.hash.HashPassword.hashPassword;
import hu.uni_pannon.mik.model.WmachinesModel;
import hu.uni_pannon.mik.view.AddWasherFrame;
import hu.uni_pannon.mik.view.MenuFrame;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Vince
 */
public class AddWasherController {

    private AddWasherFrame addFrame;
    private MenuFrame menuFrame;

    public AddWasherController(AddWasherFrame addFrame, MenuFrame menuFrame) {
        this.addFrame = addFrame;
        this.menuFrame = menuFrame;
    }

    public void showNameErrorDialog() {
        JOptionPane.showMessageDialog(new JFrame(), "Name is not filled!", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void showDescriptionErrorDialog() {
        JOptionPane.showMessageDialog(new JFrame(), "Description is not filled!", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void showInvalidNameDialog() {
        JOptionPane.showMessageDialog(new JFrame(), "This washer already exists", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public boolean checkInsertWmachine() throws SQLException {
        WmachinesModel wmachine = addFrame.getWmachinesModel();

        String query = "SELECT * FROM wmachines WHERE name = '" + wmachine.getName() + "'";
        System.out.println(query);
        ResultSet result = DBConnectSingleton.getStatement().executeQuery(query);
        return result.isBeforeFirst();
    }

    public void insertWMachineIntoDb() throws SQLException, NoSuchAlgorithmException {
        WmachinesModel wmachine = addFrame.getWmachinesModel();

        String query = "INSERT INTO wmachines (name, description) "
                + "VALUES('" + wmachine.getName() + "', '" + wmachine.getDescription() + "')";
        System.out.println(query);
        DBConnectSingleton.getStatement().executeUpdate(query);
    }

    public void showSuccessfulDialog() {
        JOptionPane.showMessageDialog(new JFrame(), "Washer added succesful", "Succesful", JOptionPane.INFORMATION_MESSAGE);
    }

}
