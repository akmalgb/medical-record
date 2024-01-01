package org.example.controller;

import org.example.repository.MedicalConditionRepository;
import org.example.repository.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicalCondition")
public class MedicalConditionController {
    private final UserRepository userRepository;

    private final MedicalConditionRepository medicalConditionRepository;

    public MedicalConditionController(UserRepository userRepository, MedicalConditionRepository medicalConditionRepository) {
        this.userRepository = userRepository;
        this.medicalConditionRepository = medicalConditionRepository;
    }

}
