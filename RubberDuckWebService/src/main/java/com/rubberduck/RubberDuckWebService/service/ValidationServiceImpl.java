package com.rubberduck.RubberDuckWebService.service;

import com.rubberduck.RubberDuckWebService.model.Student;
import com.rubberduck.RubberDuckWebService.model.Teacher;
import com.rubberduck.RubberDuckWebService.model.User;
import com.rubberduck.RubberDuckWebService.repo.StudentRepo;
import com.rubberduck.RubberDuckWebService.repo.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


@Service
public class ValidationServiceImpl implements ValidationService {

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    TeacherRepo teacherRepo;

    private static String key = "Bar12345Bar54321";

    public String userSignIn(User user, String password) throws NoSuchAlgorithmException {

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

    private String getAccessToken(User user, String userType) {

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

    private static String[] decryptAccessToken(String accessToken) {

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

    private boolean validateUser(User user, String[] info) throws NullPointerException {
        String userId = info[0];
        String password = info[3];

        if (user == null) {
            throw new IllegalArgumentException();
        }
        if (userId.equals(user.getId().toString()) && password.equals(user.getPassword())) {
            return true;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public String getUserId(String accessToken, String desiredUserCategory) throws IllegalArgumentException {
        String[] result = decryptAccessToken(accessToken);
        if(result == null || result.length != 4) {
            throw new IllegalArgumentException();
        }
        String userType = result[1];
        String userName = result[2];

        if (!userType.toLowerCase().equals(desiredUserCategory.toLowerCase())) {
            throw new IllegalArgumentException();
        }

        if (userType.equals("STUDENT")) {
            Student student = studentRepo.findByUserName(userName);
            if (validateUser(student, result)) {
                return result[0];
            }
        }

        if (userType.equals("TEACHER")) {
            Teacher teacher = teacherRepo.findByUserName(userName);
            if (validateUser(teacher, result)) {
                return result[0];
            }
        }

        return result[0];
    }

}
