package com.cap.testcases;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.cap.base.TestBase;
import com.cap.utilities.TestUtil;

public class AddCustomerTest extends TestBase{
	@Test(dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void addCustomerTest(Hashtable<String, String> data)
			throws InterruptedException {
		click("addCustBtn_CSS");
		type("firstName_CSS", data.get("First Name"));
		type("lastName_CSS", data.get("Last Name"));
		type("postCode_CSS", data.get("Pincode"));
		click("addCustomerBtn_CSS");
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(data.get("AlertText")));
		alert.accept();

	}

}
