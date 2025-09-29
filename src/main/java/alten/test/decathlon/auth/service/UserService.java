package alten.test.decathlon.auth.service;


import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import alten.test.decathlon.auth.dto.AccountResponse;
import alten.test.decathlon.auth.entity.User;
import alten.test.decathlon.auth.exceptions.InvalidCredentialException;
import alten.test.decathlon.auth.exceptions.UserCreationException;
import alten.test.decathlon.auth.exceptions.UserNotFoundException;
import alten.test.decathlon.auth.repository.UserRepository;

@Service
public class UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

        public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
/**
 * 
 * @param username
 * @param rawPassword
 * @param email
 * @param firstname
 * @return save one user
 */
    public AccountResponse createAccount(String username, String rawPassword, String email, String firstname) {
    if(userRepository.findByEmail(email).orElse(null) != null) {
        throw new UserCreationException(email);    
      }
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setEmail(email);
        user.setFirstname(firstname);
        userRepository.save(user);
        return new AccountResponse(user.getEmail(), user.getPassword());
    }
/**
 * 
 * @param email
 * @return return the user with the email
 */
    public User findByEmail (String email){
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
    }
/**
 * 
 * @param rawPassword
 * @param encodedPassword
 * @return check the password
 */
    public boolean checkPassword(String rawPassword, String encodedPassword) {
        boolean isChecked = passwordEncoder.matches(rawPassword, encodedPassword);
        if (isChecked) {
            return isChecked;
        } else {
            throw new InvalidCredentialException();
        }
    }



}
