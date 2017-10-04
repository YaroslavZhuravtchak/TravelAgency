package com.zhuravchak.model.utill;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * The Class Hashing.
 */
public class Hashing {

    /**
     * Generate salt.
     *
     * @return salt
     */
    public static byte[] generateSalt(){
        byte[] salt = new byte[32];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);

        return salt;
    }

    /**
     * Salt password.
     *
     * @param  password the password to salt
     * @param  salt the salt
     * @return salted password
     */
    public static byte[] saltPassword(String password, byte[] salt) {

        byte[] passwordInBytes = null;
        byte[] saltedPassword = new byte[32];
        Arrays.fill(saltedPassword,(byte)0);
        try {
            passwordInBytes = password.getBytes("UTF-8");

            System.arraycopy(passwordInBytes, 0, saltedPassword  , 0, passwordInBytes.length);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        for(int i = 0; i<saltedPassword.length; i++){
            saltedPassword[i] = (byte) (saltedPassword[i]^salt[i]);
        }
        return saltedPassword;
    }

    /**
     * Hash password in sha256
     *
     * @param  saltedPassword the salted password
     * @return hashed password
     */
    public static String sha256(byte[] saltedPassword) {
        byte[] hash = null;
        try{
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            hash = sha.digest(saltedPassword);
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
        return byteToHexString(hash);
    }

    /**
     * Convert byte array to hex string
     *
     * @param  arr the byte array
     * @return hex string
     */
    public static String byteToHexString(byte[] arr) {
        StringBuffer hexString = new StringBuffer();

        for (int i = 0; i < arr.length; i++) {
            String hex = Integer.toHexString(0xff & arr[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    /**
     * Convert hex string to byte array
     *
     * @param  str the hex string
     * @return byte array
     */
    public static byte[] hexStringToByte(String str){
        byte[] arr = new byte[32];
        Arrays.fill(arr, (byte) 0);
        for(int i = 0, k =0 ; i<str.length(); i+=2,k++){
            String b = String.valueOf(str.charAt(i))+String.valueOf(str.charAt(i+1));
            int n = Integer.valueOf(b,16);
            arr[k]= (byte)n;
        }
        return arr;
    }
}
