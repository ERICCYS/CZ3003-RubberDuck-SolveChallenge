package com.rubberduck.RubberDuckWebService.model;

import javax.persistence.*;

@Entity
@Table(name = "Student")
public class Student extends User {

    @Column(name = "MARK", nullable = false)
    private int mark;

    public Student() {
    }

    public Student(String firstName, String lastName, String userName, String password, int mark) {
        super(firstName, lastName, userName, password);
        this.mark = mark;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public void addMark(int mark) {
        this.mark = this.mark + mark;
    }
}
