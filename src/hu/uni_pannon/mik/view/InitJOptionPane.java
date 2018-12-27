/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.uni_pannon.mik.view;

import java.awt.Color;
import javax.swing.UIManager;

/**
 *
 * @author zalchege
 */
public class InitJOptionPane {

    public static void init() {
        UIManager UI = new UIManager();
        UI.put("OptionPane.background", new Color(44, 62, 80));
        UI.put("Panel.background", new Color(44, 62, 80));
        UI.put("OptionPane.messageForeground", Color.white);
        
        
    }
}
