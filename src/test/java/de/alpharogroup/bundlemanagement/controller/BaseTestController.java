package de.alpharogroup.bundlemanagement.controller;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.alpharogroup.bundlemanagement.configuration.ApplicationConfiguration;

@RunWith(SpringRunner.class)
@WebMvcTest
@ActiveProfiles("test")
public abstract class BaseTestController
{

	@Autowired
	protected MockMvc mockMvc;

	ObjectMapper objectMapper;

	public BaseTestController()
	{
		ApplicationConfiguration.initialize(objectMapper = new ObjectMapper());
	}

	public String getBaseUrl(int serverPort)
	{
		return "http://localhost:" + serverPort + ApplicationConfiguration.REST_VERSION;
	}

}
