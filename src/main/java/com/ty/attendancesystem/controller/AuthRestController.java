package com.ty.attendancesystem.controller;

import com.ty.attendancesystem.message.jwt.request.LoginRequest;
import com.ty.attendancesystem.message.jwt.request.RegisterRequest;
import com.ty.attendancesystem.message.jwt.response.JwtResponse;
import com.ty.attendancesystem.model.Role;
import com.ty.attendancesystem.constant.RoleName;
import com.ty.attendancesystem.model.User;
import com.ty.attendancesystem.security.jwt.JwtProvider;
import com.ty.attendancesystem.service.RoleService;
import com.ty.attendancesystem.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AuthRestController {
  private AuthenticationManager authenticationManager;

  private UserService userService;

  private RoleService roleService;

  private PasswordEncoder passwordEncoder;

  private JwtProvider jwtProvider;

  public AuthRestController(AuthenticationManager authenticationManager,
      UserService userService,
      RoleService roleService,
      PasswordEncoder passwordEncoder,
      JwtProvider jwtProvider){
    this.authenticationManager=authenticationManager;
    this.userService=userService;
    this.roleService=roleService;
    this.passwordEncoder=passwordEncoder;
    this.jwtProvider = jwtProvider;
  }

  @PostMapping("/auth")
  public ResponseEntity authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            loginRequest.getUsername(),
            loginRequest.getPassword()
        )
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = jwtProvider.generateJwtToken(authentication);
    return ResponseEntity.ok(new JwtResponse(jwt));
  }

  @PostMapping("/auth/signup")
  public ResponseEntity registerUser(@Valid @RequestBody RegisterRequest signUpRequest) {
    if(userService.existsByUsername(signUpRequest.getUsername())) {
      return new ResponseEntity("Fail -> Username is already taken!",
          HttpStatus.BAD_REQUEST);
    }

    if(userService.existsByEmail(signUpRequest.getEmail())) {
      return new ResponseEntity("Fail -> Email is already in use!",
          HttpStatus.BAD_REQUEST);
    }

    // Creating user's account
    User user = new User(signUpRequest.getUsername(), passwordEncoder.encode(signUpRequest.getPassword()),
        signUpRequest.getEmail());

    Set<String> strRoles = signUpRequest.getRoles();
    Set<Role> roles = new HashSet<>();

    strRoles.forEach(role -> {
      switch(role) {
        case "admin":
          Role adminRole = roleService.findByName(RoleName.ROLE_ADMIN.name())
              .orElseThrow(() -> new RuntimeException("Fail! -> Cause: Admin role is not found."));
          roles.add(adminRole);
          break;
        case "teacher":
          Role tRole = roleService.findByName(RoleName.ROLE_TEACHER.name())
              .orElseThrow(() -> new RuntimeException("Fail! -> Cause: Teacher role is not found."));
          roles.add(tRole);
          break;
        case "parent":
          Role prRole = roleService.findByName(RoleName.ROLE_PARENT.name())
              .orElseThrow(() -> new RuntimeException("Fail! -> Cause: Parent role is not found."));
          roles.add(prRole);
          break;
        default:
          Role stRole = roleService.findByName(RoleName.ROLE_STUDENT.name())
              .orElseThrow(() -> new RuntimeException("Fail! -> Cause: Student role is not found."));
          roles.add(stRole);
      }
    });

    user.setRoles(roles);
    userService.save(user);

    return ResponseEntity.ok().body("User registered successfully!");
  }
}
