package MyPackage;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;




@Listeners(MyPackage.MyTestListener.class)

public class MyClass2 {

	 public WebDriver driver ;
	 public WebDriverWait wait;
	
	 @BeforeMethod
		public void setup() {
			// Set the path of the Driver executable
	        driver = new EdgeDriver();
	        driver.manage().window().maximize();
	        driver.get("https://the-internet.herokuapp.com/");
	        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        
		}
	 
	 
	 @Test(priority=1)
	       public void TC1_hovers() throws InterruptedException {

	       	WebElement linkelementHover = driver.findElement(By.linkText("Hovers"));
	       	linkelementHover.click();
	           Thread.sleep(2000);         

	           WebElement hover3 = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[3]/img"));
	           Actions actions = new Actions(driver);
	           actions.moveToElement(hover3).perform();
	           WebElement captionuser3 = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[3]/div/h5"));
	           WebElement captionlink3 = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[3]/div/a"));
	           
	           // Assertions to verify visibility
	           Assert.assertTrue(captionuser3.isDisplayed(), "Caption name is not visible for user 3.");
	           Assert.assertTrue(captionlink3.isDisplayed(), "Caption link is not visible for user 3.");
	      
	           Thread.sleep(2000);
	    	   
	       }   
	 
	 
	 @AfterMethod
     public void tearDown() {
     driver.quit();
     }
	 
	 
		

}
