package com.example.test.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.test.service.TestService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class TestServiceImpl implements TestService {

	@HystrixCommand(commandKey="doSomething")
	public Map<String, Object> doSomething() {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("message", "hello world!");

		return result;
	}
}
