package org.example.controller;

import org.example.dto.ResponseDto;
import org.example.dto.UserDto;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.example.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;


    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseDto<List<User>>> getAllUsers() {
        try {
            List<User> users = new ArrayList<>(userRepository.findAll());

            if (users.isEmpty()) {
                return ResponseUtil.notFound();
            }

            return ResponseUtil.success(HttpStatus.OK.value(), "data found", users);
        } catch (Exception e) {
            return ResponseUtil.internalServerError();
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
            User user  = new User();
            user.setName(req.getName());
            user.setUsername(req.getUsername());
            User _user = userRepository.save(user);
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
}
