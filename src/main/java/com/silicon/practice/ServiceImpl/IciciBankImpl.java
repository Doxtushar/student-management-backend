package com.silicon.practice.ServiceImpl;

import org.springframework.stereotype.Service;

import com.silicon.practice.service.Bank;

@Service
public class IciciBankImpl implements Bank{

	@Override
	public String depositeAmount(int amount) {
		
		return "amount Added in icici bank succesfully"+amount;
	}

}
