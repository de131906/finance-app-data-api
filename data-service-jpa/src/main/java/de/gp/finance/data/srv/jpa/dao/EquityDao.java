package de.gp.finance.data.srv.jpa.dao;

import org.springframework.data.repository.CrudRepository;

import de.gp.finance.data.srv.jpa.model.EquityEntity;

public interface EquityDao extends CrudRepository<EquityEntity, String> { 
    
}
