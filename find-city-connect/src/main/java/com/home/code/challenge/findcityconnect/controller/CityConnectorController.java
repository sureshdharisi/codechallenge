package com.home.code.challenge.findcityconnect.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.home.code.challenge.findcityconnect.exception.InvalidDataException;
import com.home.code.challenge.findcityconnect.utils.ConnectivityRegistry;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * This will publish the service which will accept two cities to check the road
 * connectivity is there or not.
 * 
 * @author dsuresh
 *
 */

@RestController
public class CityConnectorController {
	Logger logger = LoggerFactory.getLogger(CityConnectorController.class);
	@Autowired
	private ConnectivityRegistry registry;
	

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
	@GetMapping(path = "/connected")
	@ApiOperation(value = "Check the cities connectivity")
	public String isCitiesConnected(@ApiParam(value = "Origin", example = "Boston") @RequestParam(required = false ) String origin,
			@ApiParam(value = "Destination", example = "Newark") @RequestParam(required = false) String destination) {
		logger.debug("city 1 " + origin);
		logger.debug("city 2 " + destination);
		if(StringUtils.isBlank(origin)) {
			throw new InvalidDataException("CITY001", "Origin city is required");
		}
		if(StringUtils.isBlank(destination)) {
			throw new InvalidDataException("CITY002", "Destination city is required");
		}
		return registry.isCitiesConnected(origin, destination) ? "yes" : "no";
	}
}
