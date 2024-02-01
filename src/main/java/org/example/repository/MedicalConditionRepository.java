package org.example.repository;

import org.example.model.MedicalCondition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalConditionRepository extends JpaRepository<MedicalCondition, Long> {
    List<MedicalCondition> findByUserId(Long userId);
}
