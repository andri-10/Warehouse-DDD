package com.andri.warehouse.infrastructure.security.service;

import com.andri.warehouse.domain.user.User;
import com.andri.warehouse.domain.user.UserRepository;
import com.andri.warehouse.domain.user.Username;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(Username.of(username))
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return new UserPrincipal(
                user.getUserId().getUserId(),
                user.getUsername().getUsername(),
                user.getEmail().getEmail(),
                user.getPassword().getHashedPassword(),
                mapRoleToAuthorities(user.getRole().toString())
        );
    }

    private Collection<? extends GrantedAuthority> mapRoleToAuthorities(String role) {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
    }

    public static class UserPrincipal implements UserDetails {
        private final String userId;
        private final String username;
        private final String email;
        private final String password;
        private final Collection<? extends GrantedAuthority> authorities;

        public UserPrincipal(String userId, String username, String email, String password,
                             Collection<? extends GrantedAuthority> authorities) {
            this.userId = userId;
            this.username = username;
            this.email = email;
            this.password = password;
            this.authorities = authorities;
        }

        public String getUserId() {
            return userId;
        }

        public String getEmail() {
            return email;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorities;
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public String getUsername() {
            return username;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}