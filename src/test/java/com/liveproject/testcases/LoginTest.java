package com.liveproject.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.liveproject.pages.HomePage;
import com.liveproject.pages.LoginPage;
import com.liveproject.utilities.Utilities;

public class LoginTest {

	@Test(dataProviderClass = Utilities.class, dataProvider = "dp")
	public void loginTest(Hashtable<String, String> data) throws InterruptedException {

		HomePage home = new HomePage();

		LoginPage lp = home.goToLogin();

		lp.doLogin(data.get("username"), data.get("password"));
		// lp.doLogin("g.divya.us@gmail.com","Munnig25");
	}

}
