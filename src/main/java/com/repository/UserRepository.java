package com.repository;

import com.dto.UserDTO;
import com.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByCardCode(String cardCode);
    Optional<User> findById(Integer id);
    @Query("SELECT new com.dto.UserDTO(u.id, u.name, u.position.id, u.lab.id) FROM User u WHERE u.lab.id = :labId")
    List<UserDTO> findUserByLabId(@Param("labId") Integer labId);
}