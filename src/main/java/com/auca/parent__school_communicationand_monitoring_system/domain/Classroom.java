package com.auca.parent__school_communicationand_monitoring_system.domain;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "classroom")
public class Classroom {

    @Id 
@GeneratedValue(strategy = GenerationType.UUID)
UUID id;

int roomNumber;
String name;


}
