/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.uni_pannon.mik.controller;

import hu.uni_pannon.mik.db_transactions.DBConnectSingleton;
import hu.uni_pannon.mik.model.WmachinesModel;
import hu.uni_pannon.mik.view.AddWasherFrame;
import hu.uni_pannon.mik.view.MenuFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author zalchege
 */
public class WasherPickerController {

    private DefaultTableModel tableModel;
    private MenuFrame menuFrame;
    private AddWasherFrame addWasherFrame;
    private String selectedWasher;

    public WasherPickerController(DefaultTableModel tableModel, MenuFrame menuFrame) {
        this.tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }

        };

        this.tableModel.addColumn("Waschmaschines");

        this.menuFrame = menuFrame;

        this.addWasherFrame = new AddWasherFrame();
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }



    

    public void addListener() {
        this.menuFrame.getWmTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                if (e.getClickCount() == 1) {
                    final JTable jTable = (JTable) e.getSource();
                    final int row = jTable.getSelectedRow();
                    final int column = jTable.getSelectedColumn();
                    final String valueInCell = (String) jTable.getValueAt(row, column);
                    setSelectedWasher(valueInCell);
                    
                }
            }
        });
    }


    public void setSelectedWasher(String selectedWasher) {
        this.selectedWasher = selectedWasher;
    }
    
    public String getSelectedWasher() {
        return this.selectedWasher;
    }
    


}
