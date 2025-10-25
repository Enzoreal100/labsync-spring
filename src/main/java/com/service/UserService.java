package com.service;

import com.dto.UserDTO;
import com.model.User;
import com.model.Position;
import com.model.Lab;
import com.dao.UserDAO;
import com.dao.PositionDAO;
import com.dao.LabDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserDAO userRepository;

    @Autowired
    private PositionDAO positionRepository;

    @Autowired
    private LabDAO labRepository;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<UserDTO> getUserByCardCode(String cardCode) {
        return userRepository.findByCardCode(cardCode)
                .map(this::convertToDTO);
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