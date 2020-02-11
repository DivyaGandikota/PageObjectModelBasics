package com.liveproject.testcases;

import org.testng.annotations.AfterSuite;

import com.liveproject.base.Page;

public class BaseTest {

	
	@AfterSuite
	public void tearDown(){
		Page.quit();
	}
}
