package de.gp.finance.data.srv.api;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import de.gp.finance.data.model.BaseDocument;

public interface IDataService<E extends BaseDocument> {

	List<E> getAll() throws IOException;
	
	Optional<E> getById(String id);
	
	E createOrUpdate(E data) throws DocumentProcessingException;
	
	void delete(E data) throws DocumentProcessingException;
}
