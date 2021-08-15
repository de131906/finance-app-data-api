package de.gp.finance.data.srv.doc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.gp.finance.data.model.BankAccount;
import de.gp.finance.data.model.Currency;
import de.gp.finance.data.model.Institution;
import de.gp.finance.data.srv.api.DocumentProcessingException;
import de.gp.finance.data.srv.api.IDataService;

@Service
public class BankAccountService extends AccountService<BankAccount> {

    @Autowired
    private IDataService<Institution> institutionSrv;

    @Autowired
    private IDataService<Currency> currencySrv;
    
    BankAccountService() {
        super(BankAccount.class);
    }

    @Override
    public BankAccount createOrUpdate(BankAccount data) throws DocumentProcessingException {
        data.setInstitutionId(data.getInstitution().getId());
        data.setInstitution(null);
        data.setCurrencyId(data.getCurrency().getId());
        data.setCurrency(null);
        return super.createOrUpdate(data);
    }

    @Override
    protected BankAccount setupDocument(BankAccount entity) {
        Institution institution = institutionSrv.getById(entity.getInstitutionId()).get();
        entity.setInstitution(institution);
        Currency currency = currencySrv.getById(entity.getCurrencyId()).get();
        entity.setCurrency(currency);
        return entity;
    }
    
}
