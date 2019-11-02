package com.rubberduck.RubberDuckWebService.model;

import javax.persistence.*;

@Entity
@Table(name = "WorldQuestion")
public class WorldQuestion {

    @Id
    @Column(name = "WORLD_QN_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "world")
    private String world;

    @Column(name = "count")
    private Integer count;


    public WorldQuestion() {
    }

    public WorldQuestion(String world, Integer count) {
        this.world = world;
        this.count = count;
    }

    @Override
    public String toString() {
        return "WorldQuestion{" +
                "id=" + id +
                ", world='" + world + '\'' +
                ", count=" + count +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
