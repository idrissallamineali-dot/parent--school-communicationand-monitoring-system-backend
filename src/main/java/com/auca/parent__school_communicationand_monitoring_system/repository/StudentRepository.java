package com.auca.parent__school_communicationand_monitoring_system.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auca.parent__school_communicationand_monitoring_system.domain.Student;

public interface StudentRepository extends JpaRepository<Student, UUID> {

}
