package com.project.polyclinic.service;

import com.project.polyclinic.models.User;
import com.project.polyclinic.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    public UserService userService;

    public int getUserId(Authentication authentication) {
        if (authentication == null)
            return 0;
        else
            return ((User)loadUserByUsername(authentication.getName())).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
    public User getUserByUserId (int userId) {
        return userRepository.findById(userId);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Transactional
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }
}
