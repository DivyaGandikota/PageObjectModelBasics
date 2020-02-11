package com.liveproject.listeners;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.liveproject.base.Page;
import com.liveproject.utilities.MonitoringMail;
import com.liveproject.utilities.TestConfig;
import com.liveproject.utilities.Utilities;
import com.relevantcodes.extentreports.LogStatus;

public class CustomListeners extends Page implements ITestListener,ISuiteListener{

	public String messageBody;

	public void onTestStart(ITestResult result) {
		//onTestStart is for before a test is invoked.
		
		test = report.startTest(result.getName());	//for extent reports to work, start Test.	
	}

	public void onTestSuccess(ITestResult result) {
		
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		
		try {
			Utilities.captureScreenshot();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		test.log(LogStatus.PASS, result.getName().toUpperCase()+ " - Pass");
		test.log(LogStatus.PASS, test.addScreenCapture(Utilities.screenshotName));

		Reporter.log("Screenshot taken onTestSuccess");
		Reporter.log("<a target=\"_blank\" href="+Utilities.screenshotName+" >Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href="+Utilities.screenshotName+" ><img src="+Utilities.screenshotName+" height=100 width=100 ><img></a>");				
		
		report.endTest(test);
		report.flush();
		
	}

	public void onTestFailure(ITestResult result) {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		try {
			Utilities.captureScreenshot();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		test.log(LogStatus.FAIL, result.getName().toUpperCase()+ " - Failed with Exception "+ result.getThrowable());
		test.log(LogStatus.FAIL, test.addScreenCapture(Utilities.screenshotName));

		Reporter.log("Click to see screenshot");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href="+Utilities.screenshotName+" >Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href="+Utilities.screenshotName+" ><img src="+Utilities.screenshotName+" height=100 width=100 ><img></a>");				
		
		report.endTest(test);
		report.flush();
	}

	public void onTestSkipped(ITestResult result) {
		test.log(LogStatus.SKIP, result.getName().toUpperCase() + " is skipped as the runmode is No");
		report.endTest(test);
		report.flush();
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	public void onStart(ITestContext context) {
		
	}

	public void onFinish(ITestContext context) {
		
	}

	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ISuite suite) {
		//Send extentreports in auto e-mails,once build is success.
		MonitoringMail mail = new MonitoringMail();
		try {
			messageBody = "http://" + InetAddress.getLocalHost().getHostAddress()
					+ ":8080/job/PageObjectModelBasics/ExtentReports/";
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		try {
			mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
 
}
