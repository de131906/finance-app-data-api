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

import de.gp.finance.data.model.Equity;
import de.gp.finance.data.srv.api.DocumentProcessingException;
import de.gp.finance.data.srv.api.IDataService;

@RestController
@CrossOrigin
public class EquityEndpoint {
    
    @Autowired
    private IDataService<Equity> srv;

    @GetMapping("/equities")
	@ResponseBody
	public List<Equity> equities() throws IOException {
		return srv.getAll();
	}

    @PostMapping(value = "/equity/update", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Equity> updateOrCreateInstitution(@RequestBody Equity data) throws DocumentProcessingException {
		Equity entity = srv.createOrUpdate(data);
		return ResponseEntity.ok(entity);
	}

    @DeleteMapping("/equity/delete")
	public ResponseEntity<Equity> delete(@RequestBody Equity data) throws DocumentProcessingException {
		srv.delete(data);
		return ResponseEntity.ok().build();
	}
}
