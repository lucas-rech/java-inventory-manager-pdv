package edu.ifrs.si.inventorymanagerpdv.service;

import edu.ifrs.si.inventorymanagerpdv.config.exceptions.NullableUserException;
import edu.ifrs.si.inventorymanagerpdv.config.exceptions.UsernameExistsException;
import edu.ifrs.si.inventorymanagerpdv.model.User;
import edu.ifrs.si.inventorymanagerpdv.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomUserDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User user = userRepository.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException(username);
            }

            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.username())
                    .password(user.password())
                    .roles(String.valueOf(user.role()))
                    .build();
    }

    public User create(User newUser) {
        if (newUser == null) {
            throw new NullableUserException("creating newUser is null");
        }
        if (userRepository.findByUsername(newUser.username()) != null) {
            throw new UsernameExistsException(newUser.username());
        }
        User createdUser = new User(
                null,
                newUser.name(),
                newUser.username(),
                newUser.phoneNumber(),
                newUser.role(),
                passwordEncoder.encode(newUser.password()),
                LocalDateTime.now().withNano(0),
                LocalDateTime.now().withNano(0),
                false
        );

        return userRepository.save(createdUser);
    }
}
