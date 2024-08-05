package com.example.LV_STND_META;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.DigestException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/* 암호화 종류
 * AES
 * AES_INTERN
 * DES
 * RC2
 * TripleDES
 * Rijndael
 * 
 * 사용 예시 
 * LongviewCyptoUtil.encryptForArcplan(암호화 종류,암호화 하고자 할 문자열, 암복호화 키);
 * LongviewCyptoUtil.decryptForArcplan(암호화 종류,복호화 하고자 할 문자열, 암복호화 키);
 * 
 * String encString = LongviewCyptoUtil.encryptForArcplan("AES","testString", "LongviewKey");
 * String decString = LongviewCyptoUtil.decryptForArcplan("AES","df88UZrbNKpB9YuVNGkZ0Q==", "LongviewKey");
 * 
 * 주의 사항
 * JAVA 에서 사용하는 암복호화 키와 Longview 에서 사용하는 암복호화 키는 동일해야함
 * 위 암호와 종류 이외의 문자열을 함수에 넣으면 AES 방식으로 작동함. 위에 적혀있는 암호화 종류만 사용해야함
 * 함호화 종류 입력 시 위 주석을 복사하여 넣는걸 추천함(대소문자 구분 및 오타방지)
 * 
 * 파일 역할
 * LongviewCyptoUtil.java - 실제 롱뷰암복호화가 이루어지는 자바 소스
 * PasswordDeriveBytes.java - 롱뷰 암복호화를 위해 필요한 자바 소스
 * test.java - 위 파일을 프로젝트에 추가하여 실제 소스에서 사용하는 예시
 */

public class LongviewCyptoUtil {

	public static String encryptForArcplan(String LongviewEncryptType, String valueToEncrypt,String key) throws DigestException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException
    {
		String outEncrypted = "";

		outEncrypted = longviewEncrypt(LongviewEncryptType,valueToEncrypt, key);

        return outEncrypted;
    }
	
	public static String decryptForArcplan(String LongviewEncryptType, String valueToEncrypt,String key) throws DigestException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException 
    {
		String outDecrypted = "";

		outDecrypted = longviewDecrypt(LongviewEncryptType,valueToEncrypt, key);

        return outDecrypted;
    }
	
