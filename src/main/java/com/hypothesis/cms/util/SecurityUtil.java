package com.hypothesis.cms.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {

	//key should be minimum length of length 24
	private String myEncryptionKey = "";
	private SecretKeyFactory skf;
	private byte[] arrayBytes;
	private KeySpec ks;
	private Cipher cipher;
	private SecretKey secretKey;

	public SecurityUtil() {
		try {
			arrayBytes = myEncryptionKey.getBytes("UTF8");
			ks = new DESedeKeySpec(arrayBytes);
			skf = SecretKeyFactory.getInstance("DESede");
			cipher = Cipher.getInstance("DESede");
			secretKey = skf.generateSecret(ks);
			System.out.println(myEncryptionKey.length());
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidKeySpecException | InvalidKeyException e) {
			e.printStackTrace();
		}

	}

	public String encrypt(String plainText) {
		byte[] plainTextByte = plainText.getBytes();
		byte[] encryptedByte = null;
		try {
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			encryptedByte = cipher.doFinal(plainTextByte);
		} catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
			e.printStackTrace();
		}
		Base64.Encoder encoder = Base64.getEncoder();
		String encryptedText = encoder.encodeToString(encryptedByte);
		return encryptedText;
	}

	public String decrypt(String encryptedText) {
		Base64.Decoder decoder = Base64.getDecoder();
		byte[] encryptedTextByte = decoder.decode(encryptedText);
		byte[] decryptedByte = null;
		try {
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			decryptedByte = cipher.doFinal(encryptedTextByte);
		} catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
			e.printStackTrace();
		}
		String decryptedText = new String(decryptedByte);
		return decryptedText;
	}

}
