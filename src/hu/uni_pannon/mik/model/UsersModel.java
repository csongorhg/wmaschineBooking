/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.uni_pannon.mik.model;

/**
 *
 * @author zalchege
 */
public class UsersModel {

    private static String firstname, lastname, username, password,
            passwordVerify;
    private static int user_id;

    public static int getUser_id() {
        return user_id;
    }

    public static void setUser_id(int user_id) {
        UsersModel.user_id = user_id;
    }
    
    private static UsersModel user;

    private UsersModel() {}
    
    public static UsersModel getInstance() {
        if (UsersModel.user == null) {
            UsersModel.user = new UsersModel();
        }
        return UsersModel.user;
    }

    public static String getFirstname() {
        return UsersModel.firstname;
    }

    public static void setFirstname(String firstname) {
        UsersModel.firstname = firstname;
    }

    public static String getLastname() {
        return UsersModel.lastname;
    }

    public static void setLastname(String lastname) {
        UsersModel.lastname = lastname;
    }

    public static String getUsername() {
        return UsersModel.username;
    }

    public static void setUsername(String username) {
        UsersModel.username = username;
    }

    public static String getPassword() {
        return UsersModel.password;
    }

    public static void setPassword(String password) {
        UsersModel.password = password;
    }

    
    
}
