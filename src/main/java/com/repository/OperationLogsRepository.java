package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.entity.OperationLogs;

public interface OperationLogsRepository extends JpaRepository<OperationLogs, Long>{
    @Query("SELECT ol FROM OperationLogs ol ORDER BY ol.id DESC")
    public List <OperationLogs> findByUserLabIdOrderByIdDesc( int idLab);

    @Query("SELECT ol FROM OperationLogs ol ORDER BY ol.id DESC")
    public List <OperationLogs> findAllOrderByIdDesc();
    
    @Query("SELECT COUNT(ol) FROM OperationLogs ol")
    long countAll();


}
