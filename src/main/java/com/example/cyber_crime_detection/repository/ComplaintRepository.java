package com.example.cyber_crime_detection.repository;

import com.example.cyber_crime_detection.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    Optional<Complaint> findByGeneratedId(String generatedId);
    List<Complaint> findByStatus(String status);
}
