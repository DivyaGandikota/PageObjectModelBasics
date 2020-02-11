package com.liveproject.rough;

import com.liveproject.base.Page;
import com.liveproject.pages.HomePage;
import com.liveproject.pages.LoginPage;
import com.liveproject.pages.ZohoAppPage;
import com.liveproject.pages.crm.accounts.AccountsPage;

public class LoginTest {

	public static void main(String[] args) throws InterruptedException {
	
		HomePage home = new HomePage();
		
		LoginPage login  = home.goToLogin();
		
		ZohoAppPage app = login.doLogin("g.divya.us@gmail.com","Munnig25");

		app.goToCRM();
		
		AccountsPage account= Page.menu.goToAccounts();
		
		account.accountName();
		
		/*HomePage home = new HomePage();
		home.goToLogin();
		LoginPage login = new LoginPage(); 
		login.goToLogin();
		ZohoAppPage app = new ZohoAppPage();
		app.goToCRM();
		
		Page.menu.goToAccounts();*/
		

	}
}
