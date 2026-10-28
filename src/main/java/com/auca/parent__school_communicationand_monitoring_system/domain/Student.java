package com.auca.parent__school_communicationand_monitoring_system.domain;

import java.lang.annotation.Inherited;
import java.util.UUID;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table (name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID   id;
   
    private String firstName;   
    private String lastName;
    
    @Column (unique = true)
    private String regNumber;
    private String parentName;
    private String parentPhone;

}
