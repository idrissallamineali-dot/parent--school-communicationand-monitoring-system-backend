package com.auca.parent__school_communicationand_monitoring_system.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Id;

@Entity
@Table (name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    private String name;

    @ManyToMany(mappedBy = "subjects")
    @JsonIgnore
    private List<Teacher> teachers;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }



}
