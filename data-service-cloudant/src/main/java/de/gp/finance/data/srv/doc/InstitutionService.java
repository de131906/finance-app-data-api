package de.gp.finance.data.srv.doc;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import de.gp.finance.data.model.Institution;
import de.gp.finance.data.srv.api.DocumentProcessingException;
import de.gp.finance.data.srv.api.IDataService;

@Service
public class InstitutionService extends BaseService<Institution> implements IDataService<Institution>  {

    @Override
    public List<Institution> getAll() throws IOException {
        return getAll("all", "institutions", Institution.class);
    }

    @Override
    public Optional<Institution> getById(String id) {
        return Optional.of(getById(id, Institution.class));
    }

    @Override
    public Institution createOrUpdate(Institution data) throws DocumentProcessingException {
        return createOrUpdate(data, Institution.class);
    }

    @Override
    public void delete(Institution data) throws DocumentProcessingException {
        delete(data, Institution.class);
    }

    @Override
	protected Institution setupDocument(Institution institution) {
		return institution;
	}
    
}
