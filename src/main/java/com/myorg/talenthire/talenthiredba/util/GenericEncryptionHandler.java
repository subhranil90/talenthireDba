package com.myorg.talenthire.talenthiredba.util;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GenericEncryptionHandler {
	// Private Key
	private final String PASSWORD_ENC_KEY = "talenthiredbaenckeypass";
	// Algorithm Used
	private final String PASSWORD_ENC_ALG = "PBEWithMD5AndDES";
	private final String PASSWORD_ENC_KEY_OBTAIN_ITERATION = "1000";
	private final String PASSWORD_ENC_POOLSIZE = "1";
	private final String PASSWORD_ENC_PROVIDER = "SunJCE";
	private final String PASSWORD_ENC_GENERATOR = "org.jasypt.salt.RandomSaltGenerator";
	private final String PASSWORD_ENC_OUTPUT_TYPE = "base64";

	@Bean(name = "jasyptStringEncryptor")
	public StringEncryptor getPasswordEncryptor() {
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		config.setPassword(PASSWORD_ENC_KEY);
		config.setAlgorithm(PASSWORD_ENC_ALG);
		config.setKeyObtentionIterations(PASSWORD_ENC_KEY_OBTAIN_ITERATION);
		config.setPoolSize(PASSWORD_ENC_POOLSIZE);
		config.setProviderName(PASSWORD_ENC_PROVIDER);
		config.setSaltGeneratorClassName(PASSWORD_ENC_GENERATOR);
		config.setStringOutputType(PASSWORD_ENC_OUTPUT_TYPE);
		encryptor.setConfig(config);
		return encryptor;
	}
}
