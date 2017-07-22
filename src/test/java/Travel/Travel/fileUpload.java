package Travel.Travel;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.thoughtworks.selenium.webdriven.commands.Click;

public class fileUpload {
	
	public static void upload(){
		 System.setProperty("webdriver.chrome.driver", "D:\\Selenium Projects\\chromedriver.exe");
		WebDriver driver =new ChromeDriver();
		
		driver.get("https://www.naukri.com/");
		driver.findElement(By.xpath(".//*[@id='login_Layer']/div")).click();
		driver.findElement(By.xpath(".//*[@id='eLogin']")).sendKeys("hmghegde@gmail.com");
		driver.findElement(By.xpath(".//*[@id='pLogin']")).sendKeys("mableshwarg");
		driver.findElement(By.xpath(".//*[@id='lgnFrm']/div[7]/button")).click();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath(".//*[@id='compDetail']/div[4]/div[1]/button")).click();
		driver.findElement(By.xpath(".//*[@id='uploadLink']")).click();
		WebElement we = driver.findElement(By.xpath(".//*[@id='attachCV']"));
		String path="D:\\NewFolder\\Resume.doc";
		we.sendKeys(path);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if(driver.findElement(By.xpath("//div[@class='blueBut1 bdrtpDotGry'] //button")).isDisplayed()){
		System.out.println("Save button is visible");
		
		driver.findElement(By.xpath("//div[@class='blueBut1 bdrtpDotGry'] //button")).click();//(".//*[@id='editForm']/div[7]/button/div/b")).click();
			
		}
		
		else{System.out.println("Save button is not visible");}
			}

	
	public static void main(String args[]){
		
		fileUpload.upload();
	}
}
