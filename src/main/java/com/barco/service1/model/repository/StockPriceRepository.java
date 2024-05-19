package com.barco.service1.model.repository;

import com.barco.service1.model.pojo.stock.StockPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Nabeel Ahmed
 */
@Repository
public interface StockPriceRepository extends JpaRepository<StockPrice, Long> {
}
