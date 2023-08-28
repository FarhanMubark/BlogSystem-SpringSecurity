package com.example.blogsystem.Service;

import com.example.blogsystem.Api.ApiException;
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

    public void updateUser(Integer id, User user){
        User user1 = authRepository.findUserById(id);

        if (user1 == null) {
            throw new ApiException("User Not found");
        }

        user1.setUsername(user.getUsername());
        user1.setPassword(user.getPassword());
        authRepository.save(user1);
    }

    public void deleteUser(Integer id){
        User user = authRepository.findUserById(id);
        if (user == null) {
            throw new ApiException("User Not found");
        }

        authRepository.delete(user);
    }

}