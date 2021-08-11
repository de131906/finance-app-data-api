package de.gp.finance.data.srv.api;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import de.gp.finance.data.model.BaseDocument;

public class ModelConverter {

	public static <E, D extends BaseDocument> D convert(E entity, Class<D> docClass) throws JsonProcessingException {
		ObjectMapper mapper = JsonMapper.builder()
			.addModule(new JavaTimeModule())
			.build();
		return mapper.readValue(mapper.writeValueAsString(entity), docClass);
	}

	public static <E, D extends BaseDocument>
	List<D> convert(Iterable<E> entities, Class<D> docClass) throws JsonProcessingException {
		List<D> docs = new ArrayList<>();
		ObjectMapper mapper = JsonMapper.builder()
			.addModule(new JavaTimeModule())
			.build();
		for (E entity : entities) {
			docs.add(mapper.readValue(mapper.writeValueAsString(entity), docClass));
		}
		return docs;
	}
}
