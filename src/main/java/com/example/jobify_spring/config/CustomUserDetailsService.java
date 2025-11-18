package com.example.jobify_spring.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.jobify_spring.models.UserModel;
import com.example.jobify_spring.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    List<UserModel> users = userRepository.findAll();

    UserModel user = users.stream().filter(u -> u.getName().equalsIgnoreCase(username)).findAny()
        .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    return new User(user.getName(), "", new ArrayList<>());
  }
}
