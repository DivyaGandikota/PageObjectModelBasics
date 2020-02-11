package com.liveproject.testcases;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.liveproject.base.Page;
import com.liveproject.pages.ZohoAppPage;
import com.liveproject.utilities.Utilities;


public class CreateAccountTest {
	
	
	@Test(dataProviderClass=Utilities.class,dataProvider="dp")
	public void createAccountTest(Hashtable<String,String> data){
		
		ZohoAppPage zp = new ZohoAppPage();
		zp.goToCRM();
		Page.menu.goToAccounts();
//		AccountsPage account = 
	//	CreateAccountPage cap = account.gotoCreateAccounts();
	//	cap.createAccount(data.get("accountname"));
		Assert.fail("Create account test failed");
		
	}

}
