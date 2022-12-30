package org.ait.project.template.modules.users.service.internal;

import org.ait.project.template.modules.users.model.entity.Users;
import org.ait.project.template.modules.users.model.repository.UsersRepository;
import org.ait.project.template.modules.users.service.delegate.UserDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UsersRepository repository;

    @Autowired
    private UserDelegate userDelegate;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user =  userDelegate.getUserByEmail(email);

        return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
