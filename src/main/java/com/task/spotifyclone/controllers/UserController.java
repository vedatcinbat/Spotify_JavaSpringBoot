package com.task.spotifyclone.controllers;


import com.task.spotifyclone.models.DTOs.UserDTO;
import com.task.spotifyclone.models.Song;
import com.task.spotifyclone.models.User;
import com.task.spotifyclone.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Create a new user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.createUser(user);
        return ResponseEntity.ok(savedUser);
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> userDTOs = userService.getAllUsers();
        return ResponseEntity.ok(userDTOs);
    }

    // Get a single user by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO UserDto = userService.findUserById(id);
        return ResponseEntity.ok(UserDto);
    }

    // Update a user
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(id, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    // Delete a user
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }


    // Like a song
    @PostMapping("/{userId}/like/{songId}")
    public ResponseEntity<Void> likeSong(@PathVariable Long userId, @PathVariable Long songId) {
        userService.likeSong(userId, songId);
        return ResponseEntity.ok().build();
    }

    // Unlike a song
    @PostMapping("/{userId}/unlike/{songId}")
    public ResponseEntity<Void> unlikeSong(@PathVariable Long userId, @PathVariable Long songId) {
        userService.unlikeSong(userId, songId);
        return ResponseEntity.ok().build();
    }
}
