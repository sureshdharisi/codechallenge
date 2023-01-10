package com.coding.reward.calculator.service;

import java.util.Arrays;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.coding.reward.calculator.configuration.RewardsConfig;
import com.coding.reward.calculator.configuration.RewardsLimits;

@ExtendWith(MockitoExtension.class)
public class RewardsCalculatorServiceMockTest {

	@InjectMocks
	private RewardsCalculatorService calculatorService;

	public void initMocks() {
		MockitoAnnotations.openMocks(this);
	}

	@ParameterizedTest
	@MethodSource("inputToCalculatePoints")
	public void testCalculateRewardPoints(Double purchaseAmount, RewardsConfig config, Integer expectedPoints) {
		ReflectionTestUtils.setField(calculatorService, "rewards", config);
		Integer rewardsPoints = calculatorService.calculatePoints(purchaseAmount);
		Assertions.assertThat(rewardsPoints).isEqualTo(expectedPoints);
	}

	private static Stream<Arguments> inputToCalculatePoints() {
		return Stream.of(
				Arguments.of(120.0,
						createRewardsConfig(createRewardsLimits(50, 100, 1), createRewardsLimits(100, null, 2)), 90),
				Arguments.of(120.0,
						createRewardsConfig(createRewardsLimits(50, 100, 1), createRewardsLimits(100, 150, 2)), 90),
				Arguments.of(100.0,
						createRewardsConfig(createRewardsLimits(50, 100, 1), createRewardsLimits(100, 120, 2)), 50),
				Arguments.of(150.0,
						createRewardsConfig(createRewardsLimits(50, 100, 1), createRewardsLimits(100, 130, 2)), 110),
				Arguments.of(99.0,
						createRewardsConfig(createRewardsLimits(50, 100, 1), createRewardsLimits(100, null, 2)), 49),
				Arguments.of(50.0,
						createRewardsConfig(createRewardsLimits(50, 100, 1), createRewardsLimits(100, 120, 2)), 0),
				Arguments.of(120.0,
						createRewardsConfig(createRewardsLimits(50, 100, 1), createRewardsLimits(100, 120, 2)), 90));
	}

	private static RewardsConfig createRewardsConfig(RewardsLimits limit1, RewardsLimits limit2) {
		RewardsConfig config = new RewardsConfig();
		config.setLimits(Arrays.asList(limit1, limit2));
		return config;
	}

	private static RewardsLimits createRewardsLimits(Integer lowerLimit, Integer upperLimit, Integer points) {
		RewardsLimits limits = new RewardsLimits();
		limits.setLowerLimit(lowerLimit);
		limits.setUpperLimit(upperLimit);
		limits.setPoints(points);
		return limits;
	}

}
