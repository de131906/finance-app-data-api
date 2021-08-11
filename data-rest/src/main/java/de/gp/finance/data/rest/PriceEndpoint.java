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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.gp.finance.data.model.Price;
import de.gp.finance.data.srv.api.DocumentProcessingException;
import de.gp.finance.data.srv.api.IDataService;

@RestController
@CrossOrigin
public class PriceEndpoint {

    @Autowired
    private IDataService<Price> srv;
    
    @GetMapping("/prices")
	@ResponseBody
    public List<Price> prices(@RequestParam(name = "equityId") String equityId) throws IOException {
		return srv.getAllById(equityId);
	}

    @PostMapping(value = "/price/update", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Price> updateOrCreateInstitution(@RequestBody Price data) throws DocumentProcessingException {
		Price entity = srv.createOrUpdate(data);
		return ResponseEntity.ok(entity);
	}

	@DeleteMapping("/price/delete")
	public ResponseEntity<Price> delete(@RequestBody Price data) throws DocumentProcessingException {
		srv.delete(data);
		return ResponseEntity.ok().build();
	}

}
