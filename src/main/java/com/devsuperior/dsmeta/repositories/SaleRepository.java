package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.devsuperior.dsmeta.dto.SumSalesDTO;
import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
	
    @Query("SELECT s FROM Sale s " +
           "WHERE (:name = '' OR UPPER(s.seller.name) LIKE UPPER(CONCAT('%', :name, '%'))) AND " +
           "(s.date >= :minDate) AND " +
           "(s.date <= :maxDate) " +
           "ORDER BY s.amount")
     Page<Sale> searchSales(String name, LocalDate minDate, LocalDate maxDate, Pageable pageable);
    
    @Query("SELECT new com.devsuperior.dsmeta.dto.SumSalesDTO(s.seller.name, SUM(s.amount)) " +
            "FROM Sale s " +
            "WHERE (s.date >= :minDate) AND " +
            "(s.date <= :maxDate) " +
            "GROUP BY s.seller.name " +
            "ORDER BY s.seller.name")
     Page<SumSalesDTO> sumSales(LocalDate minDate, LocalDate maxDate, Pageable pageable);
 }    
   
    



