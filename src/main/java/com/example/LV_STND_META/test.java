package com.example.LV_STND_META;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.DigestException;
import java.security.NoSuchAlgorithmException;
//사용 예시 파일
public class test {
	public static void main(String[] args) throws DigestException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException
    {
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
		//Rijndael
		//String encString = LongviewCyptoUtil.encryptForArcplan("Rijndael","testString", "testKey");
		String decString = LongviewCyptoUtil.decryptForArcplan("TripleDES","C67oAtEjOVI=", "My key");
        System.out.println("dasdsad"+decString);
    }
}
