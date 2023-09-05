package com.dev.airlinereservationsystem.service;

import com.dev.airlinereservationsystem.dto.UserDto;
import com.dev.airlinereservationsystem.entity.User;
import com.dev.airlinereservationsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public String registerUser(UserDto userDto){
        if(userRepository.existsByUsername(userDto.getUsername())){
            return "Username is already taken";
        }
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
        return "register successfully!";
    }
}
