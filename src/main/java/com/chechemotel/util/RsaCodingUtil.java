package com.chechemotel.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import sun.misc.BASE64Decoder;


import org.apache.commons.codec.binary.Base64;


/**
 * <b>Rsa加解密工具</b><br>
 * <br>
 * 公钥采用X509,Cer格式的<br>
 * 私钥采用PKCS12加密方式的PFX私钥文件<br>
 * 加密算法为1024位的RSA，填充算法为PKCS1Padding<br>
 * 
 * @author 行者
 * @version 4.1.0
 */
public final class RsaCodingUtil {


	// ======================================================================================
	// 公钥加密私钥解密段
	// ======================================================================================

	/**
	 * 指定Cer公钥路径加密
	 * 
	 * @param src
	 * @param pubCerPath
	 * @return hex串
	 */
	public static String encryptByPubCerFile(String src, String pubCerPath) {

		PublicKey publicKey = RsaReadUtil.getPublicKeyFromFile(pubCerPath);
		if (publicKey == null) {
			return null;
		}
		return encryptByPublicKey(src, publicKey);
	}

	/**
	 * 用公钥内容加密
	 * 
	 * @param src
	 * @param pubKeyText
	 * @return hex串
	 */
	public static String encryptByPubCerText(String src, String pubKeyText) {
		PublicKey publicKey = RsaReadUtil.getPublicKeyByText(pubKeyText);
		if (publicKey == null) {
			return null;
		}
		return encryptByPublicKey(src, publicKey);
	}

	/**
	 * 公钥加密返回
	 * 
	 * @param src
	 * @param publicKey
	 * @param encryptMode
	 * @return hex串
	 */
	public static String encryptByPublicKey(String src, PublicKey publicKey) {
		byte[] destBytes = rsaByPublicKey(src.getBytes(), publicKey, Cipher.ENCRYPT_MODE);

		if (destBytes == null) {
			return null;
		}

		return FormatUtil.byte2Hex(destBytes);

	}

	/**
	 * 根据私钥文件解密
	 * 
	 * @param src
	 * @param pfxPath
	 * @param priKeyPass
	 * @return
	 */
	public static String decryptByPriPfxFile(String src, String pfxPath, String priKeyPass) {
		if (FormatUtil.isEmpty(src) || FormatUtil.isEmpty(pfxPath)) {
			return null;
		}
		PrivateKey privateKey = RsaReadUtil.getPrivateKeyFromFile(pfxPath, priKeyPass);
		if (privateKey == null) {
			return null;
		}
		return decryptByPrivateKey(src, privateKey);
	}

	/**
	 * 根据私钥文件流解密
	 * 
	 * @param src
	 * @param pfxPath
	 * @param priKeyPass
	 * @return
	 */
	public static String decryptByPriPfxStream(String src, byte[] pfxBytes, String priKeyPass) {
		if (FormatUtil.isEmpty(src)) {
			return null;
		}
		PrivateKey privateKey = RsaReadUtil.getPrivateKeyByStream(pfxBytes, priKeyPass);
		if (privateKey == null) {
			return null;
		}
		return decryptByPrivateKey(src, privateKey);
	}

	/**
	 * 私钥解密
	 * 
	 * @param src
	 * @param privateKey
	 * @return
	 */
	public static String decryptByPrivateKey(String src, PrivateKey privateKey) {
		if (FormatUtil.isEmpty(src)) {
			return null;
		}
		try {
			byte[] destBytes = rsaByPrivateKey(FormatUtil.hex2Bytes(src), privateKey, Cipher.DECRYPT_MODE);
			if (destBytes == null) {
				return null;
			}
			return new String(destBytes, RsaConst.ENCODE);
		} catch (UnsupportedEncodingException e) {
//			//log.error("解密内容不是正确的UTF8格式:", e);
		} catch (Exception e) {
//			//log.error("解密内容异常", e);
		}

		return null;
	}

	// ======================================================================================
	// 私钥加密公钥解密
	// ======================================================================================

