package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.entity.OperationLogs;

public interface OperationLogsRepository extends JpaRepository<OperationLogs, Long>{
    public List <OperationLogs> findByUserLabId( int idLab);
    
    @Query("SELECT COUNT(ol) FROM OperationLogs ol")
    long countAll();
}
