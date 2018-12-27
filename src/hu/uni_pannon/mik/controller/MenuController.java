/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.uni_pannon.mik.controller;

import hu.uni_pannon.mik.db_transactions.DBConnectSingleton;
import hu.uni_pannon.mik.model.BookingModell;
import hu.uni_pannon.mik.model.UsersModel;
import hu.uni_pannon.mik.view.MenuFrame;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author zalchege
 */
public class MenuController {

    private DefaultTableModel tableModel;
    private MenuFrame menuFrame;
    private String[] intervalArray = {"8:00-9:00","9:00-10:00","10:00-11:00","11:00-12:00","12:00-13:00","13:00-14:00","14:00-15:00","15:00-16:00","16:00-17:00","17:00-18:00","18:00-19:00","19:00-20:00","20:00-21:00","21:00-22:00","22:00-23:00"};
    private String[][] data;
    
    
    private ArrayList<BookingModell> bookings;

    public MenuController(DefaultTableModel tableModel, MenuFrame menuFrame) {

        this.tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }
        ;

        };
        initColumns();
        bookings = new ArrayList<>();
        this.menuFrame = menuFrame;

    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public void initColumns() {
        System.out.println(UsersModel.getInstance().getUsername());
        this.tableModel.addColumn("Interval");

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        System.out.println("day: " + dayOfWeek);

        for (int i = 0; i < 7; i++) {
            String day = null;

            switch (dayOfWeek) {
                case 2:
                    day = "Monday";
                    break;
                case 3:
                    day = "Tuesday";
                    break;
                case 4:
                    day = "Wednesday";
                    break;
                case 5:
                    day = "Thursday";
                    break;
                case 6:
                    day = "Friday";
                    break;
                case 7:
                    day = "Saturday";
                    break;
                case 1:
                    day = "Sunday";
                    break;
            }

            this.tableModel.addColumn(day);

            dayOfWeek = dayOfWeek + 1 > 7 ? 1 : dayOfWeek + 1;
        }
    }

    public void fillWashesTable() {
        SwingWorker<Boolean, BookingModell> worker = new SwingWorker<Boolean, BookingModell>() {
            @Override
            protected Boolean doInBackground() throws Exception, SQLException, InterruptedException {
                data = new String[intervalArray.length][8];
                for (int i = 0; i < intervalArray.length; i++) {
                    data[i][0] = intervalArray[i];
                }
                
                String query = "SELECT users.firstname, users.lastname, wmachines.name, DAYOFWEEK(washes.work_date_start), HOUR(washes.work_date_start) AS starthour, "
                        + " washes.work_date_end, HOUR(washes.work_date_end) AS endhour, users.idusers, wmachines.idwmachines, washes.idwashes "
                        + "FROM washes "
                        + "INNER JOIN users ON washes.users_id = users.idusers "
                        + "INNER JOIN wmachines ON washes.wmachines_id = wmachines.idwmachines "
                        + "WHERE work_date_start >= '"+new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(new Date())+" 00:00:00'";

                ResultSet resultSet = DBConnectSingleton.getStatement().executeQuery(query);

                while (resultSet.next()) {
                    Thread.sleep(10);
                    //System.out.println("Reading washes...");
                    
                    String firstName = resultSet.getString(1);
                    String lastName = resultSet.getString(2);
                    String wmName = resultSet.getString(3);
                    int workDateDay = Integer.parseInt(resultSet.getString(4));
                    int startHour = Integer.parseInt(resultSet.getString(5));
                    String workDateEnd = resultSet.getString(6);
                    int endHour = Integer.parseInt(resultSet.getString(7));
                    int userId = Integer.parseInt(resultSet.getString(8));
                    int wmaschineId = Integer.parseInt(resultSet.getString(9));
                    int washId = Integer.parseInt(resultSet.getString(10));
                    Date time = new Date();
                    Calendar c = Calendar.getInstance();
                    c.setTime(time);
                    int righttime = c.get(Calendar.DAY_OF_WEEK);
                    if(menuFrame.getWasherPickerController().getSelectedWasher().equals(wmName))
                        switch (workDateDay) {
                            case 2:
                                //day = "Monday";
                                for(int i = startHour-8; i < endHour-8;i++){
                                    data[i][Math.abs(righttime-workDateDay)+1] = firstName + lastName;
                                }
                                break;
                            case 3:
                                //day = "Tuesday";
                                for(int i = startHour-8; i < endHour-8;i++){
                                    data[i][Math.abs(righttime-workDateDay)+1] = firstName + lastName;
                                }
                                break;
                            case 4:
                                for(int i = startHour-8; i < endHour-8;i++){
                                    data[i][Math.abs(righttime-workDateDay)+1] = firstName + lastName;
                                }
                                break;
                            case 5:
                                for(int i = startHour-8; i < endHour-8;i++){
                                    data[i][Math.abs(righttime-workDateDay)+1] = firstName + lastName;
                                }
                                break;
                            case 6:
                                for(int i = startHour-8; i < endHour-8;i++){
                                    data[i][Math.abs(righttime-workDateDay)+1] = firstName + lastName;
                                }
                                break;
                            case 7:
                                for(int i = startHour-8; i < endHour-8;i++){
                                    data[i][Math.abs(righttime-workDateDay)+1] = firstName + lastName;
                                }
                                break;
                            case 1:
                                for(int i = startHour-8; i < endHour-8;i++){
                                    data[i][Math.abs(righttime-workDateDay)+1] = firstName + lastName;
                                }
                                break;
                        }

                    }
                    
                for (int i = 0; i < data.length; i++) {
                    for (int j = 0; j < data[i].length; j++) {
                        System.out.print(data[i][j] + " ");
                    }
                    System.out.println("");
                }
                    /*BookingModell bm = new BookingModell(firstName, lastMame, wmName, 
                            workDateStart, workDateEnd, startHour, endHour, userId,
                            wmaschineId, washId);

                    publish(bm);*/
                return true;
            }

            protected void process(List<BookingModell> bms) {}

            @Override
            protected void done() {
                System.out.println("Finished reading washes!");
                for(int i = 0; i < data.length; i++){
                    tableModel.addRow(data[i]);
                }
                
            }

        };
        worker.execute();
    }

    public void bookWashTime(int column, int[] rows){
        if(column == 0){
            JOptionPane.showMessageDialog(new JFrame(), "You can't override the intervals",
                "Error", JOptionPane.ERROR_MESSAGE);
        }
        else {
            boolean good = true;
            for(int i = 0; i < rows.length; i++)
                if(data[rows[i]][column] != null)
                    good = false;
            if(good) {
            System.out.println(data[rows[0]][column]);
            int userid = 0;
            String query = "SELECT users.idusers FROM users WHERE users.username LIKE '"+UsersModel.getUsername()+"'";
            System.out.println(query);
            try {
                 ResultSet resultSet = DBConnectSingleton.getStatement().executeQuery(query);
                while(resultSet.next()){
                    userid = resultSet.getInt("idusers");
                }
            } catch (SQLException ex) {
                Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
            }

            int machineid = 0;
            query = "SELECT idwmachines FROM wmachines WHERE name LIKE '"+
                    menuFrame.getWasherPickerController().getSelectedWasher()+"'";
            System.out.println(query);
            try {
                 ResultSet resultSet = DBConnectSingleton.getStatement().executeQuery(query);
                while(resultSet.next()){
                    machineid = resultSet.getInt("idwmachines");
                }
            } catch (SQLException ex) {
                Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
            }

            Date insertDay = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(insertDay);
            c.add(Calendar.DATE, column-1);
            insertDay = c.getTime();
            String insertDayFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(insertDay) + " ";
            query = "INSERT INTO `washmaschines`.`washes` (`users_id`, `wmachines_id`, `work_date_start`, `work_date_end`) VALUES ('"+userid+"', '"+machineid+"', '"+insertDayFormat +(rows[0]+8)+":00:00', '"+insertDayFormat + (rows[rows.length-1]+9) +":00:00')";
            try {
                DBConnectSingleton.getStatement().executeUpdate(query);
            } catch (SQLException ex) {
                Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
            fillWashesTable();
            ((DefaultTableModel)menuFrame.getTable().getModel()).getDataVector().removeAllElements();
            ((DefaultTableModel)menuFrame.getTable().getModel()).fireTableDataChanged();
            JOptionPane.showMessageDialog(new JFrame(), "Wash interval booked!",
                "Successful", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(new JFrame(), "Somebody washes at this time",
                "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void showSuccessfulBookDialog() {
        JOptionPane.showMessageDialog(new JFrame(), "Booking was successful!",
                "Successful", JOptionPane.INFORMATION_MESSAGE);
    }

}
