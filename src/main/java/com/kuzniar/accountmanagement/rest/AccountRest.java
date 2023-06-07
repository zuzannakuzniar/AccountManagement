package com.kuzniar.accountmanagement.rest;

import com.kuzniar.accountmanagement.datamodel.Account;
import com.kuzniar.accountmanagement.service.AccountService;
import com.kuzniar.accountmanagement.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountRest {

    private AccountService accountService;
    private ExchangeService exchangeService;

    @Autowired
    public AccountRest(AccountService accountService, ExchangeService exchangeService){
        this.accountService = accountService;
        this.exchangeService = exchangeService;
    }

    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable long id) {
        return accountService.getAccount(id);
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public Account saveAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @GetMapping("/{id}/{currency}")
    public Account getAccountBalanceExchanged(@PathVariable long id, @PathVariable String currency) {
        return exchangeService.getBalance(id, currency);
    }

    @PutMapping(path = "/withdrawal/{id}/{amount}")
    public Account withdrawMoney(@PathVariable long id, @PathVariable double amount) {
        return accountService.withdrawMoney(id, amount);
    }

    @PutMapping(path = "/deposit/{id}/{amount}")
    public Account depositMoney(@PathVariable long id, @PathVariable double amount) {
        return accountService.depositMoney(id, amount);
    }


}
