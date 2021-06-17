package com.banking.pos.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.pos.model.Account;
import com.banking.pos.model.Transaction;
import com.banking.pos.repository.AccountRepository;
import com.banking.pos.repository.TransactionRepository;

@Service
public class PosServiceImpl implements PosService {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	TransactionRepository transactionRepository;

	@Override
	public ArrayList<Account> getAccountsAll() {

		ArrayList<Account> accounts = new ArrayList<>();

		accountRepository.findAll().forEach(accounts1 -> accounts.add(accounts1));

		return accounts;
	}

	/* What if account does not exist. 
	 * java.util.NoSuchElementException: No value present */
	@Override
	public double getBalanceByAccountId(Long accountId) {
		double balance = 0;

		try {
			Account account = accountRepository.findById(accountId).get();
			balance = account.getBalance();
		} catch (Exception e) {
			System.out.println("No proper account...");
		}
		return balance;
	}

	@Override
	public void addPayment(Transaction transaction) {

		if (transaction.getMessageType().equals(Transaction.MESSAGE_TYPE_PAYMENT)) {
			updateAccountBalanceByPayment(transaction);
		} else if (transaction.getMessageType().equals(Transaction.MESSAGE_TYPE_ADJUSTMENT)) {
			updateAccountBalanceByAdjustment(transaction);
		} else {
			System.out.println("Message Type is different than expected!!!");

		}
	}

	private void updateAccountBalanceByPayment(Transaction transaction) {

		// calculating the commision
		transaction.calculateCommision();
		transactionRepository.save(transaction);

		Account account = accountRepository.findById(transaction.getAccountId()).get();
		double balanceNew = account.getBalance() + (transaction.getAmount() - transaction.getCommission());
		account.setBalance(balanceNew);
		accountRepository.save(account);
		
	}

	/*
	 * Is there any commission calculation for adjustment process? For this case;
	 * the amount difference and the new commision for new transaction are
	 * calculated and the balance is updated according to the calculations. Amount
	 * is added, it might be smaller or bigger. 
	 * By the way, Transactions might be logged.
	 */

	/* if there is not a record for this transaction, a message should be sent ?!*/
	private void updateAccountBalanceByAdjustment(Transaction transaction) {
		try {

			Transaction transactionOld = transactionRepository.findById(transaction.getTransactionId()).get();
		
			double amountDifference = transaction.getAmount() - transactionOld.getAmount();

			transaction.calculateCommision();
			double commisionDifference = transaction.getCommission() - transactionOld.getCommission();


			Account account = accountRepository.findById(transaction.getAccountId()).get();
			account.setBalance(account.getBalance() + amountDifference - commisionDifference);
			accountRepository.save(account);

			transactionRepository.save(transaction);

		} catch (Exception e) {
			System.out.println("No proper transaction...");
		}

	}

}
