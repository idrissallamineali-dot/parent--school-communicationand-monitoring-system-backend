package com.auca.parent__school_communicationand_monitoring_system.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.auca.parent__school_communicationand_monitoring_system.domain.Classroom;

public interface ClassroomRepository extends JpaRepository<Classroom, UUID> {

    Optional<Classroom> findByRoomNumber(int roomNumber);

    @Query("SELECT c FROM Classroom c WHERE NOT EXISTS (SELECT t FROM Teacher t WHERE t.classroom = c) ORDER BY c.roomNumber ASC")
    List<Classroom> findFreeClassrooms();

}
