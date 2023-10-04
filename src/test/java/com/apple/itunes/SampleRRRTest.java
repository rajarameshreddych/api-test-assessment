package com.apple.itunes;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.apple.itunes.SampleRRR;

public class SampleRRRTest {

	@Test
	public void test1() {
		SampleRRR r1 = new SampleRRR();
		int n = r1.add(10, 10);
		Assert.assertEquals(n, 20);
	}
}
