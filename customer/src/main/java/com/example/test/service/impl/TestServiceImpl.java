package com.example.test.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.management.InstanceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.test.service.TestService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class TestServiceImpl implements TestService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestServiceImpl.class);
	
	@Autowired
	@LoadBalanced
	private RestTemplate template;
	
	@HystrixCommand(commandKey="doSomething")
	public Map<String, Object> doSomething() {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("message", "hello world!");
		
		LOGGER.info("Returning: " + result);
		LOGGER.debug("GET -> /test/hello . Response: {}", result);

		return result;
	}

	@Override
	@HystrixCommand(commandKey="fail", fallbackMethod="failureResponse", ignoreExceptions={InstanceNotFoundException.class})
	public void fail() {
		template.getForEntity("http://fail.com", String.class);
	}
	
	public void failureResponse() {
		//do some fallback
		LOGGER.info("Fallback executed successfully");
	}
}
