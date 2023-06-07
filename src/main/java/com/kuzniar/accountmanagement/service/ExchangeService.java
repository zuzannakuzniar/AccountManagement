package com.kuzniar.accountmanagement.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kuzniar.accountmanagement.datamodel.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class ExchangeService {

    @Autowired
    AccountService accountService;

    public ExchangeService(AccountService accountService){
        this.accountService = accountService;
    }

    public Account getBalance(long accountId, String currency){
        Account account = accountService.getAccount(accountId);

        String urlString = "https://currency-exchange.p.rapidapi.com/exchange?from=PLN&to="+currency+"&q=1";
        BigDecimal exchangeRate = BigDecimal.ZERO;
        try {
            URL url = new URL(urlString);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.setRequestProperty("X-RapidAPI-Key", "ed9df76b32msh824995b033e6575p169be5jsn0914a5a8e845");
            request.setRequestProperty("X-RapidAPI-Host", "currency-exchange.p.rapidapi.com");
            request.connect();
            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            exchangeRate = root.getAsBigDecimal();
        } catch (IOException e){
            e.printStackTrace();
        }

        double exchangedBalance = account.getBalance() * exchangeRate.doubleValue();
        account.setExchangedBalance( Math.round(exchangedBalance * 100.0) / 100.0);
        accountService.updateAccount(account);
        return account;
    }
}
