package org.example.repository;

import org.example.model.MedicalCondition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalConditionRepository extends JpaRepository<MedicalCondition, Long> {
}
