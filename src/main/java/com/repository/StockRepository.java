package com.repository;

import com.dto.StockDTO;
import com.entity.Stock;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long>{
    @Query("SELECT new com.dto.StockDTO(s.id, i.eanCode, i.name, s.quantity, s.minQuantity, s.lab.id) FROM Stock s LEFT JOIN s.item i WHERE s.lab.id = :labId")
    List<StockDTO> findAllByLabId(@Param("labId") int labId);
}
