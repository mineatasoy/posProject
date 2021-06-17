package com.banking.pos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.banking.pos.model.Transaction;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, String>{

}
