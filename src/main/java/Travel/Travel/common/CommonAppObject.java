package Travel.Travel.common;

import java.util.Properties;

import org.apache.log4j.Logger; 

public class CommonAppObject {

	private String testMethodName;
	private Logger logger;
	private Properties Property;
	private static CommonAppObject instance = null;
	private String  logDirPath;
	private boolean testResult;
	private String className;

	

	private CommonAppObject() {

	}

	

	public static CommonAppObject getInstance() {

		if (instance == null) {
			synchronized (CommonAppObject.class) {
				instance = new CommonAppObject();
			}
		}
		return instance;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * Get method for property
	 * 
	 * @return property
	 * @throws Exception
	 */

	public Properties getProperty() {
		try {
			LoadProperty.getInstance();
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return Property;
	}

/*	public Logger getLogger() {

		if (logger == null) {

			logger = Logger.getLogger(CommonAppObject.getInstance().getClassName().getClass());
		}
		return logger;
	}
*/
	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public String getTestMethodName() {
		return testMethodName;
	}

	public void setTestMethodName(String testMethodName) {
		this.testMethodName = testMethodName;
	}

	/*
	 * public String getTestClassName() { return
	 * StringUtil.getInstance().getClassName(); }
	 */

	public void setProperty(Properties property) {
		Property = property;
	}

	
	public boolean getTestResult() {
		return testResult;
	}

	public void setTestResult(boolean testResult) {
		this.testResult = testResult;
	}

	public String getLogDirPath() {
		return logDirPath;
	}

	public void setLogDirPath(String logDirPath) {
		this.logDirPath = logDirPath;
	}
}
