/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.uni_pannon.mik.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author zalchege
 */
public class HashPassword {

    /* Static method, not a constructor */
    public static String hashPassword(String password) throws NoSuchAlgorithmException{

        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(password.getBytes());

        byte[] bytes = messageDigest.digest();

        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(Integer.toHexString(b & 0xff).toString());
        }

        return stringBuilder.toString();

    }

}