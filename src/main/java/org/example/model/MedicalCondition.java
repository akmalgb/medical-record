package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@Table(name = "medical_conditions")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MedicalCondition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

//    @Column(name = "user_id")
//    private Long userId;

    @Column(name = "doctor_name")
    private String doctorName;

    @Column(name = "speciality")
    private String speciality;

    @Column(name = "phone")
    private String phone;

    @Column(name = "notes")
    private String notes;
}
