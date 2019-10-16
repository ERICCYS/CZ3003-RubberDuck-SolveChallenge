package com.rubberduck.RubberDuckWebService.model;

import javax.persistence.*;

@Entity
@Table(name = "Teacher")
public class Teacher {

    @Id
    @Column(name = "ID", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
