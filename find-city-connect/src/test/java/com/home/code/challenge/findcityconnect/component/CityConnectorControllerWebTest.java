package com.home.code.challenge.findcityconnect.component;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.home.code.challenge.findcityconnect.BaseWebTestCase;
import com.home.code.challenge.findcityconnect.exception.InvalidDataException;

public class CityConnectorControllerWebTest extends BaseWebTestCase{

	
	
	@ParameterizedTest
	@MethodSource("inputCities")
	public void validateCityConnectivity(String origin,String destination,String expectedOutput) {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/connected?origin="+origin+"&destination="+destination,
				String.class)).isEqualTo(expectedOutput);
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
	public void validateCityConnectivityFailure(String origin, String destination, String expectedErrorCode,
			String expectedErrorMessage) {
		String url = "http://localhost:" + port + "/connected?";
		if (origin != null) {
			url = url + "origin=" + origin + "&";
		}
		if (destination != null) {
			url = url + "destination=" + destination + "&";
		}
		assertThat(this.restTemplate.getForObject(url, String.class)).contains(expectedErrorCode, expectedErrorMessage);
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
