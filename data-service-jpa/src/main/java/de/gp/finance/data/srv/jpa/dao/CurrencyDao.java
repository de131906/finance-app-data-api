package de.gp.finance.data.srv.jpa.dao;

import org.springframework.data.repository.CrudRepository;

import de.gp.finance.data.srv.jpa.model.CurrencyEntity;

public interface CurrencyDao extends CrudRepository<CurrencyEntity, String> { 
    
}
