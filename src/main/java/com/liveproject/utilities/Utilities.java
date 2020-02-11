package com.liveproject.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import com.liveproject.base.Page;

public class Utilities extends Page{
	
	public static String screenshotName;

	public static void captureScreenshot() throws IOException {
		
		Date d = new Date();
		screenshotName=d.toString().replace(":", "_").replace(" ", "_")+".jpg";
		
		File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"/target/surefire-reports/html/"+screenshotName));
	}

	
	@DataProvider(name="dp")
	public static Object[][] getData(Method m) {

		String sheetName = m.getName(); //gives the sheet name. - "AddCustomerTest";
		
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);

		Object[][] data = new Object[rows - 1][1]; //col is 1 because hashtable-data is single value
		
		Hashtable<String, String> table= null;
		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			
			table = new Hashtable<String,String>();
			
			for (int colNum = 0; colNum < cols; colNum++) {
				
				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum)); //(firstname,"Divya") , (lastname,"G"),.. 
			//	data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
				data[rowNum - 2][0] =table;
			}
		}
		return data;
	}
	
	// Log test in excel, and provide runmode Y/N to execute or skip the test.
	public static boolean isTestRunnable(String testName,ExcelReader excel) {
		
		String sheetName="test-suite";
		int rows=excel.getRowCount(sheetName);
		
		for(int rowNum=2;rowNum<=rows;rowNum++) {
			
			String testCase=excel.getCellData(sheetName, "TCID", rowNum);
			
			if(testCase.equalsIgnoreCase(testName)) {
				
				String runmode=excel.getCellData(sheetName, "Runmode", rowNum);		
				
				if(runmode.equalsIgnoreCase("Y"))
					return true;
				else 
					return false;
			}
		}
		return false;
	}
}
