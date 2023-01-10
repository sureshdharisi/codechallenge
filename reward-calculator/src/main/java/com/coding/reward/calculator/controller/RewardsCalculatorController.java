package com.coding.reward.calculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.coding.reward.calculator.exception.InvalidDataException;
import com.coding.reward.calculator.response.RewardsResponse;
import com.coding.reward.calculator.service.RewardsCalculatorService;

import lombok.extern.slf4j.Slf4j;

@RestController

@Slf4j
public class RewardsCalculatorController {
	

	@Autowired
	private RewardsCalculatorService service;
	
	/**
	 * This will verify the given origin and destination are connected by road or
	 * not.
	 * 
	 * <p>
	 * Example:
	 * 
	 * <br/>
	 * http://localhost:8080/connected?origin=Boston&destination=Newark -> yes <br/>
	 * http://localhost:8080/connected?origin=Boston&destination=Philadelphia -> No
	 * <br/>
	 * http://localhost:8080/connected?origin=Philadelphia&destination=Albany -> No
	 * </p>
	 * 
	 * 
	 * Made request params are optional to verify the error scenarios.
	 * @param origin
	 * @param destination
	 * @return
	 */
	@GetMapping(path = "/calculate/{purchaseAmount}")
	public RewardsResponse calculateRewards( @PathVariable(required = true) Double purchaseAmount) {
		log.debug("Purchase amount = {}",purchaseAmount);
		if(purchaseAmount==null) {
			throw new InvalidDataException("RC001", "Purchase amount is required");
		}
		Integer points=service.calculatePoints(purchaseAmount);
		
		RewardsResponse response=new RewardsResponse(points);
		
		return response;
	}
}
