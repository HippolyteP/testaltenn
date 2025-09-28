package alten.test.decathlon.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import alten.test.decathlon.auth.entity.User;

public interface UserRepository extends JpaRepository<User, Long>
 {
    Optional<User> findByEmail(String username);

}
