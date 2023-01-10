package com.coding.reward.calculator.exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.coding.reward.calculator.response.ExceptionResponse;

public class RewardsControllerAdviceTest {

	private RewardsControllerAdvice advice = new RewardsControllerAdvice();

	@Test
	public void testToHandleInvalidDataException() {
		InvalidDataException exception = new InvalidDataException("RC001", "Purchase amount is required");

		ExceptionResponse response = advice.handleInvalidDataException(exception);
		assertThat(response.getErrorCode()).isEqualTo("RC001");
		assertThat(response.getErrorMessage()).isEqualTo("Purchase amount is required");
	}

}
