package de.gp.finance.data.srv.api;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import de.gp.finance.data.model.BaseDocument;

public interface IDataService<D extends BaseDocument> {

	List<D> getAll() throws IOException;

	List<D> getAllById(String id) throws IOException;
	
	Optional<D> getById(String id);
	
	D createOrUpdate(D data) throws DocumentProcessingException;
	
	void delete(D data) throws DocumentProcessingException;
}
