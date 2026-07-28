package com.priyanshu.roleradar.service.impl;

import com.priyanshu.roleradar.dto.UserRequestDTO;
import com.priyanshu.roleradar.dto.UserResponseDTO;
import com.priyanshu.roleradar.entity.User;
import com.priyanshu.roleradar.exception.UserNotFoundException;
import com.priyanshu.roleradar.mapper.UserMapper;
import com.priyanshu.roleradar.repository.UserRepository;
import com.priyanshu.roleradar.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDTO registerUser(UserRequestDTO requestDTO) {

        User user = UserMapper.toEntity(requestDTO);

        // Encrypt password before saving
        user.setPassword(passwordEncoder.encode(requestDTO.getPassword()));

        User savedUser = userRepository.save(user);

        return UserMapper.toResponseDTO(savedUser);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(UserMapper::toResponseDTO)
                .toList();
    }

    @Override
    public UserResponseDTO getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with id: " + id));

        return UserMapper.toResponseDTO(user);
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO requestDTO) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with id: " + id));

        existingUser.setFullName(requestDTO.getFullName());
        existingUser.setEmail(requestDTO.getEmail());

        // Encrypt updated password
        existingUser.setPassword(
                passwordEncoder.encode(requestDTO.getPassword())
        );

        existingUser.setRole(requestDTO.getRole());

        User updatedUser = userRepository.save(existingUser);

        return UserMapper.toResponseDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with id: " + id));

        userRepository.delete(existingUser);
    }
}