package com.liveproject.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.liveproject.pages.crm.accounts.AccountsPage;

public class TopMenu {

	/*
	 * TopMenu IS A page?? - Not a page.
	 * 
	 * HomePage HAS A TopMenu
	 * 
	 * LoginPage HAS A TopMenu
	 * 
	 * ZohoAppPage HAS A TopMenu
	 * 
	 */

	WebDriver driver;

	public TopMenu(WebDriver driver) {
		this.driver = driver;
	}

	public void goToHome() {

	}

	public void goToLeads() {

	}

	public void goToContacts() {

	}

	public AccountsPage goToAccounts() { //responsible for taking to AccountsPage

		
		driver.findElement(By.xpath("//*[@id=\"mainMenuTabDiv\"]/crm-menu/div[1]/crm-tab/div[2]/div[4]/a")).click();
	
		return new AccountsPage();
	}

	public void signOut() {

	}

}
