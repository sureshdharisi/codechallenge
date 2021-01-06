package com.home.code.challenge.findcityconnect.component;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.home.code.challenge.findcityconnect.BaseTestCase;
import com.home.code.challenge.findcityconnect.controller.CityConnectorController;
import com.home.code.challenge.findcityconnect.utils.RoadConnectivityLoader;

/**
 * Verifies all spring components are loaded in the spring container
 * @author dharisi
 *
 */

class FindCityConnectApplicationTests extends BaseTestCase{
	
	@Autowired
	private CityConnectorController cityController;
	
	@Autowired
	private RoadConnectivityLoader loader;
	

	@Test
	void contextLoads() {
		// verify the city controller is null or not;
		assertThat(cityController).isNotNull();
		assertThat(loader).isNotNull();
	}

}
