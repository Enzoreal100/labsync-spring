package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import com.dto.OperationLogsDTO;
import com.dto.UserLogDTO;
import com.entity.OperationLogs;
import com.repository.OperationLogsRepository;

@Service
public class OperationLogsService {
    @Autowired
    OperationLogsRepository operationLogsRepository;

    public List<OperationLogsDTO> findAll(){
        return operationLogsRepository.findAllOrderByIdDesc().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public List<OperationLogsDTO> findByIdLab(int idLab){
        return operationLogsRepository.findByUserLabIdOrderByIdDesc(idLab).stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    private OperationLogsDTO convertToDTO(OperationLogs log) {
        UserLogDTO userLogDTO = new UserLogDTO(
            log.getUser().getId(),
            log.getUser().getName(),
            log.getUser().getPosition().getName(),
            log.getUser().getLab().getId()
        );
        return new OperationLogsDTO(
            log.getId(),
            userLogDTO,
            log.getOperationType(),
            log.getItem()
        );
    }

    public void createLog(OperationLogs log){
        operationLogsRepository.save(log);
    }
}
