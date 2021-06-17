package com.banking.pos.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.banking.pos.model.Account;
import com.banking.pos.model.Transaction;
import com.banking.pos.service.PosService;

@RestController
public class PosApiController {
	
	
	@Autowired
	PosService posService;

	//Extra method for calling all accounts.
	@GetMapping(value = "/api/accounts")
	public ArrayList<Account> accounts() {

		return posService.getAccountsAll();
		
	}
	
	//1st endpoint to query the balance of an account.
	@GetMapping(value = "/api/account-balance/{accountId}")
	//http://localhost:8080/api/balance?accountId=4755
	public double getBalanceByAccountId(@PathVariable Long accountId) {
		
		return posService.getBalanceByAccountId(accountId);
		
	}

	
	//2nd endpoint for payment process.
	//curl -H "Content-Type: application/json" -X POST -d {\"messageType\":\"PAYMENT\",\"transactionId\":\"54622353626262626\",\"accountId\":\"4755\",\"origin\":\"VISA\",\"amount\":\"10.01\"} http://localhost:8080/api/payment
	@PostMapping(path = "/api/payment")
	public void getPayment(@RequestBody Transaction transaction) {

		posService.addPayment(transaction);
			
	}
		
}
