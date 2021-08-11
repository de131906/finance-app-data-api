package de.gp.finance.data.srv.doc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import de.gp.finance.data.model.Currency;
import de.gp.finance.data.srv.api.DocumentProcessingException;
import de.gp.finance.data.srv.api.IDataService;

@Service
public class CurrencyService extends BaseService<Currency> implements IDataService<Currency> {

    @Override
    public List<Currency> getAll() throws IOException {
        return getAll(Currency.class);
    }

    @Override
    public List<Currency> getAllById(String id) throws IOException {
        return new ArrayList<>();
    }

    @Override
    public Optional<Currency> getById(String id) {
        return Optional.of(getById(id, Currency.class));
    }

    @Override
    public Currency createOrUpdate(Currency data) throws DocumentProcessingException {
        data.setSymbol(data.getSymbol().toUpperCase());
        return createOrUpdate(data, Currency.class);
    }

    @Override
    public void delete(Currency data) throws DocumentProcessingException {
        delete(data, Currency.class);
    }

    @Override
    protected Currency setupDocument(Currency entity) {
        return entity;
    }
    
}
