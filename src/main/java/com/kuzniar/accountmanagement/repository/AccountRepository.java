package com.kuzniar.accountmanagement.repository;

import com.kuzniar.accountmanagement.datamodel.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
}
