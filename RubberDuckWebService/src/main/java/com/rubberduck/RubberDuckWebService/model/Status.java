package com.rubberduck.RubberDuckWebService.model;

import javax.persistence.*;

@Entity
@Table(name = "Status")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "STUDENT_ID", nullable = false)
    private Long studentId;

    @Column(name = "CHARACTER_CHOICE", nullable = false)
    private String character;

    @Column(name = "WORLD", nullable = false)
    private String world;

    @Column(name = "SECTION", nullable = false)
    private String section;

    @Column(name = "LEVEL", nullable = false)
    private String level;

    public Status() {
    }

    public Status(Long studentId, String character, String world, String section, String level) {
        this.studentId = studentId;
        this.character = character;
        this.world = world;
        this.section = section;
        this.level = level;
    }

    public Status(Status that, Long id) {
        this(that.getStudentId(), that.getCharacter(), that.getWorld(), that.getSection(), that.getLevel());
        this.setId(id);
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", character='" + character + '\'' +
                ", world='" + world + '\'' +
                ", section='" + section + '\'' +
                ", level='" + level + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
