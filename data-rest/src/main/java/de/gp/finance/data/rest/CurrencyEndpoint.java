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

import de.gp.finance.data.model.Currency;
import de.gp.finance.data.srv.api.DocumentProcessingException;
import de.gp.finance.data.srv.api.IDataService;

@RestController
@CrossOrigin
public class CurrencyEndpoint {
    
    @Autowired
	private IDataService<Currency> srv;

    @GetMapping("/currencies")
	@ResponseBody
	public List<Currency> currencies() throws IOException {
		return srv.getAll();
	}

    @PostMapping(value = "/currency/update", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Currency> updateOrCreateInstitution(@RequestBody Currency data) throws DocumentProcessingException {
		Currency entity = srv.createOrUpdate(data);
		return ResponseEntity.ok(entity);
	}

	@DeleteMapping("/currency/delete")
	public ResponseEntity<Currency> delete(@RequestBody Currency data) throws DocumentProcessingException {
		srv.delete(data);
		return ResponseEntity.ok().build();
	}
}
