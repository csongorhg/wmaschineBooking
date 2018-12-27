/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.uni_pannon.mik.model;

import java.util.ArrayList;

/**
 *
 * @author zalchege
 */
public class BookingModell {

    String interval, firstName, lastName, wmName,
            workDateStart, workDateEnd;
    int startHour, endHour, idUsers, idWmachines, idWashes;

    public BookingModell(String firstName, String lastName, String wmName, String workDateStart, String workDateEnd, int startHour, int endHour, int idUsers, int idWmachines, int idWashes) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.wmName = wmName;
        this.workDateStart = workDateStart;
        this.workDateEnd = workDateEnd;
        this.startHour = startHour;
        this.endHour = endHour;
        this.idUsers = idUsers;
        this.idWmachines = idWmachines;
        this.idWashes = idWashes;
    }

    @Override
    public String toString() {
        return "BookingModell{" + "interval=" + interval + ", firstName=" + firstName + ", lastName=" + lastName + ", wmName=" + wmName + ", workDateStart=" + workDateStart + ", workDateEnd=" + workDateEnd + ", startHour=" + startHour + ", endHour=" + endHour + ", idUsers=" + idUsers + ", idWmachines=" + idWmachines + ", idWashes=" + idWashes + '}';
    }
    
    

}
