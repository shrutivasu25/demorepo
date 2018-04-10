package com.invoke.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class TestConfiguration {
	
	protected static Properties loadTestConfigurations(Logger log) {
		File file;
		FileInputStream fileInput = null;
		Properties config = new Properties();
		log.info("Reading/Loading test configurations");
		file = new File("src/main/resources/config.properties");
		try {
			fileInput = new FileInputStream(file);
			config.load(fileInput);
		} catch(FileNotFoundException e) {
			throw new RuntimeException("File " + "src/main/resources/config.properties" + " file.\n" + e.getStackTrace().toString());
		} catch(IOException  e) {
			throw new RuntimeException("Could not read " + "src/main/resources/config.properties" + " file.\n" + e.getStackTrace().toString());
		}
		return config;
	}	
}
