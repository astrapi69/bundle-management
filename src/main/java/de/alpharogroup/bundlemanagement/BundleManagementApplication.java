package de.alpharogroup.bundlemanagement;

import de.alpharogroup.file.search.PathFinder;
import de.alpharogroup.jdbc.PostgreSQLConnectionsExtensions;
import de.alpharogroup.yaml.YamlToPropertiesExtensions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import de.alpharogroup.bundlemanagement.configuration.ApplicationProperties;

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
		SpringApplication.run(BundleManagementApplication.class, args);
	}

	static void addInitHooks(final SpringApplication application) {
		application.addListeners((ApplicationListener<ApplicationStartingEvent>) event -> {

			try
			{
				File yamlFile = new File(PathFinder.getSrcMainResourcesDir(), "application.yml");
				Properties properties = YamlToPropertiesExtensions
					.toProperties(yamlFile.getAbsolutePath());
				String host = properties.getProperty("app.db-host");
				int port = Integer.valueOf(properties.getProperty("app.db-port"));
				String dbName = properties.getProperty("app.db-name");
				String dbUser = properties.getProperty("app.db-username");
				String dbPw = properties.getProperty("app.db-password");
				PostgreSQLConnectionsExtensions.newDatabase(host,
					port,
					dbName,
					dbUser, dbPw, "", "");
			}
			catch (SQLException e)
			{
				throw new RuntimeException(e);
			}
			catch (ClassNotFoundException e)
			{
				throw new RuntimeException(e);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		});
	}

}
