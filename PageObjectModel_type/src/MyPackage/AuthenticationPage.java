package MyPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page object model class for the Authentication Page of the application.
 */
public class AuthenticationPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By usernameField = By.id("username");
    private By passwordField = By.cssSelector("#password");
    private By loginButton = By.xpath("//*[@id='login']/button");
    private By flashMessage = By.id("flash");
    private By logoutLink = By.linkText("Logout");

    /**
     * Constructor for AuthenticationPage class.
     *
     * @param driver The WebDriver instance used to interact with the browser.
     */
    public AuthenticationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Enters the username in the username field.
     *
     * @param username The username to be entered.
     */
    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    /**
     * Enters the password in the password field.
     *
     * @param password The password to be entered.
     */
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    /**
     * Clicks the login button.
     */
    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    /**
     * Retrieves the flash message text.
     *
     * @return The text of the flash message.
     */
    public String getFlashMessage() {
        WebElement flash = wait.until(ExpectedConditions.visibilityOfElementLocated(flashMessage));
        return flash.getText();
    }

    /**
     * Checks if the logout link is displayed.
     *
     * @return True if the logout link is displayed, otherwise false.
     */
    public boolean isLogoutLinkDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(logoutLink)).isDisplayed();
    }

	public boolean isFlashMessageDisplayed() {
		// TODO Auto-generated method stub
		return wait.until(ExpectedConditions.visibilityOfElementLocated(flashMessage)).isDisplayed();
    }
}
