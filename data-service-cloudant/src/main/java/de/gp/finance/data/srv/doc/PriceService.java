package de.gp.finance.data.srv.doc;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.cloudant.client.api.query.Expression;
import com.cloudant.client.api.query.Operation;
import com.cloudant.client.api.query.QueryBuilder;
import com.cloudant.client.api.query.QueryResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.gp.finance.data.model.Equity;
import de.gp.finance.data.model.Price;
import de.gp.finance.data.srv.api.DocumentProcessingException;
import de.gp.finance.data.srv.api.IDataService;

@Service
public class PriceService extends BaseService<Price> implements IDataService<Price> {

    @Autowired
    private IDataService<Equity> equitySrv;

    @Override
    public List<Price> getAll() throws IOException {
        return getAll(Price.class);
    }

    @Override
    public List<Price> getAllById(String id) throws IOException {
        QueryResult<Price> prices = database.query(new QueryBuilder(Operation.and(
            Expression.eq("docType", "Price"),
            Expression.eq("equityId", id)))
        .build(), Price.class);
        return prices.getDocs().stream().map(p -> setupDocument(p)).collect(Collectors.toList());
    }

    @Override
    public Optional<Price> getById(String id) {
        return Optional.empty();
    }

    @Override
    public Price createOrUpdate(Price data) throws DocumentProcessingException {
        data.setEquityId(data.getEquity().getId());
        data.setEquity(null);
        return createOrUpdate(data, Price.class);
    }

    @Override
    public void delete(Price data) throws DocumentProcessingException {
        delete(data, Price.class);
    }

    @Override
    protected Price setupDocument(Price entity) {
        Equity equity = equitySrv.getById(entity.getEquityId()).get();
        entity.setEquity(equity);
        return entity;
    }
    
}
