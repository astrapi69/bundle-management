package de.alpharogroup.bundlemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={ DataSourceAutoConfiguration.class})
public class BundleManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(BundleManagementApplication.class, args);
	}

}
