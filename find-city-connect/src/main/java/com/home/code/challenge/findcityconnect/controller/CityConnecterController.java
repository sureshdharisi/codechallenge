package com.home.code.challenge.findcityconnect.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.home.code.challenge.findcityconnect.utils.RoadConnectivityLoader;


@RestController
public class CityConnecterController {
	Logger logger = LoggerFactory.getLogger(CityConnecterController.class);
	@Autowired
	private RoadConnectivityLoader connectivityLoader;

	@GetMapping(path = "/connected")
	public String isCitiesConnected(@RequestParam String origin,@RequestParam String destination) {
		logger.debug("city 1 "+origin);
		logger.debug("city 2 "+destination);
		return connectivityLoader.isCitiesConnected(origin, destination)?"yes":"no";
	}
}
