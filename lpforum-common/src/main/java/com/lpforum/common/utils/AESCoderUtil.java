package com.lpforum.common.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 * AES 加密解密工具类
 * 
 * @author lipeng
 */
public class AESCoderUtil {

    /**
     * 密钥算法
     */
    private static final String KEY_ALGORITHM            = "AES";

    /**
     * 加密算法/工作模式/填充方式
     */
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * 加密算法/工作模式/填充方式
     */
    private static final String CBC_ALGORITHM            = "AES/CBC/PKCS5Padding";

    private AESCoderUtil() {

    }

    /**
     * 转换密钥
     * 
     * @param key 二进制密钥
     * @return 密钥
     */
    private static Key toKey(byte[] key) {
        // 生成密钥
        return new SecretKeySpec(key, KEY_ALGORITHM);
    }

    /**
     * 加密
     * 
     * @param toEncryptStr 待加密的字符串
     * @param key 密钥
     * @return String 加密后的字符串（16位形式）
     * @throws Exception
     */
    public static String encrypt(String toEncryptStr, Key key) throws Exception {
        byte[] encryptBytes = encrypt(toEncryptStr.getBytes(), key);
        return parseByte2HexStr(encryptBytes);
    }

    /**
     * 加密
     * 
     * @param toEncryptStr 待加密的字符串
     * @param key 密钥
     * @return String 加密后的字符串（16位形式）
     * @throws Exception
     */
    public static String encrypt(String toEncryptStr, String key) throws Exception {
        byte[] encryptBytes = encrypt(toEncryptStr.getBytes(), parseHexStr2Byte(key));
        return parseByte2HexStr(encryptBytes);
    }

    /**
     * 加密
     * 
     * @param data 待加密数据
     * @param key 密钥
     * @return byte[] 加密数据
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, Key key) throws Exception {
        return encrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
    }

    /**
     * 加密
     * 
     * @param data 待加密数据
     * @param key 二进制密钥
     * @return byte[] 加密数据
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        return encrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
    }

    /**
     * 加密
     * 
     * @param data 待加密数据
     * @param key 二进制密钥
     * @param cipherAlgorithm 加密算法/工作模式/填充方式
     * @return byte[] 加密数据
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, byte[] key, String cipherAlgorithm) throws Exception {
        // 还原密钥
        Key k = toKey(key);
        return encrypt(data, k, cipherAlgorithm);
    }

    /**
     * 加密
     * 
     * @param data 待加密数据
     * @param key 密钥
     * @param cipherAlgorithm 加密算法/工作模式/填充方式
     * @return byte[] 加密数据
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, Key key, String cipherAlgorithm) throws Exception {
        // 实例化
        Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        // 使用密钥初始化，设置为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, key);
        // 执行操作
        return cipher.doFinal(data);
    }

    /**
     * 解密
     * 
     * @param toDecryptStr 待解密的字符串
     * @param key 密钥
     * @return String 解密后的字符串
     * @throws Exception
     */
    public static String decrypt(String toDecryptStr, String key) throws Exception {
        byte[] str2Byte = parseHexStr2Byte(toDecryptStr);
        byte[] decrypt = decrypt(str2Byte, parseHexStr2Byte(key));
        return new String(decrypt, "utf-8");
    }

    /**
     * 解密
     * 
     * @param data 待解密数据
     * @param key 二进制密钥
     * @return byte[] 解密数据
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        return decrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
    }

    /**
     * 解密
     * 
     * @param data 待解密数据
     * @param key 密钥
     * @return byte[] 解密数据
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, Key key) throws Exception {
        return decrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
    }

    /**
     * 解密
     * 
     * @param data 待解密数据
     * @param key 二进制密钥
     * @param cipherAlgorithm 加密算法/工作模式/填充方式
     * @return byte[] 解密数据
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, byte[] key, String cipherAlgorithm) throws Exception {
        // 还原密钥
        Key k = toKey(key);
        return decrypt(data, k, cipherAlgorithm);
    }

    /**
     * 解密
     * 
     * @param data 待解密数据
     * @param key 密钥
     * @param cipherAlgorithm 加密算法/工作模式/填充方式
     * @return byte[] 解密数据
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, Key key, String cipherAlgorithm) throws Exception {
        // 实例化
        Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        // 使用密钥初始化，设置为解密模式
        cipher.init(Cipher.DECRYPT_MODE, key);
        // 执行操作
        return cipher.doFinal(data);
    }

    /**
     * 将16进制转换为二进制
     * 
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return new byte[0];
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     * 将二进制转换成16进制
     * 
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }


    public static void main(String[] args) throws Exception {
    	try {
			String mobile = "15800389112";//用户手机号
			String key = "CB4BCDA4C9426E0817578E5CADDDD445";//AES秘钥
			//加密mobile
			String encreptMobile = encrypt(mobile, key);
			System.out.println("mobile:"+encreptMobile);
			System.out.println("decreptMobile:"+decrypt(encreptMobile, key));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