	 public static String longviewEncrypt(String LongviewEncryptType, String inputString, String key) throws DigestException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException  
     {
         byte[] inBlock = null;
         String encodingType = "UTF-8";
		try {
			inBlock = inputString.getBytes(encodingType);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
         String result = "";
         
         Cipher symmetricAlgorithm  = null;
         
		 
         int cb1;
         int cb2;
         
         if(LongviewEncryptType.equals("AES")||LongviewEncryptType.equals("AES_INTERN")) {
        	 symmetricAlgorithm  = Cipher.getInstance("AES/CBC/PKCS5Padding");
             cb1 = 32;
     	     cb2 = 16;           	 
         }else if(LongviewEncryptType.equals("DES")) {
        	 symmetricAlgorithm  = Cipher.getInstance("DES/CBC/PKCS5Padding");
             cb1 = 8;
     	     cb2 = 8;           	 
         }else if(LongviewEncryptType.equals("RC2")) {
        	 symmetricAlgorithm  = Cipher.getInstance("RC2/CBC/PKCS5Padding");
             cb1 = 16;
     	     cb2 = 8;           	 
         }else if(LongviewEncryptType.equals("TripleDES")) {
        	 symmetricAlgorithm  = Cipher.getInstance("DESede/CBC/PKCS5Padding");
             cb1 = 24;
     	     cb2 = 8;           	 
         }else if(LongviewEncryptType.equals("Rijndael")) {
        	 symmetricAlgorithm  = Cipher.getInstance("AES/CBC/PKCS5Padding");
             cb1 = 32;
     	     cb2 = 16;           	 
         }else {
        	 symmetricAlgorithm  = Cipher.getInstance("AES/CBC/PKCS5Padding");
             cb1 = 32;
     	     cb2 = 16;        	 
         }
 		 
         byte[] rgbSalt = new byte[13];
         
         if(LongviewEncryptType.equals("AES_INTERN")) {
        	 rgbSalt[0] =(byte) 162;
             rgbSalt[1] =(byte) 18;
             rgbSalt[2] =(byte) 33;
             rgbSalt[3] =(byte) 110;
             rgbSalt[4] =(byte) 33;
             rgbSalt[5] =(byte) 76;
             rgbSalt[6] =(byte) 86;
             rgbSalt[7] =(byte) 70;
             rgbSalt[8] =(byte) 103;
             rgbSalt[9] =(byte) 162;
             rgbSalt[10] =(byte) 50;
             rgbSalt[11] =(byte) 98;
             rgbSalt[12] =(byte) 162;           	 
         }else {
        	 rgbSalt[0] =(byte) 66;
             rgbSalt[1] =(byte) 33;
             rgbSalt[2] =(byte) 18;
             rgbSalt[3] =(byte) 110;
             rgbSalt[4] =(byte) 32;
             rgbSalt[5] =(byte) 77;
             rgbSalt[6] =(byte) 101;
             rgbSalt[7] =(byte) 100;
             rgbSalt[8] =(byte) 118;
             rgbSalt[9] =(byte) 165;
             rgbSalt[10] =(byte) 51;
             rgbSalt[11] =(byte) 101;
             rgbSalt[12] =(byte) 66;         	 
         }
         
        
         PasswordDeriveBytes passwordDeriveBytes = new PasswordDeriveBytes(key, rgbSalt);
         
         SecretKey secret = null;
         
         if(LongviewEncryptType.equals("AES")||LongviewEncryptType.equals("AES_INTERN")) {
        	 secret = new SecretKeySpec(passwordDeriveBytes.getBytes(cb1), "AES");
         }else if(LongviewEncryptType.equals("DES")) {  
        	 secret = new SecretKeySpec(passwordDeriveBytes.getBytes2(cb1), "DES");
         }else if(LongviewEncryptType.equals("RC2")) {    	 
        	 secret = new SecretKeySpec(passwordDeriveBytes.getBytes(cb1), "RC2");
         }else if(LongviewEncryptType.equals("TripleDES")) {       
        	 secret = new SecretKeySpec(passwordDeriveBytes.getBytes2(cb1), "DESede");
         }else if(LongviewEncryptType.equals("Rijndael")) {       
        	 secret = new SecretKeySpec(passwordDeriveBytes.getBytes(cb1), "AES");
         }else {   	 
        	 secret = new SecretKeySpec(passwordDeriveBytes.getBytes(cb1), "AES");
         }
         
         IvParameterSpec ivspec = null;
         
         if(LongviewEncryptType.equals("TripleDES") || LongviewEncryptType.equals("DES")) {
        	 ivspec = new IvParameterSpec(passwordDeriveBytes.getBytes2(cb2));
         }else {
        	 ivspec = new IvParameterSpec(passwordDeriveBytes.getBytes(cb2));
         }
       
         try {
			symmetricAlgorithm.init(Cipher.ENCRYPT_MODE, secret,ivspec);
		} catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
         byte[] ciphertext = symmetricAlgorithm.doFinal(inBlock);
		
         result = Base64.encodeBase64String(ciphertext);         
         return result;
     }
	 
	 public static String longviewDecrypt(String LongviewEncryptType, String inputString, String key) throws DigestException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException  
     {
         byte[] inBlock = null;
         String encodingType = "UTF-8";
		try {
			inBlock = inputString.getBytes(encodingType);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
         String result = "";
		 
         byte[] input = Base64.decodeBase64(inputString);
         
         Cipher symmetricAlgorithm  = null;
         
		 
         int cb1;
         int cb2;
         
         if(LongviewEncryptType.equals("AES")||LongviewEncryptType.equals("AES_INTERN")) {
        	 symmetricAlgorithm  = Cipher.getInstance("AES/CBC/PKCS5Padding");
             cb1 = 32;
     	     cb2 = 16;           	 
         }else if(LongviewEncryptType.equals("DES")) {
        	 symmetricAlgorithm  = Cipher.getInstance("DES/CBC/PKCS5Padding");
             cb1 = 8;
     	     cb2 = 8;           	 
         }else if(LongviewEncryptType.equals("RC2")) {
        	 symmetricAlgorithm  = Cipher.getInstance("RC2/CBC/PKCS5Padding");
             cb1 = 16;
     	     cb2 = 8;           	 
         }else if(LongviewEncryptType.equals("TripleDES")) {
        	 symmetricAlgorithm  = Cipher.getInstance("DESede/CBC/PKCS5Padding");
             cb1 = 24;
     	     cb2 = 8;           	 
         }else if(LongviewEncryptType.equals("Rijndael")) {
        	 symmetricAlgorithm  = Cipher.getInstance("AES/CBC/PKCS5Padding");
             cb1 = 32;
     	     cb2 = 16;           	 
         }else {
        	 symmetricAlgorithm  = Cipher.getInstance("AES/CBC/PKCS5Padding");
             cb1 = 32;
     	     cb2 = 16;        	 
         }
 		 
         byte[] rgbSalt = new byte[13];
         
         if(LongviewEncryptType.equals("AES_INTERN")) {
        	 rgbSalt[0] =(byte) 162;
             rgbSalt[1] =(byte) 18;
             rgbSalt[2] =(byte) 33;
             rgbSalt[3] =(byte) 110;
             rgbSalt[4] =(byte) 33;
             rgbSalt[5] =(byte) 76;
             rgbSalt[6] =(byte) 86;
             rgbSalt[7] =(byte) 70;
             rgbSalt[8] =(byte) 103;
             rgbSalt[9] =(byte) 162;
             rgbSalt[10] =(byte) 50;
             rgbSalt[11] =(byte) 98;
             rgbSalt[12] =(byte) 162;           	 
         }else {
        	 rgbSalt[0] =(byte) 66;
             rgbSalt[1] =(byte) 33;
             rgbSalt[2] =(byte) 18;
             rgbSalt[3] =(byte) 110;
             rgbSalt[4] =(byte) 32;
             rgbSalt[5] =(byte) 77;
             rgbSalt[6] =(byte) 101;
             rgbSalt[7] =(byte) 100;
             rgbSalt[8] =(byte) 118;
             rgbSalt[9] =(byte) 165;
             rgbSalt[10] =(byte) 51;
             rgbSalt[11] =(byte) 101;
             rgbSalt[12] =(byte) 66;         	 
         }
         
        
         PasswordDeriveBytes passwordDeriveBytes = new PasswordDeriveBytes(key, rgbSalt); 
         
         SecretKey secret = null;
         
         if(LongviewEncryptType.equals("AES")||LongviewEncryptType.equals("AES_INTERN")) {
        	 secret = new SecretKeySpec(passwordDeriveBytes.getBytes(cb1), "AES");
         }else if(LongviewEncryptType.equals("DES")) {  
        	 secret = new SecretKeySpec(passwordDeriveBytes.getBytes2(cb1), "DES");
         }else if(LongviewEncryptType.equals("RC2")) {    	 
        	 secret = new SecretKeySpec(passwordDeriveBytes.getBytes(cb1), "RC2");
         }else if(LongviewEncryptType.equals("TripleDES")) {       
        	 secret = new SecretKeySpec(passwordDeriveBytes.getBytes2(cb1), "DESede");
         }else if(LongviewEncryptType.equals("Rijndael")) {       
        	 secret = new SecretKeySpec(passwordDeriveBytes.getBytes(cb1), "AES");
         }else {   	 
        	 secret = new SecretKeySpec(passwordDeriveBytes.getBytes(cb1), "AES");
         }
         
         IvParameterSpec ivspec = null;
         
         if(LongviewEncryptType.equals("TripleDES") || LongviewEncryptType.equals("DES")) {
        	 ivspec = new IvParameterSpec(passwordDeriveBytes.getBytes2(cb2));
         }else {
        	 ivspec = new IvParameterSpec(passwordDeriveBytes.getBytes(cb2));
         }
         
         try {
			symmetricAlgorithm.init(Cipher.DECRYPT_MODE, secret,ivspec);
		} catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
         byte[] ciphertext = symmetricAlgorithm.doFinal(input);
		
         result = new String(ciphertext,encodingType);     
         return result;
     }
	 
}
