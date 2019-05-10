/**
 * @author Jose_Gracia_Berenguer, Carlos_Robles
 * @version May 10, 2019
 * @param args Recibe los datos del programa
 */
package controller;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class crypto_controller {

	/**
	 * getCipher crea el cipher a traves de unas coordenadas o keys estáticas que
	 * entre las 4 son 16 dígitos
	 *
	 * @param synchro1      coordenada 1 o key
	 * @param synchro2      coordenada 2 o key
	 * @param synchro3      coordenada 3 o key
	 * @param synchro4      coordenada 4 o key
	 * @param isEncryptMode si está ya encriptado o no
	 * @return devuelve el objeto Cipher
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 */
	private static Cipher getCipher(String synchro1, String synchro2, String synchro3, String synchro4,
			boolean isEncryptMode) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
		byte raw[] = (synchro1 + synchro2 + synchro3 + synchro4).getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES");
		if (isEncryptMode) {
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		} else {
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
		}
		return cipher;
	}

	/**
	 * hexToByte transforma de hexadecimal a byte
	 *
	 * @param hex recibe el texto en hexadecimal
	 * @return devuelve el texto en un array de bytes (bytes[])
	 */
	private static byte[] hexToByte(String hex) {
		byte bytes[] = new byte[hex.length() / 2];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return bytes;
	}

	/**
	 * toHexString pasa de hexadecimal a string
	 *
	 * @param bytes recibe el array de bytes
	 * @return devuelve el texto en string
	 */
	private static String toHexString(byte bytes[]) {
		StringBuffer retString = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			retString.append(Integer.toHexString(256 + (bytes[i] & 0xff)).substring(1));
		}
		return retString.toString();
	}

	/**
	 * encrypt encripta el texto llamando a todos los métodos necesarios
	 *
	 * @param text recibe el texto a encriptar
	 * @return devuelve el texto encriptado
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static String encrypt(String text) throws InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		javax.crypto.Cipher cipher = getCipher("!6w", "9x50", "9F17A", "9AXf", true);
		return toHexString(cipher.doFinal(text.getBytes()));
	}

	/**
	 * decrypt decripta el texto llamando a todos los métodos necesarios
	 *
	 * @param text recibe el texto encriptado
	 * @return devuelve el desencriptado
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 */
	public static String decrypt(String text) throws IllegalBlockSizeException, BadPaddingException,
			InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
		javax.crypto.Cipher cipher = getCipher("!6w", "9x50", "9F17A", "9AXf", false);
		String decrypted = new String(cipher.doFinal(hexToByte(text)));
		return decrypted;
	}

//	public static void main(String[] str) throws Exception {
//		String textToEncrypt = "TextToreceive";
//		String encryptedText = encrypt(textToEncrypt);
//
//		System.out.println("\"" + textToEncrypt + "\" after encryption---->" + encryptedText);
//
//		String decryptedSecretText = decrypt(encryptedText);
//		System.out.println("After decryption --->" + decryptedSecretText);
//
//	}
}
