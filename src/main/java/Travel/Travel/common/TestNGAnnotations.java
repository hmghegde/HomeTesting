package Travel.Travel.common;

import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;


import Travel.Travel.common.CommonAppObject;
import Travel.Travel.common.CommonException;
import Travel.Travel.common.CommonFileUtil;
import Travel.Travel.common.CommonTestNGUtil;

import Travel.Travel.common.LoggerUtil;

public abstract class TestNGAnnotations {

	public Logger logger;
	public Properties property;
	public boolean testResult = false;

	@BeforeSuite
	public void beforeBeginExcecution() throws Exception {
		// Backup Execution logs, report
		logger = Logger.getLogger(TestNGAnnotations.class);
		CommonAppObject.getInstance().setLogger(logger);
		logger.info("\n-------------Starting TestNG Reports and Logs Backup Process------------\n");
		
		CommonFileUtil.getInstance().executionBackUp("ExecutionLogs");
		CommonTestNGUtil.getInstance().testNGReportBackup();
		logger.info("\n-------------TestNG Reports and Logs Backup Complete------------\n");

		property = CommonAppObject.getInstance().getProperty();

	}

	@BeforeTest
	public void beforeBeginTest() throws CommonException {
	

	}

	@BeforeClass
	public void beforeBeginClass() throws CommonException {

		CommonAppObject.getInstance().setClassName(this.getClass().getSimpleName());
		logger = CommonAppObject.getInstance().getLogger();
	}

	@BeforeMethod
	public void beforeBeginScript(Method method) throws CommonException {
		testResult = false;
		CommonAppObject.getInstance().setTestMethodName(method.getName());
		LoggerUtil.getInstance().setLoggerFile();
		logger.info(
				"\n #####################################################################################################################################################################"
						+ "\nStarting execution of  the test case :" + method.getName() +

		"\n*********************************************************************************************************************************************************************");

	}

	@AfterMethod
	public void afterEndScript(Method method) {
		
		CommonAppObject.getInstance().setTestResult(testResult);
		// ALM Update, attachments, tearDown script details like Pass or fail

		logger.info("\n-*--*--*--*--*--*--*END OF TEST--*--*--*--*--*--*--*--*--*--*--*--*--*--*-\n");

	}

	@AfterTest
	public void afterEndTest() {
	

	}

	@AfterSuite
	public void afterExcecutionEnds() throws CommonException, Exception {
		// close all the opened instances like DB connections etc..
		DataBaseConnection.getInstance().closeDBConnection();
	}

	@DataProvider(name = "GetTestData")
	Object[][] getTestData(Method method) throws Exception {
		CommonAppObject.getInstance().setTestMethodName(method.getName());
		System.out.println("dataPath  " + CommonAppObject.getInstance().getProperty().getProperty("TestDataPath"));
		String excelDataPath = Travel.Travel.common.CommonAppObject.getInstance().getProperty().getProperty("TestDataPath");
		String sheetName = Travel.Travel.common.CommonAppObject.getInstance().getProperty().getProperty("TestDataSheetName");
		return new ExcelDataReader().getExcelSheet(excelDataPath, sheetName);

	}

}
