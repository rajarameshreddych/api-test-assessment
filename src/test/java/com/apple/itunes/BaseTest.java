package com.apple.itunes;

import org.testng.annotations.BeforeSuite;

public class BaseTest {
	protected String projectFolderPath = System.getProperty("user.dir");
	protected ConfigReader config;
	
	@BeforeSuite
	public void init() {
		config = new ConfigReader(projectFolderPath + "//Resources//config.properties");
	}
}
