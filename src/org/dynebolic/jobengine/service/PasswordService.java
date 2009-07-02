package org.dynebolic.jobengine.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public final class PasswordService{
	public static String encrypt(String plaintext){

		MessageDigest md = null;
	    try{
	    	md = MessageDigest.getInstance("SHA"); //step 2
	    } catch(NoSuchAlgorithmException e) {
	    	try {
				throw new Exception(e.getMessage());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    }
	    
	    try {
	    	md.update(plaintext.getBytes("UTF-8")); //step 3
	    } catch(UnsupportedEncodingException e){

	    	try {
				throw new Exception(e.getMessage());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    }
	    byte raw[] = md.digest(); //step 4
	    String hash = (new BASE64Encoder()).encode(raw); //step 5
	    return hash; //step 6
	}

}