package com.trantor.loans.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.trantor.loans.config.LoansServiceConfig;
import com.trantor.loans.model.Customer;
import com.trantor.loans.model.Loans;
import com.trantor.loans.properties.Properties;
import com.trantor.loans.repository.LoansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoansController {
    @Autowired
    private LoansRepository loansRepository;
    @Autowired
    LoansServiceConfig loansConfig;

    @PostMapping("/myLoans")
    public List<Loans> getLoansDetails(@RequestBody Customer customer) {
        List<Loans> loans = loansRepository.findByCustomerIdOrderByStartDtDesc(customer.getCustomerId());
        if (loans != null) {
            return loans;
        } else {
            return null;
        }

    }
    
    @GetMapping("/loans/properties")
    public String getPropertiesDetails() throws JsonProcessingException {
    	ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
    	Properties properties = new Properties(loansConfig.getMsg(),loansConfig.getBuildVersion(),loansConfig.getMailDetails(),loansConfig.getActiveBranches());
        return objectWriter.writeValueAsString(properties);    
    }

}
