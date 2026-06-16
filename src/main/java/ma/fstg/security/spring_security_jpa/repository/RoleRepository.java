package ma.fstg.security.spring_security_jpa.repository;


import ma.fstg.security.spring_security_jpa.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}