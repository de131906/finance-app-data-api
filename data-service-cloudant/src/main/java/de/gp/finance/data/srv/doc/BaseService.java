package de.gp.finance.data.srv.doc;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.cloudant.client.api.Database;
import com.cloudant.client.api.model.Response;
import com.cloudant.client.api.views.Key;

import org.springframework.beans.factory.annotation.Autowired;

import de.gp.finance.data.model.BaseDocument;

public abstract class BaseService<E extends BaseDocument> {
    
    @Autowired
	private Database database;
	

    protected List<E> getAll(String designDoc, String viewName, Class<E> clazz) throws IOException {
		return database.getViewRequestBuilder(designDoc, viewName)
			.newRequest(Key.Type.STRING, Object.class).includeDocs(true)
			.build().getResponse().getDocsAs(clazz)
			.stream().map(e -> { return setupDocument(e); })
			.collect(Collectors.toList());
	}

	protected E getById(String id, Class<E> clazz) {
		return setupDocument(database.find(clazz, id));
	}
	
	protected E createOrUpdate(E data, Class<E> clazz) {
		Response response = null;
		if (data.isPersisted()) {
			response = database.update(data);
		} else {
			data.setDocType(clazz.getSimpleName());
			response = database.post(data);
		}
		return setupDocument(database.find(clazz, response.getId()));
	}
	
	protected void delete(E data, Class<E> clazz) {
		E entity = database.find(clazz, data.getId());
		if (entity != null && entity.getId().contentEquals(data.getId())
				&& entity.getRev().contentEquals(data.getRev())) {
			database.remove(entity);
		}
	}

    protected abstract E setupDocument(E entity);
}
