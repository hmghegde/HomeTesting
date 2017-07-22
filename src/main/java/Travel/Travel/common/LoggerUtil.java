package Travel.Travel.common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;



public class LoggerUtil {

	public java.util.Date date = new java.util.Date();
	public SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy_hh_mm_ss");
	public Appender fh;
	Properties property = CommonAppObject.getInstance().getProperty();
	public String TestCasefolder;
	
	
	Logger logger = CommonAppObject.getInstance().getLogger();

	
	private static LoggerUtil instance = null;

	/*
	 * A private Constructor prevents any other class from instantiating.
	 */
	private LoggerUtil()  {
		
	}

	/* Static 'instance' method */
	public static LoggerUtil getInstance() throws CommonException {
		if (instance == null)
			instance = new LoggerUtil();

		return instance;
	}
	
	/**
	 * // This method creates a folder with test class name and a log file will
	 * be create to log the execution of entire test case. Each test case case
	 * will have on specific log file.
	 **/
	public File setLoggerFile() {

		
		String testName = CommonAppObject.getInstance().getTestMethodName();
		String className = CommonAppObject.getInstance().getClassName();
		
		
		String created_date = dateFormat.format(date);
		String current_dir = System.getProperty("user.dir");
		String logDirPath = current_dir + "/ExecutionLogs/" + className + "_" + created_date;
		CommonFileUtil.getInstance().createDirectory(logDirPath);
		CommonAppObject.getInstance().setLogDirPath(logDirPath);
		File logDir = new File(logDirPath);
		
		
		//logDir.mkdir();
		File file = new File(logDir + "/" + testName + ".log");
		String fileName = file.getAbsolutePath();

		try {
			fh = new FileAppender(new PatternLayout("[%-5p] [%d]  [ %c %M %L] - %m%n"), fileName, false);
			logger.removeAllAppenders();
			logger.addAppender(fh);
		} catch (IOException e) {
			logger.error("IOException:", e);
			e.printStackTrace();
		}

		//logger.info("Test case log  file will be  :" + fileName);
		return file;
	}
	
	public void printResult(boolean testResult) throws Exception{
		printResult(testResult,"");
	}
	
	
	
	public void printResult(boolean testResult, String msg) throws Exception{
		
		
		 if(testResult){	        	
	        	logger.info("#############\t "+CommonAppObject.getInstance().getTestMethodName()+" :: PASSED \t#############\n" );        	
	        		        	 
	        	
	        }else{
	        	logger.info("#############\t "+CommonAppObject.getInstance().getTestMethodName()+" :: FAILED \t#############\n");
	        	throw new Exception("\n"+msg);
	        }
		 logger.info("\n-*--*--*--*--*--*--*END OF TEST--*--*--*--*--*--*--*--*--*--*--*--*--*--*-\n");
	}
	


}
