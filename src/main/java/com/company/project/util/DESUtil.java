package com.company.project.util;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

public class DESUtil {
	private static Key key;
	private static String KEY_STR = "myKey";// 密钥
	private static String ALGORITHM = "DES";// 加密类型
	
	static {
        try {
            KeyGenerator generator = KeyGenerator.getInstance(ALGORITHM);
            generator.init(new SecureRandom(KEY_STR.getBytes()));
            key = generator.generateKey();
            generator = null;
        } catch (Exception e) {
            // TODO: handle exception
            throw new RuntimeException(e);
        }
    }
	
	

	/**
	 * 加密
	 * @param source
	 * @return
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 */
	public static byte[] encrypt(byte[] source) throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException{
		
		Cipher cipher = Cipher.getInstance(ALGORITHM); 
		cipher.init(Cipher.ENCRYPT_MODE, key); 
		byte[] cipherByte = cipher.doFinal(source);    
		return cipherByte;    
	}
	
	/**
	 * 解密
	 * @param source
	 * @return
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 */
	public static byte[] decrypt(byte[] source)throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException{
		
		Cipher cipher = Cipher.getInstance(ALGORITHM); 
		cipher.init(Cipher.DECRYPT_MODE, key); 
		byte[] cipherByte = cipher.doFinal(source);    
		return cipherByte;  
	
	}
	
}
