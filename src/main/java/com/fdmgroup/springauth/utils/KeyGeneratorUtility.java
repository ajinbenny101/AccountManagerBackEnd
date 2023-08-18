package com.fdmgroup.springauth.utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class KeyGeneratorUtility {
	
	//generate rsa keys which are used to encode jwts
	public static KeyPair generateRsaKey() {
		
		KeyPair keyPair;
		
		try {
			//want rsa key
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			//set bit size
			keyPairGenerator.initialize(2048);
			//generate key pair based on prev requirements
			keyPair = keyPairGenerator.generateKeyPair();
		} catch (Exception e) {
			throw new IllegalStateException();
		}
		
		return keyPair;
	}
	
}
