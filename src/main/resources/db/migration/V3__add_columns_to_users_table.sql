ALTER TABLE users
add column medical_condition_id bigint null after id,
add column nik bigint(16) null after medical_condition_id,
add column address varchar(255) null after username,
add FOREIGN KEY (medical_condition_id) REFERENCES medical_conditions(id);

ALTER TABLE users ADD COLUMN(
    dob date null,
    weight int null,
    height int null,
    is_has_insurance bool null,
    insurance_carrier varchar(255) null,
    policy_number varchar(255) null,
    emergency_contact_number varchar(255) null,
    emergency_contact_name varchar(255) null
);