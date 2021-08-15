package de.gp.finance.data.rest;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.gp.finance.data.model.BankAccount;
import de.gp.finance.data.srv.api.DocumentProcessingException;
import de.gp.finance.data.srv.api.IDataService;

@RestController
@CrossOrigin
public class BankAccountEndpoint {
    
    @Autowired
	private IDataService<BankAccount> srv;

    @GetMapping("/accounts/bank")
	@ResponseBody
	public List<BankAccount> bankaccounts() throws IOException {
		return srv.getAll();
	}

    @PostMapping(value = "/account/bank/update", consumes = "application/json", produces = "application/json")
	public ResponseEntity<BankAccount> updateOrCreateAccount(@RequestBody BankAccount data) throws DocumentProcessingException {
		BankAccount entity = srv.createOrUpdate(data);
		return ResponseEntity.ok(entity);
	}
}
