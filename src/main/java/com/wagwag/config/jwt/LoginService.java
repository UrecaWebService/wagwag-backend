package com.wagwag.config.jwt;


import com.wagwag.user.application.UserRepository;
import com.wagwag.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginService{

    private final UserRepository userRepository;

    public User findUser(Long id){
        return userRepository.findById(id);
    }
}