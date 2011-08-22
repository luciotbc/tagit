package com.luciotbc.tagit.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class Encrypt {
    public static String encrypt(String key) {     
        try {     
             MessageDigest digest = MessageDigest.getInstance("MD5");      
             digest.update(key.getBytes());      
             BASE64Encoder encoder = new BASE64Encoder();      
             return encoder.encode (digest.digest ());      
        } catch (NoSuchAlgorithmException ns) {     
             ns.printStackTrace ();      
             return key;      
        }
}
}
