package com.auca.parent__school_communicationand_monitoring_system.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.auca.parent__school_communicationand_monitoring_system.domain.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, UUID> {

    @Query("SELECT t FROM Teacher t JOIN t.subjects s WHERE LOWER(s.name) = LOWER(:name)")
    List<Teacher> findTeachersBySubjectName(@Param("name") String name);

}
