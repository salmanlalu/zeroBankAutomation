package utilities;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import utilities.Constants;
import utilities.ExcelReader;
import utilities.DataUtil;

public class DataProviders {

	//dataprovider for logIn page suite and its testcases	
	@DataProvider(name="logInPageDP")
	public Object [][] getDataforlogInPageSuite(Method m){
		
		String testcase = m.getName();
		ExcelReader excel = new ExcelReader(Constants.SUITE_LOGIN_XCEL_PATH);
		
		return DataUtil.getData(testcase, excel);
	}
	
	//dataprovider for account summary suite and testcases
	@DataProvider(name="accountSummaryDP")
	public Object [][] getDataforAccountSummarySuite(Method m){
		
		String testcase = m.getName();
		ExcelReader excel = new ExcelReader(Constants.SUITE_ACCOUNT_SUMMARY_XCEL_PATH);
		
		return DataUtil.getData(testcase, excel);
	}
	
	//dataprovider for transfer fund suite and testcases
	@DataProvider(name="transferFundDP")
	public Object [][] getDataforTransferFundSuite(Method m){
		
		String testcase = m.getName();
		ExcelReader excel = new ExcelReader(Constants.SUITE_TRANSFER_FUND_XCEL_PATH);
		
		return DataUtil.getData(testcase, excel);
	}
	
	//dataprovider for pay bills suite and testcases
	@DataProvider(name="payBillsDP")
	public Object [][] getDataforPayBillsSuite(Method m){
		
		String testcase = m.getName();
		ExcelReader excel = new ExcelReader(Constants.SUITE_PAY_BILLS_XCEL_PATH);
		
		return DataUtil.getData(testcase, excel);
	}
	
}