package com.coding.reward.calculator.service;

import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.reward.calculator.configuration.RewardsConfig;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RewardsCalculatorService {

	@Autowired
	private RewardsConfig rewards;

	public Integer calculatePoints(Double purchaseAmount) {

		Collections.sort(rewards.getLimits(),
				(reward1, reward2) -> -1 * reward1.getLowerLimit().compareTo(reward2.getLowerLimit()));

		AtomicInteger points = new AtomicInteger(0);

		rewards.getLimits().forEach(limit -> {
			log.debug("Calculating the points for the amount {} with range {}-{} and point for each dollar = {}",
					purchaseAmount, limit.getLowerLimit(), limit.getUpperLimit(), limit.getPoints());
			if (purchaseAmount > limit.getLowerLimit()) {
				if (limit.getUpperLimit() != null) {
					if (limit.getUpperLimit() > purchaseAmount.intValue()) {
						points.addAndGet(limit.getPoints() * (purchaseAmount.intValue() - limit.getLowerLimit()));
					} else {
						points.addAndGet(limit.getPoints() * (limit.getUpperLimit() - limit.getLowerLimit()));
					}

				} else {
					points.addAndGet(limit.getPoints() * (purchaseAmount.intValue() - limit.getLowerLimit()));
				}
			}

		});

		return points.get();

	}

}
