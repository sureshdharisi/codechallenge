package com.home.code.challenge.findcityconnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.home.code.challenge.findcityconnect.utils.RoadConnectivityLoader;

@RestController
public class CityConnecterController {
	
	@Autowired
	private RoadConnectivityLoader connectivityLoader;

	@GetMapping(path = "/connected")
	public String isCitiesConnected(@RequestParam String origin,@RequestParam String destination) {
		System.out.println("city 1 "+origin);
		System.out.println("city 2 "+destination);
		return connectivityLoader.isCitiesConnected(origin, destination)?"yes":"no";
	}
}
