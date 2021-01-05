package com.home.code.challenge.findcityconnect.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.home.code.challenge.findcityconnect.utils.RoadConnectivityLoader;

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
	private RoadConnectivityLoader connectivityLoader;

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
	 * @param origin
	 * @param destination
	 * @return
	 */
	@GetMapping(path = "/connected")
	@ApiOperation(value = "Check the cities connectivity")
	public String isCitiesConnected(@ApiParam(value = "Origin", example = "Boston") @RequestParam String origin,
			@ApiParam(value = "Destination", example = "Newark") @RequestParam String destination) {
		logger.debug("city 1 " + origin);
		logger.debug("city 2 " + destination);
		return connectivityLoader.isCitiesConnected(origin, destination) ? "yes" : "no";
	}
}
