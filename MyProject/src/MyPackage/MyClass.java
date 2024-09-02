package MyPackage;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;


@Listeners(MyPackage.MyTestListener.class)

public class MyClass {
	 public WebDriver driver ;
	 public WebDriverWait wait;
	 
	 @DataProvider(name = "authenticationData")
	    public Object[][] authenticationData() {
	        return new Object[][] {
	            { "user1", "pass1" },
	            { "username2", "password2" },
	            { "username3", "password3" },
	            // Add more sets of data as needed
	        };
	    }

		@BeforeMethod
		public void setup() {
			// Set the path of the Driver executable
	        driver = new EdgeDriver(); 
	        //driver.manage().window().maximize();
	        driver.get("https://the-internet.herokuapp.com/");
	        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        
		}
		
		@Test(priority=0, enabled=false)
		public void TC1_homePage() throws InterruptedException {
			
	        System.out.println("Page title is: " + driver.getTitle());
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.titleContains("The Internet"));
	        Thread.sleep(1000);
		}
		
		@Test(priority=1, dataProvider="authenticationData")
		public void TC2_Authentication_KO(String usernameInput, String passwordInput)throws InterruptedException {
			
			WebElement linkelement = driver.findElement(By.linkText("Form Authentication"));
	        linkelement.click();
	       
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
	        WebElement username = driver.findElement(By.id("username")) ;
	        username.sendKeys(usernameInput) ;
	        WebElement password = driver.findElement(By.cssSelector("#password"));
	        password.sendKeys(passwordInput) ;
	        WebElement loginButon = driver.findElement(By.xpath("//*[@id=\"login\"]/button"));
	        loginButon.click();
	        
	     // Wait for the flash message element to be visible and get its text
            WebElement flashMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
            System.out.println("Flash message text: " + flashMessage.getText());
	        
		}
		@Test(priority=2)
       public void TC3_Authentication_OK() {
			
			WebElement linkelement = driver.findElement(By.linkText("Form Authentication"));
	        linkelement.click();

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
	        WebElement username = driver.findElement(By.id("username")) ;
	        username.sendKeys("tomsmith") ;
	        WebElement password = driver.findElement(By.cssSelector("#password"));
	        password.sendKeys("SuperSecretPassword!") ;
	        WebElement loginButon = driver.findElement(By.xpath("//*[@id=\"login\"]/button"));
	        loginButon.click();
	        WebElement logoutLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Logout")));
	        Assert.assertTrue(logoutLink.isDisplayed(), "Login was not successful.");
	        WebElement flashMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
            System.out.println("Flash message text: " + flashMessage.getText());
	        
		}
		
        @Test(priority=3)
        public void TC4_dropdown() throws InterruptedException {
        	WebElement linkelementDrop = driver.findElement(By.cssSelector("#content > ul > li:nth-child(11) > a"));
        linkelementDrop.click();
             WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"dropdown\"]")) ;
             Select select = new Select(dropdown);
             select.selectByValue("2");
             WebElement selectedOption = select.getFirstSelectedOption();
             Assert.assertEquals(selectedOption.getAttribute("value"), "2", "The selected option is not correct.");
         
        }
        
                
        @AfterMethod
        public void tearDown() {
        driver.quit();
        }
	
}

