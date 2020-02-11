package com.liveproject.pages;

import org.openqa.selenium.By;

import com.liveproject.base.Page;
import com.liveproject.pages.crm.CRMHomePage;

public class ZohoAppPage extends Page{

	public CRMHomePage goToCRM() {
		/*
		 * driver.findElement(By.xpath("//*[@id=\"zl-myapps\"]/div[1]/div[7]/div/a/div")).click();
		 */
		click("crmHome_XPATH");
		return new CRMHomePage();
	}

	public void goToMail() {
		driver.findElement(By.xpath("//*[@id=\"zl-myapps\"]/div[1]/div[10]/div/a/div")).click();
	}

	public void goToCliq() {
		driver.findElement(By.xpath("//*[@id=\"zl-myapps\"]/div[1]/div[4]/div/a/div")).click();
	}
}
