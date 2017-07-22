package Travel.Travel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;

import Travel.Travel.common.CommonAppObject;
import Travel.Travel.common.Utilities;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Browse {

	//Logger logger = CommonAppObject.getInstance().getLogger();

	//@Test
    public void homepage() throws IOException, Exception {
    	Properties property = null;
    	String testName="Login";
        System.setProperty("webdriver.chrome.driver", "D:\\Selenium Projects\\chromedriver.exe");
        property = CommonAppObject.getInstance().getProperty();
        // WebDriver driver = new FirefoxDriver(); 
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String URL=property.getProperty("Main_Url");
         driver.get(URL);
         Utilities.takescreenshot(driver, testName, "Get URL");
                  
        String Myaccount = property.getProperty("xpath_MyAccount");
        String Signin = property.getProperty("xpath_signin");      
        
        WebElement expr= driver.findElement(By.xpath(Myaccount));        
       	      	
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Actions builder= new Actions(driver);
        builder.moveToElement(expr).build().perform();
        Thread.sleep(10000);
        
        Utilities.takescreenshot(driver, testName, "Move_hover_MyAccount");
        
        WebElement expr1= driver.findElement(By.xpath(Signin));
        expr1.click();
        
        Utilities.takescreenshot(driver, testName, "Click on LOGIN");
        
        driver.findElement(By.xpath(property.getProperty("xpath_email"))).sendKeys(property.getProperty("email_id"));
        driver.findElement(By.xpath(property.getProperty("xpath_password"))).sendKeys(property.getProperty("password"));
        
        Utilities.takescreenshot(driver, testName, "EnteredUsername_Password");
        
        driver.findElement(By.xpath(property.getProperty("xpath_signin"))).click();
        
        Utilities.takescreenshot(driver, testName, "LOGIN SUCCESS");
         //driver.close();
    }
    
    public static void main (String args[]) throws Exception{
    	
    	Browse br = new Browse();
    	br.homepage();
    }



}