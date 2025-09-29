package alten.test.decathlon.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import alten.test.decathlon.api.ApiResponse;
import alten.test.decathlon.auth.dto.AccountRequest;
import alten.test.decathlon.auth.dto.AccountResponse;
import alten.test.decathlon.auth.dto.TokenRequest;
import alten.test.decathlon.auth.entity.User;
import alten.test.decathlon.auth.exceptions.InvalidCredentialException;
import alten.test.decathlon.auth.service.UserService;
import alten.test.decathlon.auth.utils.JwtUtils;
import jakarta.validation.Valid;

import java.net.URI;
import java.util.Map;

import org.springframework.http.HttpStatus;
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
    public ResponseEntity<AccountResponse> account(@Valid @RequestBody AccountRequest request) {
      return new ResponseEntity<>(userService.createAccount(request.getUsername(), request.getPassword(), request.getEmail(), request.getFirstname()), HttpStatus.CREATED);
    }

    @PostMapping("/token")
    public ResponseEntity<String> token(@Valid @RequestBody TokenRequest request) {
 
      User user = userService.findByEmail(request.getEmail());
      userService.checkPassword(request.getPassword(), user.getPassword());
      return new ResponseEntity<>(jwtUtils.generateToken(request.getEmail()),HttpStatus.CREATED);
    }
}
