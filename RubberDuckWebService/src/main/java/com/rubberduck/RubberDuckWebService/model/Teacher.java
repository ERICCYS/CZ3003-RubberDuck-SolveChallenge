package com.rubberduck.RubberDuckWebService.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Teacher")
public class Teacher extends User {

    @Column(name = "TITLE")
    private String title;

    public Teacher() {
    }


    public Teacher(String firstName, String lastName, String userName, String password, String title) {
        super(firstName, lastName, userName, password);
        this.title = title;
    }


    @Override
    public String toString() {
        return "Teacher{" +
                "title='" + title + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