	/**
	 * 根据私钥文件加密
	 * 
	 * @param src
	 * @param pfxPath
	 * @param priKeyPass
	 * @return
	 */
	public static String encryptByPriPfxFile(String src, String pfxPath, String priKeyPass) {

		PrivateKey privateKey = RsaReadUtil.getPrivateKeyFromFile(pfxPath, priKeyPass);
		if (privateKey == null) {
			return null;
		}
		return encryptByPrivateKey(src, privateKey);
	}

	/**
	 * 根据私钥文件流加密
	 * 
	 * @param src
	 * @param pfxPath
	 * @param priKeyPass
	 * @return
	 */
	public static String encryptByPriPfxStream(String src, byte[] pfxBytes, String priKeyPass) {
		PrivateKey privateKey = RsaReadUtil.getPrivateKeyByStream(pfxBytes, priKeyPass);
		if (privateKey == null) {
			return null;
		}
		return encryptByPrivateKey(src, privateKey);
	}

	/**
	 * 根据私钥加密
	 * 
	 * @param src
	 * @param privateKey
	 */
	public static String encryptByPrivateKey(String src, PrivateKey privateKey) {

		byte[] destBytes = rsaByPrivateKey(src.getBytes(), privateKey, Cipher.ENCRYPT_MODE);

		if (destBytes == null) {
			return null;
		}
		return FormatUtil.byte2Hex(destBytes);

	}

	/**
	 * 指定Cer公钥路径解密
	 * 
	 * @param src
	 * @param pubCerPath
	 * @return
	 */
	public static String decryptByPubCerFile(String src, String pubCerPath) {
		PublicKey publicKey = RsaReadUtil.getPublicKeyFromFile(pubCerPath);
		if (publicKey == null) {
			return null;
		}
		return decryptByPublicKey(src, publicKey);
	}

	/**
	 * 根据公钥文本解密
	 * 
	 * @param src
	 * @param pubKeyText
	 * @return
	 */
	public static String decryptByPubCerText(String src, String pubKeyText) {
		PublicKey publicKey = RsaReadUtil.getPublicKeyByText(pubKeyText);
		if (publicKey == null) {
			return null;
		}
		return decryptByPublicKey(src, publicKey);
	}

	/**
	 * 根据公钥解密
	 * 
	 * @param src
	 * @param publicKey
	 * @return
	 */
	public static String decryptByPublicKey(String src, PublicKey publicKey) {

		try {
			byte[] destBytes = rsaByPublicKey(FormatUtil.hex2Bytes(src), publicKey, Cipher.DECRYPT_MODE);
			if (destBytes == null) {
				return null;
			}
			return new String(destBytes, RsaConst.ENCODE);
		} catch (UnsupportedEncodingException e) {
//			//log.error("解密内容不是正确的UTF8格式:", e);
		}
		return null;
	}

	// ======================================================================================
	// 公私钥算法
	// ======================================================================================
	/**
	 * 公钥算法
	 * 
	 * @param srcData
	 *            源字节
	 * @param publicKey
	 *            公钥
	 * @param mode
	 *            加密 OR 解密
	 * @return
	 */
	public static byte[] rsaByPublicKey(byte[] srcData, PublicKey publicKey, int mode) {
		try {
			Cipher cipher = Cipher.getInstance(RsaConst.RSA_CHIPER);
			cipher.init(mode, publicKey);
			// 分段加密
			int blockSize = (mode == Cipher.ENCRYPT_MODE) ? RsaConst.ENCRYPT_KEYSIZE : RsaConst.DECRYPT_KEYSIZE;
			byte[] encryptedData = null;
			for (int i = 0; i < srcData.length; i += blockSize) {
				// 注意要使用2的倍数，否则会出现加密后的内容再解密时为乱码
				byte[] doFinal = cipher.doFinal(subarray(srcData, i, i + blockSize));
				encryptedData = addAll(encryptedData, doFinal);
			}
			return encryptedData;

		} catch (NoSuchAlgorithmException e) {
//			//log.error("公钥算法-不存在的解密算法:", e);
		} catch (NoSuchPaddingException e) {
//			//log.error("公钥算法-无效的补位算法:", e);
		} catch (IllegalBlockSizeException e) {
//			//log.error("公钥算法-无效的块大小:", e);
		} catch (BadPaddingException e) {
//			//log.error("公钥算法-补位算法异常:", e);
		} catch (InvalidKeyException e) {
//			//log.error("公钥算法-无效的私钥:", e);
		}
		return null;
	}

