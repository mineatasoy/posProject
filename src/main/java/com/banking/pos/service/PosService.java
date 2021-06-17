package com.banking.pos.service;

import java.util.ArrayList;

import com.banking.pos.model.Account;
import com.banking.pos.model.Transaction;

public interface PosService {

	public ArrayList<Account> getAccountsAll();
	
	public double getBalanceByAccountId(Long accountId);
	
	public void addPayment(Transaction transaction);

}
