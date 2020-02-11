package com.liveproject.pages.crm;

import org.openqa.selenium.By;

import com.liveproject.base.Page;

public class CRMHomePage extends Page{
	
	public void verifyAccessZohoCRMBtn() {
		driver.findElement(By.cssSelector("a.act-btn.cta-btn")).click();
	}
	public void verifyPricingLink() {
		driver.findElement(By.cssSelector("li.leaf.zmenu-pricing")).click();
	}

}
