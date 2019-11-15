package com.webAppSecurities.zeroBank.payBills;

import java.util.Hashtable;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.testBase;

import utilities.Constants;
import utilities.DataProviders;
import utilities.DataUtil;
import utilities.testUtil;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PaySavedPayeeFunctionTest extends testBase {

	@Test(dataProvider = "payBillsDP", dataProviderClass = DataProviders.class)
	public static void paysavedpayeefunctiontest(Hashtable<String, String> data) throws AssertionError {

		DataUtil.checkExecutionSuite(Constants.PAY_BILLS_SUITENAME, "PaySavedPayeeFunctionTest", data.get("RunMode"));

		try {

			testUtil.logIn(data.get("Username"), data.get("Password"));

			click("payBillsTab_XPATH");

			select("payeeDropDown_XPATH", data.get("Payee"));
			select("accountDropDown_XPATH", data.get("Account"));
			type("amountInput_XPATH", data.get("Amount"));
			driver.findElement(By.xpath(OR.getProperty("dateInput_XPATH"))).click();
			driver.findElement(By.linkText("13")).click();
			type("descriptionInput_XPATH", data.get("Description"));
			click("payBtn_XPATH");

			assertThat(getTextFromMessage("payeeSavedSuccessMessage_XPATH"), is(data.get("Message")));

		} catch (AssertionError e) {

			log.info(e);
			Assert.fail(String.valueOf(e), e);
		}
	}
}
