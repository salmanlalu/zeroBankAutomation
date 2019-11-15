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

public class PurchaseFCFunctionTest extends testBase {

	@Test(dataProvider = "payBillsDP", dataProviderClass = DataProviders.class)
	public static void purchasefcfunctiontest(Hashtable<String, String> data) {

		DataUtil.checkExecutionSuite(Constants.PAY_BILLS_SUITENAME, "PurchaseFCFunctionTest", data.get("RunMode"));

		try {

			testUtil.logIn(data.get("Username"), data.get("Password"));

			click("payBillsTab_XPATH");
			click("purchasexchangeTab_XPATH");

			select("purchaseCurrencyDropDown_XPATH", data.get("Currency"));
			type("purchaseAmountInput_XPATH", data.get("Amount"));
			select_radio("usdollarRadioBtn_XPATH");
			click("purchaseBtn_XPATH");

			assertThat(getTextFromMessage("purchaseCurrencySuccessMessage_XPATH"), is(data.get("Message")));

		} catch (AssertionError e) {

			log.info(e);
			Assert.fail(String.valueOf(e), e);
		}
	}
}