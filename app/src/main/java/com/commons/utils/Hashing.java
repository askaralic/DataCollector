package com.commons.utils;


import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Askar on 12/23/2014.
 */
public class Hashing {
    private static final String salt = "@@-Dubai~Technologies~CRM-$$";

    public static byte[] GetHashKey(String hashKey) {
        // Initialise
        try {
            int dkLen = 16;
            int rounds = 1000;
            PBEKeySpec keySpec = new PBEKeySpec(hashKey.toCharArray(), salt.getBytes(), rounds, dkLen * 8);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return factory.generateSecret(keySpec).getEncoded();

        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String Encrypt(byte[] key, String dataToEncrypt) {
        try {
            Cipher encryptCipher;
            IvParameterSpec iv = new IvParameterSpec(key);
            SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
            encryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            encryptCipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = encryptCipher.doFinal(dataToEncrypt.getBytes());
            LogUtils.LOGD("TAG", "encrypted string:"
                    + Base64.encodeToString(encrypted, Base64.DEFAULT));
            return Base64.encodeToString(encrypted, Base64.DEFAULT).trim();
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | BadPaddingException | InvalidAlgorithmParameterException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static String Decrypt(byte[] key, String dataToDecrypt) {
        try {
            Cipher encryptCipher;
            IvParameterSpec iv = new IvParameterSpec(key);
            SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
            encryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            encryptCipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] decodedVal = Base64.decode(dataToDecrypt.getBytes(StandardCharsets.UTF_8), Base64.DEFAULT);
            byte[] original = encryptCipher.doFinal(decodedVal);
            return new String(original, StandardCharsets.UTF_8).trim();
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | InvalidAlgorithmParameterException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;

    }
}
