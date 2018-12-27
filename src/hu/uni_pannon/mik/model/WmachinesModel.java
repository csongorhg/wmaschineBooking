/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.uni_pannon.mik.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author zalchege
 */
public class WmachinesModel {

    private int idwmachines;
    private String name, description, date_of_insertion;

    public WmachinesModel() {}
    
    public WmachinesModel(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    public WmachinesModel(int idwmachines, String name, String description,
            String date_of_insertion) {
        this.idwmachines = idwmachines;
        this.name = name;
        this.description = description;
        this.date_of_insertion = date_of_insertion;
    }

    public int getIdwmachines() {
        return idwmachines;
    }

    public void setIdwmachines(int idwmachines) {
        this.idwmachines = idwmachines;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate_of_insertion() {
        return date_of_insertion;
    }

    public void setDate_of_insertion(String date_of_insertion) {
        this.date_of_insertion = date_of_insertion;
    }

}
