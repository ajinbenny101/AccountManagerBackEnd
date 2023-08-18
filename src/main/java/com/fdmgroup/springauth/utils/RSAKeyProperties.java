package com.fdmgroup.springauth.utils;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.stereotype.Component;

//class to store both keys
@Component
public class RSAKeyProperties {

	private RSAPublicKey publicKey;
	private RSAPrivateKey privateKey;
	
	public RSAKeyProperties() {
		//uses static method from key generator utility
		KeyPair pair = KeyGeneratorUtility.generateRsaKey();
		//casting generic key to rsa key
		this.publicKey = (RSAPublicKey) pair.getPublic();
		this.privateKey = (RSAPrivateKey) pair.getPrivate();
	}

	public RSAPublicKey getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(RSAPublicKey publicKey) {
		this.publicKey = publicKey;
	}

	public RSAPrivateKey getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(RSAPrivateKey privateKey) {
		this.privateKey = privateKey;
	}
	
	
	
}
