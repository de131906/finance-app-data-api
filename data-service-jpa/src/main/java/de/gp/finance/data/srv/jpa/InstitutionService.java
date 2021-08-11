package de.gp.finance.data.srv.jpa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.gp.finance.data.model.Institution;
import de.gp.finance.data.srv.api.DocumentProcessingException;
import de.gp.finance.data.srv.api.IDataService;
import de.gp.finance.data.srv.api.ModelConverter;
import de.gp.finance.data.srv.jpa.dao.InstitutionDao;
import de.gp.finance.data.srv.jpa.model.InstitutionEntity;

@Service
public class InstitutionService implements IDataService<Institution> {

	private static final Logger LOG = LoggerFactory.getLogger(InstitutionService.class);
	
	@Autowired
	private InstitutionDao dao;
	
	@Override
	public List<Institution> getAll() throws IOException {
		return ModelConverter.convert(dao.findAll(), Institution.class);
	}

	@Override
    public List<Institution> getAllById(String id) throws IOException {
        return new ArrayList<>();
    }

	@Override
	public Optional<Institution> getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Institution createOrUpdate(Institution data) throws DocumentProcessingException {
		Optional<InstitutionEntity> entity = Optional.empty();
		if (data.isPersisted()) {
			entity = dao.findById(data.getId()).map(i -> {
				i.setBic(data.getBic());
				i.setName(data.getName());
				return dao.save(i);
			}).or(() -> {
				throw new IllegalArgumentException(String.format("Institution %s: no Entity found", data.getId()));
			});
			LOG.debug("Updated Institution: " + entity);
		} else {
			entity = Optional.of(dao.save(InstitutionEntity.builder().name(data.getName()).bic(data.getBic()).build()));
			LOG.debug("Created Institution: " + entity);
		}
		try {
			return ModelConverter.convert(entity.get(), Institution.class);
		} catch (JsonProcessingException e) {
			throw new DocumentProcessingException(e);
		}
	}

	@Override
	public void delete(Institution data) throws DocumentProcessingException {
		if (data.isPersisted()) {
			dao.findById(data.getId()).ifPresentOrElse(e -> {
				dao.delete(e);
			}, () -> {
				throw new IllegalArgumentException(String.format("Institution %s: no Entity found", data.getId()));
			});
		} else {
			throw new DocumentProcessingException(String.format("Institution %s: no Entity found", data.getId()));
		}
	}

}