	/**
	 * 私钥算法
	 * 
	 * @param srcData
	 *            源字节
	 * @param privateKey
	 *            私钥
	 * @param mode
	 *            加密 OR 解密
	 * @return
	 */
	public static byte[] rsaByPrivateKey(byte[] srcData, PrivateKey privateKey, int mode) {
		try {
			Cipher cipher = Cipher.getInstance(RsaConst.RSA_CHIPER);
			cipher.init(mode, privateKey);
			// 分段加密
			int blockSize = (mode == Cipher.ENCRYPT_MODE) ? RsaConst.ENCRYPT_KEYSIZE : RsaConst.DECRYPT_KEYSIZE;
			byte[] decryptData = null;
			for (int i = 0; i < srcData.length; i += blockSize) {
				byte[] doFinal = cipher.doFinal(subarray(srcData, i, i + blockSize));
				decryptData = addAll(decryptData, doFinal);
			}
			return decryptData;
		} catch (NoSuchAlgorithmException e) {
//			//log.error("私钥算法-不存在的解密算法:", e);
		} catch (NoSuchPaddingException e) {
			//log.error("私钥算法-无效的补位算法:", e);
		} catch (IllegalBlockSizeException e) {
			//log.error("私钥算法-无效的块大小:", e);
		} catch (BadPaddingException e) {
			//log.error("私钥算法-补位算法异常:", e);
		} catch (InvalidKeyException e) {
			//log.error("私钥算法-无效的私钥:", e);
		}
		return null;
	}

	// /////////////==========================
	public static byte[] subarray(byte[] array, int startIndexInclusive, int endIndexExclusive) {
		if (array == null) {
			return null;
		}
		if (startIndexInclusive < 0) {
			startIndexInclusive = 0;
		}
		if (endIndexExclusive > array.length) {
			endIndexExclusive = array.length;
		}
		int newSize = endIndexExclusive - startIndexInclusive;
		if (newSize <= 0) {
			return new byte[0];
		}

		byte[] subarray = new byte[newSize];
		System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
		return subarray;
	}

	public static byte[] addAll(byte[] array1, byte[] array2) {
		if (array1 == null) {
			return clone(array2);
		} else if (array2 == null) {
			return clone(array1);
		}
		byte[] joinedArray = new byte[array1.length + array2.length];
		System.arraycopy(array1, 0, joinedArray, 0, array1.length);
		System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
		return joinedArray;
	}

	public static byte[] clone(byte[] array) {
		if (array == null) {
			return null;
		}
		return (byte[]) array.clone();
	}
	
