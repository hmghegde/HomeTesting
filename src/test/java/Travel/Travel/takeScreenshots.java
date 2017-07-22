package Travel.Travel;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class takeScreenshots {

	public static void screenshots() throws Exception{
		
		WebDriver driver =new FirefoxDriver();
		
		driver.get("http://www.facebook.com");
		
		TakesScreenshot sc= (TakesScreenshot)driver;
		
		File source= sc.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source, new File("./Screenshots/facebook.png"));
		System.out.println("Screeshots taken");
	}
	
	public static void main(String args[]) throws Exception{
		takeScreenshots.screenshots();
	}
	
}
