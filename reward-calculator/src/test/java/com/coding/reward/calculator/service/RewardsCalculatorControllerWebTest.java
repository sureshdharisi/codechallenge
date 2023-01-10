package com.coding.reward.calculator.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.coding.reward.calculator.BaseWebTestCase;
import com.coding.reward.calculator.response.ExceptionResponse;
import com.coding.reward.calculator.response.RewardsResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RewardsCalculatorControllerWebTest extends BaseWebTestCase {

	@ParameterizedTest
	@MethodSource("inputToCalculatePoints")
	public void validatetheCalculatedRewards(Double purchaseAmount, Integer expectedOutput) {
		RewardsResponse response = this.restTemplate.getForObject(
				"http://localhost:" + port + "/rewards/calculate/" + purchaseAmount, RewardsResponse.class);
		log.debug("Response = {}", response);
		assertThat(response.getPoints()).isEqualTo(expectedOutput);
	}

	private static Stream<Arguments> inputToCalculatePoints() {
		return Stream.of(Arguments.of(120.0, 90), Arguments.of(100.0, 50), Arguments.of(150.0, 150),
				Arguments.of(99.0, 49), Arguments.of(50.0, 0));
	}

	@ParameterizedTest
	@MethodSource("inputToCalculatePointsForFailure")
	public void validateCityConnectivityFailure(String purchaseAmount, String expectedErrorCode,
			String expectedErrorMessage) {
		String url = "http://localhost:" + port + "/rewards/calculate/" + purchaseAmount;
		ExceptionResponse response = this.restTemplate.getForObject(url, ExceptionResponse.class);
		assertThat(response.getErrorCode()).isEqualTo(expectedErrorCode);
		assertThat(response.getErrorMessage()).isEqualTo(expectedErrorMessage);
	}

	private static Stream<Arguments> inputToCalculatePointsForFailure() {
		return Stream.of(Arguments.of(" ", "RC000",
				"Required URI template variable 'purchaseAmount' for method parameter type Double is present but converted to null"),
				Arguments.of("abc", "RC000",
						"Failed to convert value of type 'java.lang.String' to required type 'java.lang.Double'; For input string: \"abc\""));
	}

}
