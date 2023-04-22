package com.example.demo.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

/**
 * @author YoussefMahmoud
 * @created Apr 22, 2023-2:49:40 AM
 */

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EarlySettlementRequest {

	Double loanAmount;
	Double interestRate;
	Integer loanPeriod;
	Integer interestType;
	Integer settlementMonth;
	Double prePaymentPenalty;

}