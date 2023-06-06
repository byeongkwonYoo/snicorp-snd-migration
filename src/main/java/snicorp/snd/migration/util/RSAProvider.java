package snicorp.snd.migration.util;

import org.springframework.beans.factory.annotation.Value;

import javax.crypto.Cipher;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.RSAPrivateKeySpec;

public class RSAProvider {

    private static String rsaModule ="a4e6e3f71174bb7e7c57caf907dd2a5fcc7371953d35fc1631f9c1b7b63951af03b2b959d65f1e9b9619842e89ab13bc31d095070070476de5adca625d1ca5058c0e9cefbd6c5a3b3e0af98ccf598d266b84e0bd54ba08beb39827f8f3650b47c68bd5859dfd04687e9e80c6d5dac6138c2587b45e8a60160106b4981ba80f05";

    private static String rsaExponent = "56b794ea38a3ca7599ae116f24538a1516cf1ecbfc84872ca4db52a3e98ffb04ae4d2e6baff015887c46aedc4a494cf78abac5a408f70299284f5c3e9df8924e9dd28e9b7727ca334197b86b66c0b3dfff3f81553e8d6f3620a96ecd7914c753f3450263f86421ae7adfe10b9f313e4ffa6960e0e0d6f6aa0cb9f35a18abfb9";


    public static String encode(String value){
        String retValue = "";

        BigInteger modulus = new BigInteger(rsaModule, 16);
        BigInteger exponent = new BigInteger(rsaExponent, 16);

        try {
            RSAPrivateKeySpec pubks = new RSAPrivateKeySpec(modulus, exponent);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(pubks);

            Cipher cipher = Cipher.getInstance("RSA");
            byte[] encryptedBytes = hexToByteArray(value);

            String test = new String(encryptedBytes,"UTF-8");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            retValue = new String(decryptedBytes, "UTF-8");

            System.out.println(retValue);

        } catch(Exception e){
            e.printStackTrace();
        }

        return retValue;
    }
    public static String decode(String decStr) throws Exception{

        Cipher cipher = Cipher.getInstance("RSA");
        BigInteger modulus = new BigInteger(rsaModule, 16);
        BigInteger exponent = new BigInteger(rsaExponent, 16);

        RSAPrivateKeySpec pubks = new RSAPrivateKeySpec(modulus, exponent);


        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pubks);

        // 복호
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        String test = new String(hexToByteArray(decStr));
        byte[] plainText = cipher.doFinal(hexToByteArray(decStr));

        String returnStr = new String(plainText);

        return returnStr;
    }
    public static byte[] hexToByteArray(String hex) {

        if (hex == null || hex.length() == 0) {

            return null;

        }

        byte[] ba = new byte[hex.length() / 2];

        for (int i = 0; i < ba.length; i++) {

            ba[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);

        }

        return ba;

    }

    public static String byteArrayToHex(byte[] ba) {

        if (ba == null || ba.length == 0) {

            return null;

        }

        StringBuffer sb = new StringBuffer(ba.length * 2);

        String hexNumber;

        for (int x = 0; x < ba.length; x++) {

            hexNumber = "0" + Integer.toHexString(0xff & ba[x]);

            sb.append(hexNumber.substring(hexNumber.length() - 2));

        }

        return sb.toString();

    }
/*
  public static byte[] hexToByteArray(String hex) {
      if (hex == null || hex.length() % 2 != 0) { return new byte[] {}; }

      byte[] bytes = new byte[hex.length() / 2];
      for (int i = 0; i < hex.length(); i += 2) {
          byte value = (byte) Integer.parseInt(hex.substring(i, i + 2), 16);
          bytes[(int) Math.floor(i / 2)] = value;
      }
      return bytes;
    }

    public static String byteArrayToHex(byte[] ba) {
        if (ba == null || ba.length == 0) {
            return null;
        }
        StringBuffer sb = new StringBuffer(ba.length * 2);
        String hexNumber;
        for (int x = 0; x < ba.length; x++) {
            hexNumber = "0" + Integer.toHexString(0xff & ba[x]);

            sb.append(hexNumber.substring(hexNumber.length() - 2));
        }
        return sb.toString();
    }

 */
}
