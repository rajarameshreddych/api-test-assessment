package com.apple.itunes;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	public String filePath;
	Properties properties;

	public ConfigReader() {
	}

	public ConfigReader(String filePath) {
		this.filePath = filePath;
		properties = new Properties();
		try{
			FileInputStream fis = new FileInputStream(filePath);
			properties.load(fis);
		}
		catch (IOException e) {
            e.printStackTrace();
        }
	}

	public String getConfig(String key) {
		return properties.getProperty(key);
	}
}
