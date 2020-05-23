package com.cap.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.DataProvider;

import com.cap.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class TestUtil extends TestBase {
	public static String screenShotName;

	public static void captureScreenshot() throws IOException {
		Date d = new Date();
		screenShotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(scrFile,
				new File(System.getProperty("user.dir") + "/target/surefire-reports/html/" + screenShotName));

	}

	@DataProvider(name = "dp")
	public Object[][] getData(Method m) {

		String sheetName = m.getName();

		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		
		Hashtable<String, String> table = null;

		Object data[][] = new Object[rows - 1][1];

		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			table = new Hashtable<String, String>();
			for (int colNum = 0; colNum < cols; colNum++) {
				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
				data[rowNum - 2][0] = table; 
			}
		}
		return data;

	}

	public static boolean isTestRunnable(String testName, ExcelReader excel) {

		String sheetName = "test_suite";

		int rows = excel.getRowCount(sheetName);
		for (int rowNum = 2; rowNum <= rows; rowNum++) {

			String testCase = excel.getCellData(sheetName, "TCID", rowNum);
			if (testCase.equalsIgnoreCase(testName)) {
				String runMode = excel.getCellData(sheetName, "Runmode", rowNum);
				if (runMode.equalsIgnoreCase("Y"))
					return true;
				else
					return false;

			}

		}
		return false;

	}

}
