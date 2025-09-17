package com.evcharger.architecture.controller;

import com.evcharger.architecture.entity.Role;
import com.evcharger.architecture.entity.UserEV;
import com.evcharger.architecture.exception.common.ResourceNotFoundException;
import com.evcharger.architecture.model.auth.JwtResponse;
import com.evcharger.architecture.model.auth.LoginDTO;
import com.evcharger.architecture.model.auth.RegisterDTO;
import com.evcharger.architecture.repository.RoleRepository;
import com.evcharger.architecture.repository.UserEVRepository;
import com.evcharger.architecture.util.JwtUtils;
import com.evcharger.architecture.util.enums.ERole;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserEVRepository userEVRepository;

    private final RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    private JwtUtils jwtUtils;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserEVRepository userEVRepository,
            RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userEVRepository = userEVRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginDTO loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword()));
        if (authentication == null) {
            throw new ResourceNotFoundException("User not found with username: ", "Username",
                    loginRequest.getUsername());
        }
        UserEV user = userEVRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: ", "Username",
                        loginRequest.getUsername()));
        String jwt = jwtUtils.generateToken(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);


        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(JwtResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .token(jwt)
                .roles(roles).build());

    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterDTO registerDTO) {

        if (userEVRepository.existsByUsername(registerDTO.getUsername())) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }
        if (userEVRepository.existsByEmail(registerDTO.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }
        if (userEVRepository.existsByPhoneNumber(registerDTO.getPhoneNumber())) {
            return ResponseEntity.badRequest().body("Error: Phone number is already in use!");
        }

        UserEV user = UserEV.builder()
                .username(registerDTO.getUsername())
                .email(registerDTO.getEmail())
                .password(passwordEncoder.encode(registerDTO.getPassword()))
                .phoneNumber(registerDTO.getPhoneNumber())
                .favorites(registerDTO.getFavorites())
                .build();

        // Set<Role> roles = registerDTO.getRoles();
        Set<Role> defaultRoles = new HashSet<>();

        Role userRole = roleRepository.findByName(ERole.ROLE_USER.toString())
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        defaultRoles.add(userRole);

        user.setRoles(defaultRoles);
        userEVRepository.save(user);

        return ResponseEntity.ok("User registered successfully!");
    }
}
