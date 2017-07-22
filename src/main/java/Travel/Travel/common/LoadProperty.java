package Travel.Travel.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class LoadProperty {

	private Properties property = new Properties();
	
	Logger logger = Logger.getLogger(LoadProperty.class);

	private static LoadProperty instance = null;

	private LoadProperty() throws Exception {
		Properties property = loadAllProperties();
		CommonAppObject.getInstance().setProperty(property);
	}

	public static LoadProperty getInstance() throws Exception {
		if (instance == null) {
			synchronized (LoadProperty.class) {
				instance = new LoadProperty();
			}
		}
		return instance;
	}

	public Properties loadAllProperties() throws CommonException {

		logger.info("------------ Loading below property files ------------\n");
		File[] fList = CommonFileUtil.getInstance().listFiles("./Properties");
		try {
			for (File file : fList) {
				if (file.isFile()) {
					Properties tempProperty = new Properties();
					logger.info("\t"+file+"\n");
					tempProperty.load(new FileInputStream(file));
					property.putAll(tempProperty);
				}
			}
		} catch (IOException e) {
			throw new CommonException(e, e.getMessage());
		}
		logger.info("------------ Property files loading Complete------------\n");
		return property;
	}
}
