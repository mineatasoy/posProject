package com.banking.pos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.banking.pos.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long>{

}
