package com.antoniosgarbi.service;

import com.antoniosgarbi.dto.UserDTO;
import com.antoniosgarbi.entities.User;
import com.antoniosgarbi.exception.BadRequestException;
import com.antoniosgarbi.repository.UserRepository;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository repository;
    private final Argon2PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, Argon2PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserDTO insert(UserDTO dto) {
        String passwordEncoded = passwordEncoder.encode(dto.getPassword());
        User entity = new User(null, dto.getName(), dto.getLogin(), passwordEncoded);
        entity = repository.save(entity);
        return new UserDTO(entity);
    }

    public List<UserDTO> findAll() {
        List<User> list = repository.findAll();
        return list.stream().
                map(x -> new UserDTO(x)).collect(Collectors.toList());
    }

    @Transactional
    public UserDTO update(UserDTO dto) {
        User entity = new User();
        entity = repository.save(entity);
        return new UserDTO(entity);
    }

    @Transactional
    public void delete(Integer id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new BadRequestException("Id can't be found");
        }
    }
}
