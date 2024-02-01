package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.ResponseDto;
import org.example.dto.UserDto;
import org.example.model.MedicalCondition;
import org.example.model.User;
import org.example.repository.MedicalConditionRepository;
import org.example.repository.UserRepository;
import org.example.util.DateUtil;
import org.example.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    private final MedicalConditionRepository medicalConditionRepository;

    public UserController(UserRepository userRepository, MedicalConditionRepository medicalConditionRepository) {
        this.userRepository = userRepository;
        this.medicalConditionRepository = medicalConditionRepository;
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        try {
            var listUsers = userRepository.findAll();

            List<UserDto> users =
                    listUsers.stream()
                            .map(data -> {
                                String jsonAllergies = data.getAllergies();
                                ObjectMapper om = new ObjectMapper();

                                List<Object> yourObjectList;
                                try {
                                    yourObjectList = om.readValue(jsonAllergies, new TypeReference<List<Object>>() {});
                                } catch (JsonProcessingException e) {
                                    throw new RuntimeException(e);
                                }

                                List<UserDto.MedicalConditionDto> medicalConditions =
                                        data.getMedicalConditions()
                                                .stream()
                                                .map(medicalCondition
                                                        -> UserDto.MedicalConditionDto.builder()
                                                        .id(medicalCondition.getId())
                                                        .doctorName(medicalCondition.getDoctorName())
                                                        .speciality(medicalCondition.getSpeciality())
                                                        .phone(medicalCondition.getPhone())
                                                        .notes(medicalCondition.getNotes())
                                                        .build())
                                                .toList();

                                return UserDto.builder()
                                        .id(data.getId())
                                        .nik(data.getNik())
                                        .name(data.getName())
                                        .username(data.getUsername())
                                        .address(data.getAddress())
                                        .dob(String.valueOf(data.getDob()))
                                        .weight(data.getWeight())
                                        .height(data.getHeight())
                                        .isHasInsurance(data.getIsHasInsurance())
                                        .insuranceCarrier(data.getInsuranceCarrier())
                                        .policyNumber(data.getPolicyNumber())
                                        .emergencyContactNumber(data.getEmergencyContactNumber())
                                        .emergencyContactName(data.getEmergencyContactName())
                                        .medicalConditions(medicalConditions)
                                        .allergies(yourObjectList)
                                        .build();
                            })
                            .toList();

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<ResponseDto<User>> getUserById(@PathVariable("id") long id) {
        Optional<User> userData = userRepository.findById(id);

        if (userData.isPresent()) {
            return ResponseUtil.success(HttpStatus.OK.value(), "success", userData.get());
        } else {
            return ResponseUtil.notFound();
        }
    }

    @PostMapping("/store")
    public ResponseEntity<ResponseDto<User>> createUser(@RequestBody UserDto req) {
        try {
            User user = new User();
            user.setNik(req.getNik());
            user.setName(req.getName());
            user.setUsername(req.getUsername());
            user.setAddress(req.getAddress());
            user.setDob(DateUtil.parseDate(req.getDob()));
            user.setWeight(req.getWeight());
            user.setHeight(req.getHeight());
            user.setIsHasInsurance(req.getIsHasInsurance());
            user.setInsuranceCarrier(req.getInsuranceCarrier());
            user.setPolicyNumber(req.getPolicyNumber());
            user.setEmergencyContactNumber(req.getEmergencyContactNumber());
            user.setEmergencyContactName(req.getEmergencyContactName());
            User _user = userRepository.save(user);

            List<MedicalCondition> medicalConditions = req.getMedicalConditions().stream()
                    .map(medicalConditionDto -> {
                        MedicalCondition medicalCondition = new MedicalCondition();
                        medicalCondition.setUser(_user);
                        medicalCondition.setDoctorName(medicalConditionDto.getDoctorName());
                        medicalCondition.setSpeciality(medicalConditionDto.getSpeciality());
                        medicalCondition.setPhone(medicalConditionDto.getPhone());
                        medicalCondition.setNotes(medicalConditionDto.getNotes());
                        return medicalCondition;
                    })
                    .collect(Collectors.toList());

            medicalConditionRepository.saveAll(medicalConditions);

            return ResponseUtil.success(HttpStatus.CREATED.value(), "data created", _user);
        } catch (Exception e) {
            return ResponseUtil.internalServerError();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto<User>> deleteUser(@PathVariable("id") long id) {
        try {
            Optional<User> userData = userRepository.findById(id);
            if (userData.isPresent()) {
                var user = userData.get();
                userRepository.deleteById(id);
                return ResponseUtil.success(HttpStatus.OK.value(), "data deleted", user);
            } else {
                return ResponseUtil.notFound();
            }
        } catch (Exception e) {
            return ResponseUtil.internalServerError();
        }
    }

    @GetMapping("/show-nik/{nik}")
    public ResponseEntity<ResponseDto<User>> getUserByNik(@PathVariable("nik") String nik) {
        try {
            // Assuming 'nik' is a unique identifier in your User entity
            Optional<User> userData = userRepository.findByNik(nik);

            if (userData.isPresent()) {
                var user = userData.get();
                return ResponseUtil.success(HttpStatus.OK.value(), "User data retrieved", user);
            } else {
                return ResponseUtil.notFound();
            }
        } catch (Exception e) {
            return ResponseUtil.internalServerError();
        }
    }
}
