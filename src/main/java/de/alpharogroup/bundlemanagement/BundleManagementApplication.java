package de.alpharogroup.bundlemanagement;

import de.alpharogroup.bundlemanagement.configuration.ApplicationProperties;
import de.alpharogroup.file.search.PathFinder;
import de.alpharogroup.spring.boot.application.ApplicationHooks;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

@SpringBootApplication
@EnableTransactionManagement
@EnableConfigurationProperties({ ApplicationProperties.class })
public class BundleManagementApplication extends SpringBootServletInitializer
{

	public static void main(String[] args)
	{
		SpringApplication application = new SpringApplication(BundleManagementApplication.class);
		ApplicationHooks instance = ApplicationHooks.INSTANCE;
		instance.addDatabaseIfNotExists(application, PathFinder.getSrcMainResourcesDir(),
			"application.yml");
		application.run(args);
	}

}
