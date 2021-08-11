package de.gp.finance.data.srv.jpa;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.gp.finance.data.model.Price;
import de.gp.finance.data.srv.api.DocumentProcessingException;
import de.gp.finance.data.srv.api.IDataService;
import de.gp.finance.data.srv.api.ModelConverter;
import de.gp.finance.data.srv.jpa.dao.EquityDao;
import de.gp.finance.data.srv.jpa.dao.PriceDao;
import de.gp.finance.data.srv.jpa.model.PriceEntity;

@Service
public class PriceService implements IDataService<Price> {

    private static final Logger LOG = LoggerFactory.getLogger(PriceService.class);

    @Autowired
    private PriceDao dao;

    @Autowired
    private EquityDao equityDao;

    @Override
    public List<Price> getAll() throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Price> getAllById(String equityId) throws IOException {
        List<PriceEntity> prices =  dao.findAllByEquityUid(equityId);
        return ModelConverter.convert(prices, Price.class);
    }

    @Override
    public Optional<Price> getById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Price createOrUpdate(Price data) throws DocumentProcessingException {
        Optional<PriceEntity> entity = Optional.empty();
		if (data.isPersisted()) {
			entity = dao.findById(data.getId()).map(e -> {
				e.setDate(data.getDate());
				e.setRate(data.getRate());
				return dao.save(e);
			}).or(() -> {
				throw new IllegalArgumentException(String.format("Price %s: no Entity found", data.getId()));
			});
			LOG.debug("Updated Price: " + entity);
		} else {
			entity = Optional.of(dao.save(PriceEntity.builder()
                .date(data.getDate())
                .rate(data.getRate())
                .equity(equityDao.findById(data.getEquity().getId()).get())
                .build()));
			LOG.debug("Created Equity: " + entity.get());
		}
		try {
			return ModelConverter.convert(entity.get(), Price.class);
		} catch (JsonProcessingException e) {
			throw new DocumentProcessingException(e);
		}
    }

    @Override
    public void delete(Price data) throws DocumentProcessingException {
        if (data.isPersisted()) {
			dao.findById(data.getId()).ifPresentOrElse(e -> {
				dao.delete(e);
			}, () -> {
				throw new IllegalArgumentException(String.format("Price %s: no Entity found", data.getId()));
			});
		} else {
			throw new DocumentProcessingException(String.format("Price %s: no Entity found", data.getId()));
		}
    }
    
}
