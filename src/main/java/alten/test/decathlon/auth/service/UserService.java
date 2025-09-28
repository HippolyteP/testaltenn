package alten.test.decathlon.auth.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import alten.test.decathlon.auth.entity.User;
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
    public User saveUser(String username, String rawPassword, String email, String firstname) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(rawPassword);
        user.setemail(email);
        user.setfirstname(firstname);
        return userRepository.save(user);
    }
/**
 * 
 * @param email
 * @return return the user with the email
 */
    public User findByEmail (String email){
        return userRepository.findByEmail(email).orElse(null);
    }
/**
 * 
 * @param rawPassword
 * @param encodedPassword
 * @return check the password
 */
    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }



}
