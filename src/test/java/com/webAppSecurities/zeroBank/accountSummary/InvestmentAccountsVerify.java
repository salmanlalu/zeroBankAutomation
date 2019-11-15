package com.webAppSecurities.zeroBank.accountSummary;

import java.util.Hashtable;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.testBase;

import utilities.DataProviders;
import utilities.DataUtil;
import utilities.testUtil;

public class InvestmentAccountsVerify extends testBase {

	@Test(dataProvider = "accountSummaryDP", dataProviderClass = DataProviders.class)
	public static void investmentaccountsverify(Hashtable<String, String> data) throws AssertionError {

		DataUtil.checkExecutionSuite("AccountSummarySuite", "InvestmentAccountsVerify", data.get("RunMode"));

		try {
			
			testUtil.logIn(data.get("Username"), data.get("Password"));

			click("brokerageBtn_XPATH");

			Select s = new Select(driver.findElement(By.xpath(OR.getProperty("showTransactionDropDown_XPATH"))));

			assertThat(s.getFirstSelectedOption().getText(), is(data.get("Options")));

		} catch (AssertionError e) {

			log.info(e);
			Assert.fail(String.valueOf(e), e);
		}
	}

}