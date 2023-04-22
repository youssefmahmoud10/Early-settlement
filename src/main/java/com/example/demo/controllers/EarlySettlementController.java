package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.models.EarlySettlementRequest;
import com.example.demo.services.EarlySettlementService;

/**
 * @author YoussefMahmoud
 * @created Apr 22, 2023-2:50:42 AM
 */

@RestController
@RequestMapping("earlySettlementController") //http://localhost:8080/earlySettlementController/calculateEarlySettlement
public class EarlySettlementController {

	@Autowired
	private EarlySettlementService earlySettlementService;

	@CrossOrigin
	@PostMapping(path = "/calculateEarlySettlement")
	public String calculateEarlySettlement(@RequestBody EarlySettlementRequest earlySettlementRequest) {
		return earlySettlementService.calculateEarlySettlement(earlySettlementRequest);
	}

}