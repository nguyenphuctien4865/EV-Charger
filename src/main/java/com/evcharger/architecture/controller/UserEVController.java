package com.evcharger.architecture.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.evcharger.architecture.model.ApiResponse;
import com.evcharger.architecture.model.UserEVDTO;
import com.evcharger.architecture.service.UserEVService;
import com.evcharger.architecture.service.UserService;

@RestController
@RequestMapping("/users")
public class UserEVController {

    @Autowired
    private UserEVService userService;

    @PostMapping
    public ResponseEntity<UserEVDTO> createUser(@Valid @RequestBody UserEVDTO userCreateDTO) {

        UserEVDTO user = new UserEVDTO();
        // Map DTO to entity
        user.setUserId(userCreateDTO.getUserId());
        user.setUsername(userCreateDTO.getUsername());
        user.setEmail(userCreateDTO.getEmail());
        user.setPassword(userCreateDTO.getPassword());
        user.setPhoneNumber(userCreateDTO.getPhoneNumber());
        user.setFavorites(userCreateDTO.getFavorites());

        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEVDTO> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEVDTO> updateUser(@PathVariable String id, @Valid @RequestBody UserEVDTO userUpdateDTO) {
        // Map DTO to entity
        return ResponseEntity.ok(userService.updateUser(id, userUpdateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<ApiResponse<UserEVDTO>> listUsers(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sort", defaultValue = "userId", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "ASC", required = false) String sortDir,
            @RequestParam(required = false, defaultValue = "") String username,
            @RequestParam(required = false, defaultValue = "") String email) {
        return ResponseEntity.ok(userService.listUsers(pageNo, pageSize, sortDir, sortBy, username, email));
    }
}