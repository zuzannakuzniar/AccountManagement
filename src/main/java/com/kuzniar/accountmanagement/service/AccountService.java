package com.kuzniar.accountmanagement.service;

import com.kuzniar.accountmanagement.datamodel.Account;
import com.kuzniar.accountmanagement.datamodel.Customer;
import com.kuzniar.accountmanagement.repository.AccountRepository;
import com.kuzniar.accountmanagement.repository.CustomerRepository;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public AccountService(AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    public Account createAccount(Account account) {
        Optional<Customer> customer = customerRepository.findById(account.getOwner().getId());
        if (customer.isPresent()) {
            account.setOwner(customer.get());
            account.setNumber(generateAccountNumber());
            return accountRepository.save(account);
        } else {
            throw new IllegalArgumentException("Customer with id doesn't exist.");
        }
    }

    public Account getAccount(long id) {
        Optional<Account> account = accountRepository.findById(id);
        return account.orElse(null);
    }

    public void updateAccount(Account account) {
        Optional<Account> existingAccount = accountRepository.findById(account.getId());
        if (existingAccount.isPresent()) {
            Account updatedAccount = existingAccount.get();
            updatedAccount.setAccountType(account.getAccountType());
            updatedAccount.setBalance(account.getBalance());
            updatedAccount.setNumber(account.getNumber());
            updatedAccount.setOwner(account.getOwner());
            updatedAccount.setExchangedBalance(account.getExchangedBalance());
            accountRepository.save(updatedAccount);
        } else {
            accountRepository.save(account);
        }

    }

    public Account withdrawMoney(long accountId, double amount) {
        Optional<Account> existingAccount = accountRepository.findById(accountId);
        if (existingAccount.isPresent()) {
            Account updatedAccount = existingAccount.get();
            updatedAccount.setBalance(getNewBalance(updatedAccount.getBalance(), amount));
            accountRepository.save(updatedAccount);
            return updatedAccount;
        } else {
            throw new IllegalArgumentException("Account with id " + accountId + " doesn't exist.");
        }

    }

    private double getNewBalance(double balance, double amount) {
        if(amount <= balance){
            return balance - amount;
        }
        throw new IllegalArgumentException("Given amount cannot be withdrawned. Your account balance is " + balance);
    }

    public Account depositMoney(long accountId, double amount) {
        Optional<Account> existingAccount = accountRepository.findById(accountId);
        if (existingAccount.isPresent()) {
            Account updatedAccount = existingAccount.get();
            updatedAccount.setBalance(updatedAccount.getBalance() + amount);
            accountRepository.save(updatedAccount);
            return updatedAccount;
        } else {
            throw new IllegalArgumentException("Account with id " + accountId + " doesn't exist.");
        }
    }

    public void deleteAccount(Account account) {
        if (accountRepository.existsById(account.getId())) {
            accountRepository.delete(account);
        }
    }

    private String generateAccountNumber() {
        Iban iban = new Iban.Builder()
                .countryCode(CountryCode.PL)
                .bankCode("199")
                .buildRandom();
        return iban.getAccountNumber();
    }

}
