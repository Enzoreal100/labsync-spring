package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dto.StockDTO;
import com.dto.TakeDTO;
import com.entity.Stock;
import com.repository.StockRepository;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;


    public List<StockDTO> getAllItems(int labId){
        return stockRepository.findAllByLabId(labId);
    }

    public Optional<StockDTO> getStockByEanCodeAndLabId(String eanCode, int labId) {
        return stockRepository.findDtoByEanCodeAndLabId(eanCode, labId);
    }

    public List<TakeDTO> takeItemsFromStock(List<TakeDTO> batch){
        batch.forEach(item -> {
            Stock stock = stockRepository.findById(item.getId()).get();
            stock.setQuantity(stock.getQuantity() - item.getTakeQuantity());
            stockRepository.save(stock);
        });
        return batch;
    }
}
