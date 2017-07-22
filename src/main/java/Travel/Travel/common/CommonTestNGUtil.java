package Travel.Travel.common;


import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;


import org.apache.log4j.Logger;




public class CommonTestNGUtil {

	Logger logger = EMMAppObject.getInstance().getLogger();
	SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy_hh_mm_ss");

	
	private static CommonTestNGUtil instance = null;

	/*
	 * A private Constructor prevents any other class from instantiating.
	 */
	private CommonTestNGUtil() {
		
	}

	/* Static 'instance' method */
	public static CommonTestNGUtil getInstance()  {
		if (instance == null)
			instance = new CommonTestNGUtil();

		return instance;
	}
	
	/**
	 * // Copy source directory into destination directory // including its
	 * child directories and files. When // the destination directory is not
	 * exists it will // be created. This copy process also preserve the // date
	 * information of the file.
	 **/
	public void testNGReportBackup() {

		try {
			

			String source = "./test-output";
			Path path = Paths.get(source);
			String destPath = "./Reports/Archives/";
		
			BasicFileAttributes attributes = null;
			attributes = Files.readAttributes(path, BasicFileAttributes.class);

			FileTime cdate = attributes.lastModifiedTime();
			String created_date = dateFormat.format(cdate.toMillis());

			String dirname = "TestNGReports_" + created_date + ".zip";
			String destination = destPath + dirname;
			//File srcDir = new File(source);
			// File destDir = new File(destination);

			logger.debug("Backing up previous run reports in folder " + dirname);

			EMMFileUtil emmUtil = EMMFileUtil.getInstance();
			// FileUtils.copyDirectory(srcDir, destDir);
			emmUtil.zipFolder(source, destination);
			//FileUtils.deleteDirectory(srcDir);
			//FileUtils.deleteDirectory(srcDir);

			logger.info("TestNG reports of previous run are archived at  " + destination);
			logger.info("-------------TestNG Reports Backup Complete------------");

		} catch (IOException e) {
			e.printStackTrace();
			logger.error("IO Exception", e);
		} catch (Exception e) {

			e.printStackTrace();
			logger.error("File zip Exception", e);
		}

	}

	/*@DataProvider(name="GetTestData")
	Object[][] getTestData(Method method) throws Exception{	
		EMMAppObject.getInstance().setTestMethodName(method.getName());
		String excelDataPath = EMMAppObject.getInstance().getProperty().getProperty("TestDataPath");
		String sheetName = EMMAppObject.getInstance().getProperty().getProperty("TestDataSheetName");
		return new ExcelDataReader().getExcelSheet(excelDataPath, sheetName);
	
	}*/

}
