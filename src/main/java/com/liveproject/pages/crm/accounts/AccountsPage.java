package com.liveproject.pages.crm.accounts;

import org.openqa.selenium.By;

import com.liveproject.base.Page;

public class AccountsPage extends Page {

	public void accountName() {
		driver.findElement(By.cssSelector("span.mL2.vam.cP.filterLabel")).click();
	}
}
