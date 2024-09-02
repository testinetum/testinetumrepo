package MyPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
	
	    private WebDriver driver;

	    // Locator for the "Form Authentication" link
	    private By formAuthenticationLink = By.linkText("Form Authentication");
	    private By dropdownLink = By.cssSelector("#content > ul > li:nth-child(11) > a");

	    /**
	     * Constructor for HomePage class.
	     *
	     * @param driver The WebDriver instance used to interact with the browser.
	     */
	    public HomePage(WebDriver driver) {
	        this.driver = driver;
	    }

	    /**
	     * Clicks on the "Form Authentication" link.
	     */
	    public void goToFormAuthentication() {
	        driver.findElement(formAuthenticationLink).click();
	    }

	    /**
	     * Clicks on the dropdown link.
	     */
	    public void goToDropdown() {
	        driver.findElement(dropdownLink).click();
	    }

	    /**
	     * Gets the title of the Home Page.
	     *
	     * @return The title of the page.
	     */
	    public String getPageTitle() {
	        return driver.getTitle();
	    }
	}



