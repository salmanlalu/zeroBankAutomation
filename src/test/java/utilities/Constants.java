package utilities;

public class Constants {

	    //Constants for Suite
		public static String SUITE_SHEET = "Suite";
		public static String LOGINPAGE_SUITENAME = "LoginPageSuite";
		public static String ACCOUNT_SUMMARY_SUITENAME = "AccountSummarySuite";
		public static String TRANSFER_FUND_SUITENAME = "TransferFundSuite";
		public static String PAY_BILLS_SUITENAME ="PayBillsSuite";
		public static int SUITENAME_COLS = 0;
		public static int RUNMODES_COLS = 1;

		
		//Constants for Testcases
		public static String TestCase_SHEET = "TestCases";
		public static String DATA_SHEET = "TestData";
		public static int TESTCASENAME_COLS = 0;
		public static int RUNMODES_TestCase_COLS = 1;

		
		//General Runmodes constants
		public static String RUNMODES_YES = "Y";
		public static String RUNMODES_NO = "N";
		
		//Path for all the xcel files
		public static String SUITE_XCEL_PATH = System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\Suite.xlsx";
		public static String SUITE_LOGIN_XCEL_PATH = System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\LoginPageSuite.xlsx";
		public static String SUITE_ACCOUNT_SUMMARY_XCEL_PATH = System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\AccountSummarySuite.xlsx";
		public static String SUITE_TRANSFER_FUND_XCEL_PATH = System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\TransferFundSuite.xlsx";
		public static String SUITE_PAY_BILLS_XCEL_PATH = System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\PayBillsSuite.xlsx";
}