	public static void main(String[] args) throws Exception {

		String aa = new String(Base64.decodeBase64("NTMxMzQ0MThlYzY5YzNmNTY4MzM4NWNjOGM4MWI1MDcyNjdmMGZlOTE0ZmEzNWYxZWRmMDkzOTdkZmE0N2E1YjU2ZjIzYTJmZTVhYTI4ZTRjOGE1OWU2YTg4OGJiYWMxZWU3Zjg5MTQ4NWI1NzE2NDJjNDVjN2I0NGM2NzUwZWRhZGEzODU5YmQ3M2Y0YzhkNjViMTFmZDU4OTUzYWY3ZjgwNzBhY2U4MWU1YjI3M2M2Y2I0NDlhMzM0ZWI3YTI5MTA2MmI5NTViZTIzOTA5MDhmOTA0OTA3NTUwMmZmMDNhMjNjZDNjY2E0ODEzZWM5YzhiNjA2NWY5ZWNkYWUzZg=="));
		System.out.println(aa);
		
		 PublicKey publicKey = null;

		   // 自己的公钥(测试)
		String pubKey ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCOVjCCOgYI6ch0H+IoC3r+GsXXt3214IlSVAXcMt5P48NeU2Uuxkv3nD6cSnFXHZ6eFxD5+RumaS1tAjvtzG860DCN8HkVyrnATEEwG92yYm/cqnHmG2FEvTMCXhv4PEWXpWIwTnW1ohLR8n8IN9ffCYafKY0snlyG08KficOZEwIDAQAB";
		publicKey = RsaReadUtil.getPubKey(pubKey);
		
		String enStr = encryptByPublicKey("12ssdsfase3123123",publicKey);
		
		System.out.println("1---------"+enStr);
		
		String pkStr ="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAI5WMII6BgjpyHQf4igLev4axde3fbXgiVJUBdwy3k/jw15TZS7GS/ecPpxKcVcdnp4XEPn5G6ZpLW0CO+3MbzrQMI3weRXKucBMQTAb3bJib9yqceYbYUS9MwJeG/g8RZelYjBOdbWiEtHyfwg3198Jhp8pjSyeXIbTwp+Jw5kTAgMBAAECgYAbowaWMpf7YRFH4uzxo5EFB8PEZ99j0i43qxLcN5BM6MeFaCHsXBpiAJdzl0y8gobK4WWtc733qNJmKRnjyV8LOyuKl1cqR/zXBXed5ci9LraC9TWPzLQjsOD+J/Y+hZ4DHKgqrM82RIrW2A66Lg9iEVUaxEk2d5Uf3oZx86oKIQJBAPHnIr7Cdh1RzHK/o0/YCLJY/awelgG5FhqgSsxB8o02BhSKrYIDn/k/VIy6FrXQS92rRIygbXvCZMUYOu06YdECQQCWoakZancOaXs3dr1F4ifMOGTiMLCZsDisashJ4ccpBlcHiB59yXmhUexE7OmvtLoVw5KX2wHzZMV2NLvrvYGjAkA0pWALchjwopfDNW/edZ7W6cUzi2iMMXLdEpuUvpmCIArMt62xSQas4RqOkgVVRmLseFSv4k5Ri+oQdwiEDpuxAkA8o6HegHSjUaVv45Y4nt2kogNC55UAmeeGyYoN7B1wD4RTnOGhg806Oy+hY7qDqH7NKilGhr0XTbcMahhIw8Z/AkEA6NiXpYKED7uOnJT4rVySNVQ8e3NtJPyb8QRuZPXaG45itgg2PidVxgoBGCNW1OGwInJ/vpfdRDVm9yU/uLF+3Q==";
		PrivateKey privateKey = RsaReadUtil.getPrivateKey(pkStr);

		enStr = "ODhhNDMyMmRhMWYzYzQwNjRiMWY5OGM3Zjc3YjE3NDkxOGEyYTMxZTdkMDI0ZWVlNmRmOWYwMDNiYjVjYWNiZWY0ZmFiY2MwNjUzZjQzZTI0ZDhlZDdhODY4ZWJkMzYxMDhmYWU5OTY4ZTE1NzU3NDgyM2U3MDIzMTQ3ZGI2NWI3NGMzMDY2OWE0YzE4NjE1Mjc3ZWE4OThmYTJkMGYzZjBhMWI1YmEyYTNiNzgyNGExMzAzMDc2ZmYwYjM3NTcyNWRmYTE1M2I0Njc5Mzk2Y2E0ZmM3MmFmZTk0MmI5ODA2MzE1MTY1MTRjNWMwODRiNmRmYWQyY2E0N2NmNDhhZQ==";
		String aba = new String (Base64.decodeBase64(enStr));
	    System.out.println("2---------"+aba);

		String dp = decryptByPrivateKey(aba,privateKey);
		  
	    System.out.println("1---------"+dp);

	}
}
