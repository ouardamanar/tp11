package ma.fstg.security.spring_security_jpa.repository;

import ma.fstg.security.spring_security_jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}