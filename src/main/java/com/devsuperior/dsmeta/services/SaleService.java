package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SumSalesDTO;
import com.devsuperior.dsmeta.dto.SaleDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}
	
    public Page<SaleDTO> searchSales(String name, String minDate, String maxDate, int page, int size) {
        LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

        LocalDate max = (maxDate == null || maxDate.isEmpty()) ? today : LocalDate.parse(maxDate);
        LocalDate min = (minDate == null || minDate.isEmpty()) ? max.minusYears(1L) : LocalDate.parse(minDate);
        
        PageRequest pageRequest = PageRequest.of(page, size);

        Page<Sale> result = repository.searchSales(name == null ? "" : name, min, max, pageRequest);
        return result.map(x -> new SaleDTO(x));
    }
    
    public Page<SumSalesDTO> sumSales(String minDate, String maxDate, int page, int size) {
        LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

        LocalDate max = (maxDate == null || maxDate.isEmpty()) ? today : LocalDate.parse(maxDate);
        LocalDate min = (minDate == null || minDate.isEmpty()) ? max.minusYears(1L) : LocalDate.parse(minDate);
        
        PageRequest pageRequest = PageRequest.of(page, size);

        return repository.sumSales(min, max, pageRequest);
    }
}
