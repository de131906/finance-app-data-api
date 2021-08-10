package de.gp.finance.data.rest;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.gp.finance.data.model.Institution;
import de.gp.finance.data.srv.api.DocumentProcessingException;
import de.gp.finance.data.srv.api.IDataService;

@RestController
@CrossOrigin
public class InstitutionEndpoint {

	@Autowired
	private IDataService<Institution> srv;
	
	@GetMapping("/institutions")
	@ResponseBody
	public List<Institution> institutions() throws IOException {
		return srv.getAll();
	}

	@PostMapping(value = "/institution/update", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Institution> updateOrCreateInstitution(@RequestBody Institution data) throws DocumentProcessingException {
		Institution entity = srv.createOrUpdate(data);
		return ResponseEntity.ok(entity);
	}

	@DeleteMapping("/institution/delete")
	public ResponseEntity<Institution> delete(@RequestBody Institution data) throws DocumentProcessingException {
		srv.delete(data);
		return ResponseEntity.ok().build();
	}
}
