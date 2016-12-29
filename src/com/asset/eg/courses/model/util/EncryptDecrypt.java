package com.asset.eg.courses.model.util;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

public class EncryptDecrypt {
	public static String encrypt(String in) {
		byte[] bytesEncoded;
		try {
			bytesEncoded = Base64.encodeBase64(in.getBytes("UTF8"));
			return new String(bytesEncoded,"UTF8");	
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static String Decrypt(String in) {
		byte[] inBytes;
		try {
			inBytes = in.getBytes("UTF8");
			byte[] valueDecoded= Base64.decodeBase64(inBytes);	
			return new String(valueDecoded,"UTF8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
return null;
	}

}
