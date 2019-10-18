package com.rubberduck.RubberDuckWebService.service;

import com.rubberduck.RubberDuckWebService.model.Student;
import com.rubberduck.RubberDuckWebService.model.Teacher;
import com.rubberduck.RubberDuckWebService.model.User;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public interface ValidationService {

    String key = "Bar12345Bar12345";

    static String userSignIn(User user, String password) throws NoSuchAlgorithmException {

        String hashedPassword = user.hashPassword(password);

        if(hashedPassword.equals(user.getPassword())) {
            if(user instanceof Student)
                return getAccessToken(user, "STUDENT");
            if(user instanceof Teacher)
                return getAccessToken(user, "TEACHER");
            return "";
        } else {
            throw new IllegalArgumentException();
        }
    }

    static String getAccessToken(User user, String userType) {

        // use userId|userType|userName|password| to generate the access token
        String accessToken = "";
        try {
            String text = "" + user.getId() + "|" + userType + "|" + user.getUserName() + "|" + user.getPassword() ;
            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(text.getBytes("UTF8"));
            Base64.Encoder encoder = Base64.getEncoder();
            accessToken = encoder.encodeToString(encrypted);
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] cipherText = decoder.decode(accessToken.getBytes("UTF8"));
            String decrypted = new String(cipher.doFinal(encrypted), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return accessToken;
    }

    static String[] decryptAccessToken(String accessToken) {

        String decrypted = "";
        String[] info = null;
        try {
            Cipher cipher = Cipher.getInstance("AES");
            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] cipherText = decoder.decode(accessToken.getBytes("UTF8"));
            decrypted = new String(cipher.doFinal(cipherText), "UTF-8");
            info = decrypted.split("\\|");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }

    static String getUserId(String accessToken) {
        String[] result = decryptAccessToken(accessToken);
        if(result.length == 0) {
            throw new IllegalArgumentException();
        }
        return result[0];
    }

}
