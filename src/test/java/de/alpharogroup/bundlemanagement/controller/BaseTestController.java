package de.alpharogroup.bundlemanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.alpharogroup.bundlemanagement.configuration.ApplicationConfiguration;
import de.alpharogroup.bundlemanagement.service.ResourcebundlesService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestControllersConfiguration.class)
@WebMvcTest
@ActiveProfiles("test")
public abstract class BaseTestController
{

	@Autowired
	protected MockMvc mvc;

	ObjectMapper objectMapper;

	public BaseTestController(){
		ApplicationConfiguration.initialize(objectMapper = new ObjectMapper());
	}

}
