package com.coding.reward.calculator.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.coding.reward.calculator.BaseTestCase;
import com.coding.reward.calculator.controller.RewardsCalculatorController;
import com.coding.reward.calculator.exception.InvalidDataException;
import com.coding.reward.calculator.response.RewardsResponse;

public class RewardsCalculatorControllerTest extends BaseTestCase {

	@Autowired
	private RewardsCalculatorController controller;

	@ParameterizedTest
	@MethodSource("inputToCalculatePoints")
	public void testCalculateRewardPoints(Double purchaseAmount, Integer expectedPoints) {
		RewardsResponse response = controller.calculateRewards(purchaseAmount);
		Assertions.assertThat(response.getPoints()).isEqualTo(expectedPoints);
	}

	private static Stream<Arguments> inputToCalculatePoints() {
		return Stream.of(Arguments.of(120.0, 90), Arguments.of(100.0, 50), Arguments.of(150.0, 150),
				Arguments.of(99.0, 49), Arguments.of(50.0, 0));
	}

	@ParameterizedTest
	@MethodSource("inputAmountsForFailure")
	public void validateCityConnectivityFailure(Double purchaseAmount, String expectedErrorCode,
			String expectedErrorMessage) {
		try {
			controller.calculateRewards(purchaseAmount);
			fail("This testcase should throw exception");
		} catch (InvalidDataException ide) {
			assertThat(ide.getErrorCode()).isEqualTo(expectedErrorCode);
			assertThat(ide.getMessage()).isEqualTo(expectedErrorMessage);
		}
	}

	private static Stream<Arguments> inputAmountsForFailure() {
		return Stream.of(Arguments.of(null, "RC001", "Purchase amount is required")

		);
	}

}
