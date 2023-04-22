package com.example.demo.services;

import org.springframework.stereotype.Service;
import com.example.demo.models.EarlySettlementRequest;

/**
 * @author YoussefMahmoud
 * @created Apr 22, 2023-2:53:32 AM
 */

@Service
public class EarlySettlementService {

	public String calculateEarlySettlement(EarlySettlementRequest earlySettlementRequest) {
		Double interest;
		Double loanCost;
		Double monthlyPayment;
		Double monthlyInterest;
		Double principal;
		Double beginningBalance;
		Double endingBalance;
		Double totalPrincipal = 0d;
		Double monthlyRate = (earlySettlementRequest.getInterestRate() / 100) / 12;
		final Integer numberOfPayments = earlySettlementRequest.getLoanPeriod() * 12;
		if (earlySettlementRequest.getInterestType() == 1) {
			interest = earlySettlementRequest.getLoanAmount() * (earlySettlementRequest.getInterestRate() / 100)
					* earlySettlementRequest.getLoanPeriod();
			loanCost = earlySettlementRequest.getLoanAmount() + interest;
			monthlyPayment = loanCost / numberOfPayments;
			monthlyInterest = interest / numberOfPayments;
			principal = monthlyPayment - monthlyInterest;
			for (int i = 0; i < earlySettlementRequest.getSettlementMonth(); i++)
				totalPrincipal += principal;
			return "The required amount: " + Math.round(((earlySettlementRequest.getLoanAmount() - totalPrincipal)
					+ (earlySettlementRequest.getLoanAmount() - totalPrincipal)
							* (earlySettlementRequest.getPrePaymentPenalty() / 100))
					* 100.0) / 100.0;
		} else if (earlySettlementRequest.getInterestType() == 2) {
			monthlyPayment = earlySettlementRequest.getLoanAmount() * monthlyRate
					* Math.pow(1 + monthlyRate, numberOfPayments) / (Math.pow(1 + monthlyRate, numberOfPayments) - 1);
			monthlyInterest = earlySettlementRequest.getLoanAmount() * monthlyRate;
			principal = monthlyPayment - monthlyInterest;
			beginningBalance = earlySettlementRequest.getLoanAmount();
			endingBalance = earlySettlementRequest.getLoanAmount() - principal;
			for (int i = 0; i < earlySettlementRequest.getSettlementMonth() - 1; i++) {
				beginningBalance -= principal;
				monthlyInterest = beginningBalance * monthlyRate;
				principal = monthlyPayment - monthlyInterest;
				endingBalance = beginningBalance - principal;
			}
			return "The required amount: " + Math.round(
					(endingBalance + endingBalance * (earlySettlementRequest.getPrePaymentPenalty() / 100)) * 100.0)
					/ 100.0;
		} else
			return "";
	}

}