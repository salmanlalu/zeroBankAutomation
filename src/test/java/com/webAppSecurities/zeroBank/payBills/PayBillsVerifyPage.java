package com.webAppSecurities.zeroBank.payBills;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.testBase;

import utilities.Constants;
import utilities.DataProviders;
import utilities.DataUtil;
import utilities.testUtil;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PayBillsVerifyPage extends testBase {

	@Test(dataProvider = "payBillsDP", dataProviderClass = DataProviders.class)
	public static void paybillsverifypage(Hashtable<String, String> data) throws AssertionError {

		DataUtil.checkExecutionSuite(Constants.PAY_BILLS_SUITENAME, "PayBillsVerifyPage", data.get("RunMode"));

		try {

			testUtil.logIn(data.get("Username"), data.get("Password"));

			click("payBillsTab_XPATH");

			assertThat(getTextFromMessage("payBillsVerifyMessage_XPATH"), is(data.get("Message")));

		} catch (AssertionError e) {

			log.info(e);
			Assert.fail(String.valueOf(e), e);
		}
	}
}
