package MyPackage;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Listeners(MyPackage.MyTestListener.class)
public class MyTestClass {
	private WebDriver driver;
	private HomePage homePage;
	private AuthenticationPage authPage;
	private DropdownPage dropDownPage;
	
	private static final Logger logger = Logger.getLogger(MyTestClass.class.getName());
	private static final String BASE_URL = "https://the-internet.herokuapp.com/";
	
	/*
	@DataProvider(name = "authenticationData")
    public Object[][] authenticationData() {
        return new Object[][] {
            { "username1", "password1" },
            { "username2", "password2" },
        };
    }
	*/
	
	//Data from Excel
	 @DataProvider(name = "loginData")
	    public Object[][] getData() throws IOException, EncryptedDocumentException, InvalidFormatException {
	        return ExcelReader.readExcelData(".\testdata.xlsx", "Sheet1");
	    }
	
	 
	@BeforeMethod
    public void setup() {
        driver = new EdgeDriver();
       // driver.manage().window().maximize();
        driver.get(BASE_URL);
        
        homePage = new HomePage(driver);
        authPage = new AuthenticationPage(driver);
        dropDownPage = new DropdownPage(driver);
	}
	@AfterMethod
    public void tearDown() {
        driver.quit();
    }
	
	@Test(priority=0, enabled=false)
    public void TC1_homePage() {
		String expectedTitle = "The Internet";
        String actualTitle = homePage.getPageTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Home page title does not match.");
        logger.log(Level.INFO, "Home page title verified as: {0} ", actualTitle);
    }
	
	
	 @Test(priority=1, dataProvider="loginData")
	    public void TC2_Authentication_KO(String usernameInput, String passwordInput) {
		 homePage.goToFormAuthentication();
	        authPage.enterUsername(usernameInput);
	        authPage.enterPassword(passwordInput);
	        authPage.clickLogin();
	        String flashMessage = authPage.getFlashMessage();
	        logger.log(Level.INFO, "Flash message after login attempt: {0}", flashMessage);
	        Assert.assertTrue(authPage.isFlashMessageDisplayed(), "Flash message is not displayed.");
	    }
	
	@Test(priority=2)
    public void TC3_Authentication_OK() {
		homePage.goToFormAuthentication();
        authPage.enterUsername("tomsmith");
        authPage.enterPassword("SuperSecretPassword!");
        authPage.clickLogin();
        Assert.assertTrue(authPage.isLogoutLinkDisplayed(), "Logout link is not displayed.");
        String flashMessage = authPage.getFlashMessage();
        logger.log(Level.INFO, "Flash message after successful login: {0}", flashMessage);
    }
	 
	 @Test(priority=3)
	    public void TC4_dropdown() {
	        homePage.goToDropdown();
	        dropDownPage.selectOptionByValue("2");
	        dropDownPage.assertSelectedOption("2");
	    }
	

}
