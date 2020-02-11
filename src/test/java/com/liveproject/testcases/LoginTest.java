package com.liveproject.testcases;

import org.testng.annotations.Test;

import com.liveproject.pages.HomePage;
import com.liveproject.pages.LoginPage;

public class LoginTest {

	@Test
	public void loginTest() throws InterruptedException {
		
		HomePage home = new HomePage();
		
		 LoginPage lp = home.goToLogin();
		
		 lp.doLogin("g.divya.us@gmail.com","Munnig25");
	}
	
}
