ALTER TABLE users
drop foreign key users_ibfk_1,
drop column medical_condition_id;

ALTER TABLE medical_conditions
add column user_id bigint null after id,
add FOREIGN KEY (user_id) REFERENCES users(id);