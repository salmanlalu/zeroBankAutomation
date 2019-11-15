package com.webAppSecurities.zeroBank.transferfund;

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

public class TransferFundVerifyPage extends testBase {

	@Test(dataProvider = "transferFundDP", dataProviderClass = DataProviders.class)
	public static void transferfundverifypage(Hashtable<String, String> data) throws AssertionError {

		DataUtil.checkExecutionSuite(Constants.TRANSFER_FUND_SUITENAME, "TransferFundVerifyPage", data.get("RunMode"));

		try {
			
			testUtil.logIn(data.get("Username"), data.get("Password"));

			click("transferFundTab_XPATH");

			assertThat(getTextFromMessage("tfVerifyPageMessage_XPATH"), is(data.get("Message")));

		} catch (AssertionError e) {

			log.info(e);
			Assert.fail(String.valueOf(e), e);
		}
	}

}
