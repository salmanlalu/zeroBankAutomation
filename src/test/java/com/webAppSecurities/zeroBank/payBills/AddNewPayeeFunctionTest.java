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

public class AddNewPayeeFunctionTest extends testBase {

	@Test(dataProvider = "payBillsDP", dataProviderClass = DataProviders.class)
	public static void addnewpayeefunctiontest(Hashtable<String, String> data) {

		DataUtil.checkExecutionSuite(Constants.PAY_BILLS_SUITENAME, "AddNewPayeeFunctionTest", data.get("RunMode"));

		try {

			testUtil.logIn(data.get("Username"), data.get("Password"));

			click("payBillsTab_XPATH");
			click("addPayeeTab_XPATH");

			type("payeeNameInput_XPATH", data.get("PayeeName"));
			type("payeeAddressInput_XPATH", data.get("Address"));
			type("accountInput_XPATH", data.get("Account"));
			type("payeeDeatilsInput_XPATH", data.get("Details"));
			click("addBtn_XPATH");

			assertThat(getTextFromMessage("addPayeeVerifyMessage_XPATH"), is(data.get("Message")));

		} catch (AssertionError e) {

			log.info(e);
			Assert.fail(String.valueOf(e), e);
		}

	}
}
