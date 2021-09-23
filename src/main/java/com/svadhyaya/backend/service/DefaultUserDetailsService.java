package com.svadhyaya.backend.service;

import com.svadhyaya.backend.db.models.RoleModel;
import com.svadhyaya.backend.db.models.UserModel;
import com.svadhyaya.backend.db.models.enums.RolesEnum;
import com.svadhyaya.backend.repository.RoleRepository;
import com.svadhyaya.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DefaultUserDetailsService implements UserDetailsService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private Pbkdf2PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        UserModel user = getUserFromRepoWithName(username);
        if (user == null) throw new UsernameNotFoundException(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (RoleModel role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new UserModel(user.getUsername(), user.getPassword(), grantedAuthorities, user.getRoles());
    }

    public UserModel getUserFromRepoWithName(String username) {
        return userRepository.findByUsername(username);
    }

    public void save(UserModel user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(
                Stream.of(roleRepository.findByName(RolesEnum.USER.name()))
                        .collect(Collectors.toSet())));
        userRepository.saveAndFlush(user);
    }

    public RoleRepository getRoleRepository() {
        return roleRepository;
    }

    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Pbkdf2PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public void setPasswordEncoder(Pbkdf2PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
