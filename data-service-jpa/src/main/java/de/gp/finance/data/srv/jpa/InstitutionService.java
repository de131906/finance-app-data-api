package de.gp.finance.data.srv.jpa;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.gp.finance.data.model.Institution;
import de.gp.finance.data.srv.api.IDataService;
import de.gp.finance.data.srv.api.ModelConverter;
import de.gp.finance.data.srv.jpa.dao.InstitutionDao;

@Service
public class InstitutionService implements IDataService<Institution> {

	@Autowired
	private InstitutionDao dao;
	
	@Override
	public List<Institution> getAll() throws IOException {
		return ModelConverter.convert(dao.findAll(), Institution.class);
	}

	@Override
	public Optional<Institution> getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Institution createOrUpdate(Institution data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Institution data) {
		// TODO Auto-generated method stub
		
	}

}
