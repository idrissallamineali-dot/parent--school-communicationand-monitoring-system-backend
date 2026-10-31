package com.auca.parent__school_communicationand_monitoring_system.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.auca.parent__school_communicationand_monitoring_system.domain.Student;

public interface StudentRepository extends JpaRepository<Student, UUID> {

    List<Student> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);

    @Query("SELECT DISTINCT m.student FROM ParentMeeting m WHERE m.teacher.id = :teacherId")
    List<Student> findStudentsByTeacherId(@Param("teacherId") UUID teacherId);

}
