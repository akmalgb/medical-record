package org.example.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String nik;
    private String name;
    private String username;
    private String address;
    private String dob;
    private Integer weight;
    private Integer height;
    private Boolean isHasInsurance;
    private String insuranceCarrier;
    private String policyNumber;
    private String emergencyContactNumber;
    private String emergencyContactName;
    private List<MedicalConditionDto> medicalConditions;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MedicalConditionDto {

        private Long id;
        private String doctorName;
        private String speciality;
        private String phone;
        private String notes;
    }

}
