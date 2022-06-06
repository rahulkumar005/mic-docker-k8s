package com.trantor.cards.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.trantor.cards.config.CardsServiceConfig;
import com.trantor.cards.model.Cards;
import com.trantor.cards.model.Customer;
import com.trantor.cards.properties.Properties;
import com.trantor.cards.repository.CardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardsController {

    @Autowired
    private CardsRepository cardsRepository;
    @Autowired
    private CardsServiceConfig cardsConfig;

    @PostMapping("/myCards")
    public List<Cards> getCardDetails(@RequestBody Customer customer) {
        List<Cards> cards = cardsRepository.findByCustomerId(customer.getCustomerId());
        if (cards != null) {
            return cards;
        } else {
            return null;
        }

    }
    
    @GetMapping("/cards/properties")
    public String getPropertyDetails() throws JsonProcessingException {
    	ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
    	Properties properties = new Properties(cardsConfig.getMsg(),cardsConfig.getBuildVersion(),cardsConfig.getMailDetails(),cardsConfig.getActiveBranches());
    	String jsonString = objectWriter.writeValueAsString(properties);
		return jsonString;
    	
    }
}
