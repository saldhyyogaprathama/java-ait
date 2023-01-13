package org.ait.project.template.modules.users.service.internal.impl;

import org.ait.project.template.modules.users.model.entity.Users;
import org.ait.project.template.modules.users.model.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UsersRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> user = repository.findByEmail(email);
        String emailUser = null;
        String passwordUser = null;

        if (user.isPresent()) {
            emailUser = user.get().getEmail();
            passwordUser = user.get().getPassword();
        }

        return new User(emailUser, passwordUser, new ArrayList<>());
    }
}
