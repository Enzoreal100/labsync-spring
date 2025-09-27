package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.StockDTO;
import com.repository.StockRepository;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;


    public List<StockDTO> getAllItems(int labId){
        return stockRepository.findAllByLabId(labId);
    }
}
