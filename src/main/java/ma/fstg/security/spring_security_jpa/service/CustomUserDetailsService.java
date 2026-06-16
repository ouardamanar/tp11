package ma.fstg.security.spring_security_jpa.service;

import ma.fstg.security.spring_security_jpa.repository.UserRepository;
// Importez explicitement votre entité (Remplacez .model.User par votre vrai package d'entité si nécessaire)
import ma.fstg.security.spring_security_jpa.entity.User;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. Ici, 'User' fait référence à votre entité JPA personnalisée
        User appUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé : " + username));

        // 2. On retourne le 'User' de Spring Security en utilisant son nom complet pour éviter le conflit
        return new org.springframework.security.core.userdetails.User(
                appUser.getUsername(),
                appUser.getPassword(),
                appUser.isActive(),
                true, true, true,
                appUser.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList())
        );
    }
}