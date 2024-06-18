package com.mcb.controllers;


import com.mcb.dto.UserDTO;
import com.mcb.dto.mapper.UserMapper;
import com.mcb.manager.UserService;
import com.mcb.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> addUser(@RequestBody UserDTO userDto) throws Exception{
        try {

            User user = userService.saveUser(UserMapper.toEntity(userDto));
            if (user == null){
                throw new Exception("Error to create  found " + userDto.toString());
            }else{
                return ResponseEntity.ok(user);
            }

        }catch (IllegalArgumentException e) {
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = userService.findAll();
        List<UserDTO> userDTOs = users.stream().map(UserMapper::toDTO).toList();
        return ResponseEntity.ok(userDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        return user.map(value -> ResponseEntity.ok(UserMapper.toDTO(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Optional<User> existingUser = userService.findById(id);
        if (existingUser.isPresent()) {
            userService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserDTO userDto) {
        Optional<User> existingCustomer = userService.findById(id);
        if (existingCustomer.isPresent()) {
            User user = UserMapper.upDate(existingCustomer.get(),userDto);
            User updatedUser = userService.saveUser(user);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/addFile/{idUser}/{fileName}")
    public ResponseEntity<User> addFile(@PathVariable Long idUser, @PathVariable String fileName) {
        Optional<User> existingUser = userService.findById(idUser);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.addFile(fileName);
            User updatedUser = userService.saveUser(user);
            System.out.println("sauvegarde effectuer");
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}