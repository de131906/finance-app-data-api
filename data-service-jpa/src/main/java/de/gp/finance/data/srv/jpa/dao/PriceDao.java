package de.gp.finance.data.srv.jpa.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.gp.finance.data.srv.jpa.model.PriceEntity;

public interface PriceDao extends CrudRepository<PriceEntity, String> { 
    
    List<PriceEntity> findAllByEquityUid(String equityId);
}
