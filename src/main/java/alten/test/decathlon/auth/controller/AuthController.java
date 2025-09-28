package alten.test.decathlon.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import alten.test.decathlon.auth.entity.User;
import alten.test.decathlon.auth.service.UserService;
import alten.test.decathlon.auth.utils.JwtUtils;

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
    public ResponseEntity<?> account(@RequestBody User user) {
 
      if(userService.findByEmail(user.getemail()) != null) {

        return ResponseEntity.badRequest().body("Username is already in use");
        
      }
      return ResponseEntity.ok(userService.saveUser(user.getUsername(), user.getPassword(), user.getemail(), user.getfirstname()));
      
    }

    @PostMapping("/token")
    public ResponseEntity<?> token(@RequestBody User userBody) {
 
      User user = userService.findByEmail(userBody.getemail());
      if(user != null && userService.checkPassword(userBody.getPassword(), user.getPassword())){
        return ResponseEntity.ok( jwtUtils.generateToken(userBody.getUsername()));

      } else {
        return ResponseEntity.status(401).body(Map.of("error", "invalid credential"));
      }
    }
    
}
