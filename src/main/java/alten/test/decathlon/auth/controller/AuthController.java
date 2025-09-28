package alten.test.decathlon.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import alten.test.decathlon.api.ApiResponse;
import alten.test.decathlon.auth.dto.AccountRequest;
import alten.test.decathlon.auth.dto.AccountResponse;
import alten.test.decathlon.auth.dto.TokenRequest;
import alten.test.decathlon.auth.entity.User;
import alten.test.decathlon.auth.service.UserService;
import alten.test.decathlon.auth.utils.JwtUtils;

import java.net.URI;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthController {

    
    private final UserService userService;
    private final JwtUtils jwtUtils;

        public AuthController(JwtUtils jwtUtils, UserService userService) {
        this.jwtUtils = jwtUtils;
        this.userService = userService;
    }

    @PostMapping("/account")
    public ResponseEntity<?> account(@RequestBody AccountRequest request) {
 
      if(userService.findByEmail(request.getEmail()) != null) {

        return ResponseEntity.badRequest().body("Username is already in use");
        
      }
      User userCreated = userService.saveUser(request.getUsername(), request.getPassword(), request.getEmail(), request.getFirstname());

     AccountResponse accountResponse = new AccountResponse(userCreated.getEmail(), userCreated.getPassword());
      URI location = URI.create("/account/" + userCreated.getId());
      return ResponseEntity.created(location).body(new ApiResponse(201,"User created successfully", accountResponse));
      
    }

    @PostMapping("/token")
    public ResponseEntity<?> token(@RequestBody TokenRequest request) {
 
      User user = userService.findByEmail(request.getEmail());
      if(user != null && userService.checkPassword(request.getPassword(), user.getPassword())){
        return ResponseEntity.ok( jwtUtils.generateToken(request.getEmail()));

      } else {
        return ResponseEntity.status(401).body(Map.of("error", "invalid credential"));
      }
    }
    
}
