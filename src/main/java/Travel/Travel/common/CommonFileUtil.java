package Travel.Travel.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

/**
 * Contains some methods to zip folder/file, list files and folders from a directory
 */
public class CommonFileUtil {

	//Logger logger = CommonAppObject.getInstance().getLogger();

	private static CommonFileUtil instance = null;

	private CommonFileUtil() {
		
	}

	public static CommonFileUtil getInstance() {
		if (instance == null) {
			synchronized (CommonFileUtil.class) {
				instance = new CommonFileUtil();
			}
		}
		return instance;
	}

	/**
	 * List all the files and folders from a directory
	 * 
	 * @param directoryName
	 *            to be listed
	 */
	public void listFilesAndFolders(String directoryName) {
		File directory = new File(directoryName);
		// get all the files from a directory
		File[] fList = directory.listFiles();
		for (File file : fList) {
			System.out.println(file.getName());
		}
	}

	/**
	 * List all the files under a directory
	 * 
	 * @param directoryName
	 *            to be listed
	 */
	public File[] listFiles(String directoryName) {
		File directory = new File(directoryName);
		// get all the files from a directory
		File[] fList = directory.listFiles();
		return fList;
	}

	/**
	 * List all the folder under a directory
	 * 
	 * @param directoryName
	 *            to be listed
	 */
	public void listFolders(String directoryName) {
		File directory = new File(directoryName);
		// get all the files from a directory
		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (file.isDirectory()) {
				System.out.println(file.getName());
			}
		}
	}

	/**
	 * List all files from a directory and its sub directories
	 * 
	 * @param directoryName
	 *            to be listed
	 */
	public void listFilesAndFilesSubDirectories(String directoryName) {
		File directory = new File(directoryName);
		// get all the files from a directory
		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (file.isFile()) {
				System.out.println(file.getAbsolutePath());
			} else if (file.isDirectory()) {
				listFilesAndFilesSubDirectories(file.getAbsolutePath());
			}
		}
	}

	public void zipFolder(String srcFolder, String destZipFile) throws Exception {
		logger.info("Going to archive the files:" + srcFolder);
		ZipOutputStream zip = null;
		FileOutputStream fileWriter = null;

		fileWriter = new FileOutputStream(destZipFile);
		zip = new ZipOutputStream(fileWriter);

		addFolderToZip("", srcFolder, zip);
		zip.flush();
		zip.close();
		logger.info("Compressed folder is :" + destZipFile);
	}

	private void addFileToZip(String path, String srcFile, ZipOutputStream zip) throws Exception {

		File folder = new File(srcFile);
		if (folder.isDirectory()) {
			addFolderToZip(path, srcFile, zip);
		} else {
			byte[] buf = new byte[1024];
			int len;
			FileInputStream in = new FileInputStream(srcFile);
			zip.putNextEntry(new ZipEntry(path + "/" + folder.getName()));
			while ((len = in.read(buf)) > 0) {
				zip.write(buf, 0, len);
			}
			in.close();
		}
	}

	private void addFolderToZip(String path, String srcFolder, ZipOutputStream zip) throws Exception {
		File folder = new File(srcFolder);

		for (String fileName : folder.list()) {
			if (path.equals("")) {
				addFileToZip(folder.getName(), srcFolder + "/" + fileName, zip);
			} else {
				addFileToZip(path + "/" + folder.getName(), srcFolder + "/" + fileName, zip);
			}
		}
	}

	/**
	 * This method moves all historical files to archive folder
	 */

	public void executionBackUp(String sourceDirPath) {

		String  userDir = System.getProperty("user.dir");
		
		
		File sourceDir = new File(userDir+"/"+sourceDirPath);
		System.out.println(sourceDir.getAbsolutePath());
		createDirectory(sourceDir + "/Archives");
		File destDir = new File(sourceDir + "/Archives/");		
		logger.info("Archiving the log files from previous run at " + destDir);
		if (sourceDir.isDirectory()) {

			File[] content = sourceDir.listFiles();
			if (content.length != 0) {

				for (int i = 0; i < content.length; i++) {

					if (content[i].isFile()) {

						if (content[i].getName().equals("ExeTrace.log")) {
							continue;
						} else {

							try {
								FileUtils.moveFileToDirectory(content[i], destDir, true);
							} catch (IOException e) {
								logger.error("IOException ", e);
								e.printStackTrace();
							}
						}
					}

					if (content[i].isDirectory()) {
						if (content[i].getName().equals("Archives")) {
							continue;
						} else {
							try {
								FileUtils.moveDirectoryToDirectory(content[i], destDir, true);
							} catch (IOException e) {
								logger.error("IOException ", e);
								e.printStackTrace();
							}
						}
					}
				}
			} else {
				logger.info("No files to archive ");

			}

		}

	}
	/* private void directoryCheck(String sourceDirPath) {
		// TODO Auto-generated method stub
		
	}*/

	public void createDirectory(String dirName){
		 
		 	
		   File dir = new File(dirName);

		   // if the directory does not exist, create it
		   if (!dir.exists())
		   {
			 logger = CommonAppObject.getInstance().getLogger();			   
		     logger.info("creating directory: " + dir);
		     dir.mkdir();
		     
		   }
		   
		 /*  if (!new File(sourceDir+"/"+dirName+"/Archives").exists())
		   {
		     logger.info("creating directory: " +  new File(sourceDir+"/Execution_logs/Archives"));
		     new File(sourceDir+"/"+dirName+"/Archives").mkdir();
		   }*/
		  /* if (!new File(sourceDir+"/TestData/Input").exists())
		   {
		     logger.info("creating directory: " +  new File(sourceDir+"/TestData/Input"));
		     new File(sourceDir+"/TestData/Input").mkdir();
		   }*/
		   
	 }

}
