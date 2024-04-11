package com.springapi.donatecharity.service.user;

import com.springapi.donatecharity.models.User;
import com.springapi.donatecharity.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public  CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()){
            log.error("custom user details service - error not found username: "+username);
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user.get());
    }

    public UserDetails loadUserByUserId(int userId) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()){
            log.error("custom user details service - error not found user Id: "+userId);
            throw new UsernameNotFoundException(""+userId);
        }
        return new CustomUserDetails(user.get());
    }
}
