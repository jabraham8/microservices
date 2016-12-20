package com.example.test.controller;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.service.TestService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/test")
@Api(value = "test", description = "Test API")
public class TestController {

	@Autowired
	private TestService testService;
	
	@ApiOperation(value = "Do somehting", httpMethod = "GET")
	@RequestMapping(path = "/hello",  method = GET, produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(OK)
	public Map<String, Object> doSomething() {
		return testService.doSomething();
	}
}
