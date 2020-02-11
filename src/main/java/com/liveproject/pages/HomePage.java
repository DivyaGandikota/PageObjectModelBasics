package com.liveproject.pages;

import org.openqa.selenium.By;

import com.liveproject.base.Page;

public class HomePage extends Page{

	/* Removed from all pages. Uses superclass constructor Page() for driver. 
	 * WebDriver driver;
	
	public HomePage(WebDriver driver){
		this.driver=driver;
	}*/
	public LoginPage goToLogin() throws InterruptedException {
		Thread.sleep(3000);
		//CSS Path in console - $$("body > div.main-container-wrapper > div.zh-header-wrap > div > a.zh-login")
		//driver.findElement(By.cssSelector("a.zh-login")).click();
		click("loginlink_CSS");
		return new LoginPage(); //when you login from Home Page, it give the login page

	}
	public void goToFreeSignUp() {
		driver.findElement(By.cssSelector("a.zh-signup")).click();
	}
	public void goToSupport() {
		driver.findElement(By.cssSelector("a.zh-support")).click();	
	}
	public void validateFooterLinks() {
		
	}
}
