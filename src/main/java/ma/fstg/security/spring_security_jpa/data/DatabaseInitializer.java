package ma.fstg.security.spring_security_jpa.data;

import ma.fstg.security.spring_security_jpa.entity.Role;
import ma.fstg.security.spring_security_jpa.entity.User;
import ma.fstg.security.spring_security_jpa.repository.RoleRepository;
import ma.fstg.security.spring_security_jpa.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class DatabaseInitializer {

    @Bean
    CommandLineRunner init(RoleRepository roleRepo, UserRepository userRepo, BCryptPasswordEncoder encoder) {
        return args -> {
            Role adminRole = roleRepo.save(new Role(null, "ROLE_ADMIN"));
            Role userRole = roleRepo.save(new Role(null, "ROLE_USER"));

            User admin = new User(null, "admin", encoder.encode("1234"), true, List.of(adminRole, userRole));
            User user = new User(null, "user", encoder.encode("1111"), true, List.of(userRole));

            userRepo.saveAll(List.of(admin, user));
        };
    }
}