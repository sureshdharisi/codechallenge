package com.home.code.challenge.findcityconnect.component;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.code.challenge.findcityconnect.BaseTestCase;
import com.home.code.challenge.findcityconnect.controller.CityConnectorController;
import com.home.code.challenge.findcityconnect.exception.InvalidDataException;

/**
 * Invoke the controller methods directly.
 * 
 * @author Dharisi
 *
 */
public class CityConnectorControllerTest extends BaseTestCase{

	@Autowired
	private CityConnectorController controller;
	
	@ParameterizedTest
	@MethodSource("inputCities")
	public void validateCityConnectivity(String origin,String destination,String expectedOutput) {
		assertThat(controller.isCitiesConnected(origin, destination)).isEqualTo(expectedOutput);
	}
				
	private static Stream<Arguments> inputCities() {
	    return Stream.of(
	      Arguments.of("Boston", "Newark","yes"),
	      Arguments.of("Boston", "Philadelphia","yes"),
	      Arguments.of("Philadelphia", "Albany","no")
	    );
	}
	
	@ParameterizedTest
	@MethodSource("inputCitiesForFailure")
	public void validateCityConnectivityFailure(String origin,String destination,String expectedErrorCode,String expectedErrorMessage) {
		try {
			controller.isCitiesConnected(origin, destination);
			fail("This testcase should throw exception");
		}catch(InvalidDataException ide) {
			assertThat(ide.getErrorCode()).isEqualTo(expectedErrorCode);
			assertThat(ide.getMessage()).isEqualTo(expectedErrorMessage);
		}
	}
	
	private static Stream<Arguments> inputCitiesForFailure() {
	    return Stream.of(
	      Arguments.of(" ", "Newark","CITY001","Origin city is required"),
	      Arguments.of(null, "Newark","CITY001","Origin city is required"),
	      Arguments.of("Philadelphia", " ","CITY002","Destination city is required"),
	      Arguments.of("Philadelphia", "","CITY002","Destination city is required"),
	      Arguments.of("Philadelphia", null,"CITY002","Destination city is required")
	    );
	}
	
	
}
