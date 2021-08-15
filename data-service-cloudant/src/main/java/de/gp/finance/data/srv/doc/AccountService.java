package de.gp.finance.data.srv.doc;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import de.gp.finance.data.model.Account;
import de.gp.finance.data.srv.api.DocumentProcessingException;
import de.gp.finance.data.srv.api.IDataService;

public abstract class AccountService<A extends Account> extends BaseService<A> implements IDataService<A> {

    private Class<A> clazz;
    
    AccountService(Class<A> clazz) {
        this.clazz = clazz;
    }

    @Override
    public List<A> getAll() throws IOException {
        return getAll(clazz);
    }

    @Override
    public List<A> getAllById(String id) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<A> getById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public A createOrUpdate(A data) throws DocumentProcessingException {
        return createOrUpdate(data, clazz);
    }

    @Override
    public void delete(A data) throws DocumentProcessingException {
        // TODO Auto-generated method stub
        
    }
    
}
