package com.auca.parent__school_communicationand_monitoring_system.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.auca.parent__school_communicationand_monitoring_system.domain.Subject;

public interface SubjectRepository extends JpaRepository<Subject, UUID> {

    @Query("SELECT s FROM Subject s WHERE s.teachers IS EMPTY")
    List<Subject> findUnusedSubjects();

}
