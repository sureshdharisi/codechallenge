package com.coding.reward.calculator.utils;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.coding.reward.calculator.configuration.RewardsConfig;
import com.coding.reward.calculator.configuration.RewardsLimits;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ModileObjectTests {

	@Test
	public void testRewardsConfig() {
		RewardsConfig config1 = createRewardsConfig(createRewardsLimits(50, 100, 1), createRewardsLimits(100, null, 2));
		RewardsConfig config2 = createRewardsConfig(createRewardsLimits(50, 100, 1), createRewardsLimits(100, null, 2));
		log.debug("Is equals = {}", config1.equals(config2));
		log.debug("Hashcode = {}", config1.equals(config2));
		log.debug("Is equals = {}", config1.equals(config2));

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
