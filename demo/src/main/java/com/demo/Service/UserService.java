package com.demo.Service;

import com.demo.Model.UserEntity;
import com.demo.Repository.UserRepository;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    };

    @NullMarked
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario No Encontrado " + userName));

        var authorities = user.getRoles().stream().map((role) -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());

        return new User(
                user.getUserName(),
                user.getUserPassword(),
                user.isUserEnable(),
                true, true, true,
                authorities
        );
    }
}
