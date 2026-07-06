package com.silicon.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.silicon.practice.model.Amount;
import com.silicon.practice.service.Bank;

@RestController
@RequestMapping("/icici")
public class iciciBankController {

	@Autowired
	Bank bank;

	@GetMapping("/welcome")
	public String welcome() {
		System.out.println("welcome called");

		return "welcome to icici bank";
	}

	@PostMapping("/deposite")
	public String deposite(@RequestBody Amount amount) {
		
		String result = bank.depositeAmount(amount.getAmount());
		return result;
	}

}
