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

import de.gp.finance.data.model.Currency;
import de.gp.finance.data.srv.api.DocumentProcessingException;
import de.gp.finance.data.srv.api.IDataService;
import de.gp.finance.data.srv.api.ModelConverter;
import de.gp.finance.data.srv.jpa.dao.CurrencyDao;
import de.gp.finance.data.srv.jpa.model.CurrencyEntity;

@Service
public class CurrencyService implements IDataService<Currency> {

    private static final Logger LOG = LoggerFactory.getLogger(CurrencyService.class);
    
    @Autowired
    private CurrencyDao dao;

    @Override
    public List<Currency> getAll() throws IOException {
        return ModelConverter.convert(dao.findAll(), Currency.class);
    }

	@Override
    public List<Currency> getAllById(String id) throws IOException {
        return new ArrayList<>();
    }

    @Override
    public Optional<Currency> getById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Currency createOrUpdate(Currency data) throws DocumentProcessingException {
        Optional<CurrencyEntity> entity = Optional.empty();
		if (data.isPersisted()) {
			entity = dao.findById(data.getId()).map(e -> {
				e.setSymbol(data.getSymbol().toUpperCase());
				e.setName(data.getName());
				return dao.save(e);
			}).or(() -> {
				throw new IllegalArgumentException(String.format("Currency %s: no Entity found", data.getId()));
			});
			LOG.debug("Updated Institution: " + entity);
		} else {
			entity = Optional.of(dao.save(CurrencyEntity.builder().name(data.getName()).symbol(data.getSymbol().toUpperCase()).build()));
			LOG.debug("Created Currency: " + entity);
		}
		try {
			return ModelConverter.convert(entity.get(), Currency.class);
		} catch (JsonProcessingException e) {
			throw new DocumentProcessingException(e);
		}
    }

    @Override
    public void delete(Currency data) throws DocumentProcessingException {
        if (data.isPersisted()) {
			dao.findById(data.getId()).ifPresentOrElse(e -> {
				dao.delete(e);
			}, () -> {
				throw new IllegalArgumentException(String.format("Currency %s: no Entity found", data.getId()));
			});
		} else {
			throw new DocumentProcessingException(String.format("Currency %s: no Entity found", data.getId()));
		}
    }
    
}
