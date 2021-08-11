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

import de.gp.finance.data.model.Equity;
import de.gp.finance.data.srv.api.DocumentProcessingException;
import de.gp.finance.data.srv.api.IDataService;
import de.gp.finance.data.srv.api.ModelConverter;
import de.gp.finance.data.srv.jpa.dao.CurrencyDao;
import de.gp.finance.data.srv.jpa.dao.EquityDao;
import de.gp.finance.data.srv.jpa.model.EquityEntity;

@Service
public class EquityService implements IDataService<Equity> {

    private static final Logger LOG = LoggerFactory.getLogger(EquityService.class);

    @Autowired
    private EquityDao dao;
    
    @Autowired
    private CurrencyDao currencyDao;
    
    @Override
    public List<Equity> getAll() throws IOException {
        return ModelConverter.convert(dao.findAll(), Equity.class);
    }

    @Override
    public List<Equity> getAllById(String id) throws IOException {
        return new ArrayList<>();
    }

    @Override
    public Optional<Equity> getById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Equity createOrUpdate(Equity data) throws DocumentProcessingException {
        Optional<EquityEntity> entity = Optional.empty();
		if (data.isPersisted()) {
			entity = dao.findById(data.getId()).map(e -> {
				e.setSymbol(data.getSymbol().toUpperCase());
				e.setName(data.getName());
                e.setIsin(data.getIsin().toUpperCase());
                e.setType(data.getType().name());
                e.setCurrency(currencyDao.findById(data.getCurrency().getId()).get());
				return dao.save(e);
			}).or(() -> {
				throw new IllegalArgumentException(String.format("Equity %s: no Entity found", data.getId()));
			});
			LOG.debug("Updated Equity: " + entity);
		} else {
			entity = Optional.of(dao.save(EquityEntity.builder().symbol(data.getSymbol().toUpperCase())
                .name(data.getName())
                .isin(data.getIsin().toUpperCase())
                .type(data.getType().name())
                .currency(currencyDao.findById(data.getCurrency().getId()).get()).build()));
			LOG.debug("Created Equity: " + entity.get());
		}
		try {
			return ModelConverter.convert(entity.get(), Equity.class);
		} catch (JsonProcessingException e) {
			throw new DocumentProcessingException(e);
		}
    }

    @Override
    public void delete(Equity data) throws DocumentProcessingException {
        // TODO Auto-generated method stub
        
    }
    
}
