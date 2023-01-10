package com.coding.reward.calculator.configuration;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties("rewards")
@Data
public class RewardsConfig {
	
	private List<RewardsLimits> limits;

}
