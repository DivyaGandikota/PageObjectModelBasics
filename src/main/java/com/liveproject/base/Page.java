package com.liveproject.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.liveproject.utilities.ExcelReader;
import com.liveproject.utilities.ExtentManager;
import com.liveproject.utilities.Utilities;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Page {

	public static WebDriver driver;

	public static Properties config = new Properties();

	public static Properties or = new Properties();

	public static FileInputStream fis;

	public static Logger log = Logger.getLogger("devpinoyLogger");

	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "/src/test/resources/com/liveproject/excel/testData.xlsx");

	public static WebDriverWait wait;

	public ExtentReports report = ExtentManager.getInstance();

	public static ExtentTest test;

	public static String browser;
	public static TopMenu menu;

	public Page() {

		if (driver == null) {

			try {
				fis = new FileInputStream(System.getProperty("user.dir")
						+ "/src/test/resources/com/liveproject/properties/Config.properties");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			// alt+enter for file path in "Config.properties"
			try {
				config.load(fis);
				log.debug("Config file loaded");
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				fis = new FileInputStream(System.getProperty("user.dir")
						+ "/src/test/resources/com/liveproject/properties/OR.properties");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}

			try {
				or.load(fis);
				log.debug("OR file loaded");

			} catch (IOException e) {
				e.printStackTrace();
			}

			// for jenkins system env to run using the parameterisation (browser filter
			// config)
			if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {
				browser = System.getenv("browser"); // gets browser from what you select in Jenkins
			} else {
				browser = config.getProperty("browser"); // gets browser from config.prop
			}
			config.setProperty("browser", browser);

			// reading browser from config properties.
			if (config.getProperty("browser").equals("firefox")) {

				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();

			} else if (config.getProperty("browser").equals("chrome")) {

				WebDriverManager.chromedriver().setup();
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_setting_values.notifications", 2);
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("--disable-extensions");
				options.addArguments("--disable-infobars");

				driver = new ChromeDriver(options);

			} else if (config.getProperty("browser").equals("safari")) {

				driver = new SafariDriver();

			}
			driver.get(config.getProperty("testsiteurl"));
			log.debug("Navigated to : " + config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 10);

			menu = new TopMenu(driver);
		}
	}

	public void click(String locator) {

		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(or.getProperty(locator))).click();
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(or.getProperty(locator))).click();
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(or.getProperty(locator))).click();
		}
		test.log(LogStatus.INFO, "Clicked on : " + locator);
	}

	public void type(String locator, String value) {
		if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(or.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_ID")) {
			System.out.println(locator + "------" + value);
			System.out.println(or.getProperty(locator));
			driver.findElement(By.id(or.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(or.getProperty(locator))).sendKeys(value);
		}
		test.log(LogStatus.INFO, "Typed in : " + locator + " entered value is " + value);
	}

	public void verifyEquals(String actual, String expected) throws IOException {
		try {
			Assert.assertEquals(actual, expected); // soft assertion

		} catch (Throwable t) {
			// take screenshot
			Utilities.captureScreenshot();

			// Logging in ReportNG report
			Reporter.log("<br>" + "Verification failed with : " + t.getMessage() + "<br>");
			Reporter.log("<a target=\"_blank\" href=" + Utilities.screenshotName + " ><img src="
					+ Utilities.screenshotName + " height=100 width=100 ><img></a>");

			// Extent Report
			test.log(LogStatus.FAIL, "Verification failed with exception : " + t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(Utilities.screenshotName));

		}
	}

	static WebElement dropdown;

	public void select(String locator, String value) {

		if (locator.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(or.getProperty(locator)));
		} else if (locator.endsWith("_XPATH")) {
			dropdown = driver.findElement(By.xpath(or.getProperty(locator)));
		} else if (locator.endsWith("_ID")) {
			dropdown = driver.findElement(By.id(or.getProperty(locator)));
		}

		Select select = new Select(dropdown);
		select.selectByVisibleText(value);

		log.debug("Selecting from an element : " + locator + " value as : " + value);
		test.log(LogStatus.INFO, "Selecting from dropdown : " + locator + " value as " + value);

	}

	public boolean isElementPresent(By by) {

		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public static void quit() {
		driver.quit();
	}
}
