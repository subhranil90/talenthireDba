package com.myorg.talenthire.talenthiredba.util;

final class AppTestTool {
	public static void main(String[] args) {
		// Test password encryption
		generateEncryptedPassword("password");
		generateDecryptedPassword("Y2rwlAUj1XeQxS80vrXMJI7A0I5cFBZd");
	}
	private static void generateEncryptedPassword(final String plainPassword) {
		GenericEncryptionHandler encHandler = new GenericEncryptionHandler();
		System.out.println(String.format("Plain Text Password: %s", plainPassword));
		System.out.println(String.format("Encrypted Password: %s\n", encHandler.getPasswordEncryptor().encrypt(plainPassword)));
	}
	private static void generateDecryptedPassword(final String encryptedPassword) {
		GenericEncryptionHandler encHandler = new GenericEncryptionHandler();
		System.out.println(String.format("Encrypted Password: %s", encryptedPassword));
		System.out.println(String.format("Plain Text Password: %s\n", encHandler.getPasswordEncryptor().decrypt(encryptedPassword)));
	}
}
