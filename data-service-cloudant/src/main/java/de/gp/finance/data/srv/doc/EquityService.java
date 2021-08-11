package de.gp.finance.data.srv.doc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.gp.finance.data.model.Currency;
import de.gp.finance.data.model.Equity;
import de.gp.finance.data.srv.api.DocumentProcessingException;
import de.gp.finance.data.srv.api.IDataService;

@Service
public class EquityService extends BaseService<Equity> implements IDataService<Equity> {

    @Autowired
    private IDataService<Currency> currencySrv;

    @Override
    public List<Equity> getAll() throws IOException {
        return getAll(Equity.class);
    }

    @Override
    public List<Equity> getAllById(String id) throws IOException {
        return new ArrayList<>();
    }

    @Override
    public Optional<Equity> getById(String id) {
        return Optional.of(getById(id, Equity.class));
    }

    @Override
    public Equity createOrUpdate(Equity data) throws DocumentProcessingException {
        data.setCurrencyId(data.getCurrency().getId());
        data.setCurrency(null);
        return createOrUpdate(data, Equity.class);
    }

    @Override
    public void delete(Equity data) throws DocumentProcessingException {
        delete(data, Equity.class);
    }

    @Override
    protected Equity setupDocument(Equity entity) {
        Currency currency = currencySrv.getById(entity.getCurrencyId()).get();
        entity.setCurrency(currency);
        return entity;
    }
    
}
