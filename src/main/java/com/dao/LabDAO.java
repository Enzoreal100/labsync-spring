package com.dao;

import com.model.Lab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabDAO extends JpaRepository<Lab, Integer> {
    Lab findById(int id);
}