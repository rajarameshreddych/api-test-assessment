package com.apple.itunes;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.apple.itunes.SampleRRR;

public class SampleRRRTest extends BaseTest{

	@Test
	public void test1() {
		SampleRRR r1 = new SampleRRR();
		int n = r1.add(10, 10);
		Assert.assertEquals(n, 20);
	}
	
	@Test
	public void readConfigTest() {
		String baseUrl = config.getConfig("baseURI");
		Assert.assertEquals(baseUrl, "https://itunes.apple.com");
	}
}
