package Travel.Travel;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class switchWindows {
	
	
	public void changeWindows() throws Exception{
		//System.setProperty("webdriver.chrome.driver", "D:\\Selenium Projects\\chromedriver.exe");
	WebDriver driver=new FirefoxDriver();
	
	driver.get("https://accounts.google.com/SignUp?service=mail&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F%3Ftab%3Dwm&hl=en");
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    
    String parent_window=driver.getWindowHandle();
    System.out.println("Before Switching-"+driver.getTitle());
	
	driver.findElement(By.xpath("//*[@href='https://support.google.com/accounts/answer/1733224?hl=en']")).click();
		 
	Set<String> windows= driver.getWindowHandles();
	
	Iterator <String> iterator= windows.iterator();
	
	while(iterator.hasNext()){
		String child_window=iterator.next();
		if(!parent_window.equalsIgnoreCase(child_window)){
			driver.switchTo().window(child_window);
		
			Thread.sleep(1000);
			System.out.println("After_Switching-"+driver.getTitle());
		}	
	}
	
	}	
	
	

	 public static void main (String args[]) throws Exception{
	    	
		 switchWindows br = new switchWindows();
	    	br.changeWindows();
	    }
}
