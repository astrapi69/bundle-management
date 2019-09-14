package de.alpharogroup.bundlemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import de.alpharogroup.bundlemanagement.configuration.ApplicationProperties;

@SpringBootApplication
@EnableTransactionManagement
@EnableConfigurationProperties({ ApplicationProperties.class })
public class BundleManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(BundleManagementApplication.class, args);
	}

}
