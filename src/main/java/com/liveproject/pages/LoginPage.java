package com.liveproject.pages;

import com.liveproject.base.Page;

public class LoginPage extends Page {

	public ZohoAppPage doLogin(String username, String password) throws InterruptedException {
		Thread.sleep(3000);
		type("email_CSS",username);
		Thread.sleep(3000);
		click("nxtBtn_ID");
		Thread.sleep(3000);
		type("password_ID",password);
		click("nxtBtn_ID");

		return new ZohoAppPage(); // navigates to Apps page so should return ZohoAppPage

	}

}
