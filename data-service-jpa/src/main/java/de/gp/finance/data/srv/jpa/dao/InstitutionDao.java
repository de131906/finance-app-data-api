package de.gp.finance.data.srv.jpa.dao;

import org.springframework.data.repository.CrudRepository;

import de.gp.finance.data.srv.jpa.model.InstitutionEntity;

public interface InstitutionDao extends CrudRepository<InstitutionEntity, String> { 

}
