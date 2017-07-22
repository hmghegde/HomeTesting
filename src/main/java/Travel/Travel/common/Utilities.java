package Travel.Travel.common;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utilities {

	
	public static void takescreenshot(WebDriver driver,String testName,String name) throws Exception{
	
		 TakesScreenshot ts = (TakesScreenshot)driver;
	        File src=ts.getScreenshotAs(OutputType.FILE);
	        FileUtils.copyFile(src, new File("./Screenshots/"+testName+"/"+name+".png"));
		
	}
}
