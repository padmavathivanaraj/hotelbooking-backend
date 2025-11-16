package com.example.hotelbooking.controller;

import com.example.hotelbooking.dto.LoginRequest;
import com.example.hotelbooking.dto.RegisterRequest;
import com.example.hotelbooking.dto.JwtResponse;
import com.example.hotelbooking.entity.Role;
import com.example.hotelbooking.entity.User;
import com.example.hotelbooking.repository.RoleRepository;
import com.example.hotelbooking.repository.UserRepository;
import com.example.hotelbooking.security.JwtUtils;


import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173") // frontend later use panna ready
public class AuthController {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public AuthController(UserRepository userRepo,
                          RoleRepository roleRepo,
                          PasswordEncoder passwordEncoder,
                          AuthenticationManager authenticationManager,
                          JwtUtils jwtUtils) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    private ResponseEntity<Object> badRequest(String message) {
        return ResponseEntity.badRequest().body(message);
    }

    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        if (req.getUsername() == null || req.getPassword() == null || req.getEmail() == null) {
            return badRequest("username, email and password are required");
        }

        if (userRepo.existsByUsername(req.getUsername())) {
            return badRequest("username taken");
        }

        if (userRepo.existsByEmail(req.getEmail())) {
            return badRequest("email already in use");
        }

        User u = new User();
        u.setUsername(req.getUsername());
        u.setEmail(req.getEmail());
        u.setPassword(passwordEncoder.encode(req.getPassword()));

        Optional<Role> maybeRole = roleRepo.findByName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        if (maybeRole.isPresent()) {
            roles.add(maybeRole.get());
        } else {
            Role userRole = new Role();
            userRole.setName("ROLE_USER");
            roleRepo.save(userRole);
            roles.add(userRole);
        }
        u.setRoles(roles);

        userRepo.save(u);
        return ResponseEntity.ok("User registered successfully");
    }

    // ---------- LOGIN ----------
    @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequest req) {
    if (req.getUsername() == null || req.getPassword() == null) {
        return badRequest("username and password required");
    }

    Authentication auth;
    try {
        auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
        );
    } catch (Exception ex) {
        // wrong username/password
        return ResponseEntity.status(401).body("Invalid username or password");
    }

    SecurityContextHolder.getContext().setAuthentication(auth);

    String jwt = jwtUtils.generateJwtToken(auth);

    Long userId = null;
    String email = null;
    Object principal = auth.getPrincipal();

    if (principal instanceof com.example.hotelbooking.security.UserDetailsImpl ud) {
        userId = ud.getId();
        email = ud.getEmail();
    }

    List<String> roles = auth.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());

    JwtResponse resp = new JwtResponse(jwt, userId, auth.getName(), email, roles);
    return ResponseEntity.ok(resp);
}
}
