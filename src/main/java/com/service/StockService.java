package com.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.ItemsLog;
import com.dto.StockDTO;
import com.dto.TakeDTO;
import com.dto.TakeItemsDTO;
import com.entity.OperationLogs;
import com.entity.Stock;
import com.entity.User;
import com.repository.StockRepository;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private OperationLogsService operationLogsService;


    public List<StockDTO> getAllItems(int labId){
        return stockRepository.findAllByLabId(labId);
    }

    public TakeDTO takeItemsFromStock(TakeDTO takeData){
        takeData.getItems().forEach(item -> {
            Stock stock = stockRepository.findById(item.getId()).get();
            stock.setQuantity(stock.getQuantity() - item.getTakeQuantity());
            stockRepository.save(stock);
        });
        setTakeLog(takeData);
        return takeData;
    }

    private void setTakeLog(TakeDTO takeData){
        ArrayList<ItemsLog> items = mapToItemsLog(takeData.getItems());
        User user = userService.findById(takeData.getUserId());
        OperationLogs log = new OperationLogs(
            user,
            "Take",
            items
        );
        operationLogsService.createLog(log);
    }

    private ArrayList<ItemsLog> mapToItemsLog(List<TakeItemsDTO> batch){
        ArrayList<ItemsLog> items = new ArrayList<ItemsLog>();
        batch.forEach(item -> {
            Stock stock = stockRepository.findById(item.getId()).get();
            ItemsLog itemLog = new ItemsLog(
                stock.getItem().getEanCode(),
                stock.getItem().getName(),
                item.getTakeQuantity()
            );
            items.add(itemLog);
        });
        return items;
    }
}
