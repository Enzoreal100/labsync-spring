package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.entity.OperationLogs;
import com.repository.OperationLogsRepository;

@Service
public class OperationLogsService {
    @Autowired
    OperationLogsRepository operationLogsRepository;

    public List<OperationLogs> findAll(){
        return operationLogsRepository.findAll();
    }

    public List<OperationLogs> findByIdLab(int idLab){
        return operationLogsRepository.findByUserLabId(idLab);
    }
}
