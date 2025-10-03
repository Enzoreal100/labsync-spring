package com.service;

import com.dto.UserDTO;
import com.entity.User;
import com.entity.Position;
import com.entity.Lab;
import com.repository.UserRepository;
import com.repository.PositionRepository;
import com.repository.LabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private LabRepository labRepository;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<UserDTO> getUserByCardCode(String cardCode) {
        return userRepository.findByCardCode(cardCode)
                .map(this::convertToDTO);
    }

    public User findById(int id){
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public UserDTO createUser(UserDTO userDTO){
        User user = convertFromDTO(userDTO);
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    private UserDTO convertToDTO(User user) {
        UserDTO newUserDTO = new UserDTO(user.getId(), user.getName(), user.getPosition().getId(), user.getLab().getId(), user.getCardCode());
        return newUserDTO;
    }

    private User convertFromDTO(UserDTO user){
        Position position = positionRepository.findById(user.getPosition()).orElse(null);
        Lab lab = labRepository.findById(user.getLab());
        return new User(user.getName(), position, lab, user.getCardCode());
    }
}