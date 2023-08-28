package com.example.blogsystem.Service;

import com.example.blogsystem.Model.User;
import com.example.blogsystem.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;

    public List<User> getAll(){
        return authRepository.findAll();
    }
    public void register(User user){
        // normal encrpting not hashing
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        user.setRole("USER");
        authRepository.save(user);
    }


}