package com.rubberduck.RubberDuckWebService.model;

import javax.persistence.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Entity
@Table(name = "Student")
public class Student extends User {

    public Student() {
    }

    public Student(String firstName, String lastName, String userName, String password) {
        super(firstName, lastName, userName, password);
    }
}
