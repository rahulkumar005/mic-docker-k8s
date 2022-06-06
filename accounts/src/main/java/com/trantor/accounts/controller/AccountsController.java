package com.trantor.accounts.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.trantor.accounts.config.AccountsServiceConfig;
import com.trantor.accounts.model.Accounts;
import com.trantor.accounts.model.Customer;
import com.trantor.accounts.properties.Properties;
import com.trantor.accounts.repository.AccountsRepository;

@RestController
public class AccountsController {
    @Autowired
    private AccountsRepository accountsRepository;
    @Autowired
    AccountsServiceConfig accountsConfig;

    @PostMapping("/myAccount")
    public Accounts getAccountDetails(@RequestBody Customer customer) {

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId());
        if (accounts != null) {
            return accounts;
        } else {
            return null;
        }

    }
    
    @GetMapping("/accounts/properties")
    public String getPropertyDetails() throws JsonProcessingException{

    	ObjectWriter defaultPrettyPrinter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(accountsConfig.getMsg(),accountsConfig.getBuildVersion(),accountsConfig.getMailDetails(),accountsConfig.getActiveBranches());
    	String valueAsString = defaultPrettyPrinter.writeValueAsString(properties);
    	
		return valueAsString;
}
}
