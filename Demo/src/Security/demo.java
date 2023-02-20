package Security;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.Key;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;



public class demo {

    private static Key key;

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, BadPaddingException, InvalidKeyException {
        String clearText = "nihao";
        String originKey = "12345678";

        String cipherText = desEncript(clearText,originKey);
        System.out.println(cipherText);
        String clearText2 = desDecript(cipherText,originKey);
        System.out.println(clearText2);
    }

    private static String desDecript(String cipherText, String originKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        Cipher cipher = Cipher.getInstance("DES");
        Key key = getKey(originKey);
        cipher.init(Cipher.DECRYPT_MODE,key);
        byte[] decode = Base64.decode(cipherText);
        byte[] doFinal = cipher.doFinal(decode);
        return new String(doFinal);
    }

    private static String desEncript(String clearText, String originKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("DES");
        SecretKeySpec key = getKey(originKey);
        cipher.init(Cipher.ENCRYPT_MODE,key);
        byte[] doFinal = cipher.doFinal(clearText.getBytes());
        String encode = Base64.encode(doFinal);
        return encode;
    }

    private static SecretKeySpec getKey(String originKey){
        byte[] buffer = new byte[8];
        byte[] originBytes = originKey.getBytes();
        for (int i = 0; i < 8 && i < originBytes.length; i++){
            buffer[i] = originBytes[i];
        }
        SecretKeySpec key = new SecretKeySpec(buffer,"DES");
        return key;
    }
}
