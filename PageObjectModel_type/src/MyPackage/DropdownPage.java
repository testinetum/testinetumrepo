package MyPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

/**
 * Page object model class for the Dropdown Page of the application.
 */
public class DropdownPage {
    private WebDriver driver;

    // Locator for the dropdown element
    private By dropdown = By.xpath("//*[@id='dropdown']");

    /**
     * Constructor for DropdownPage class.
     *
     * @param driver The WebDriver instance used to interact with the browser.
     */
    public DropdownPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Selects an option in the dropdown by its value.
     *
     * @param value The value of the option to be selected.
     */
    public void selectOptionByValue(String value) {
        WebElement dropdownElement = driver.findElement(dropdown);
        Select select = new Select(dropdownElement);
        select.selectByValue(value);
    }

    /**
     * Asserts that the selected option in the dropdown matches the expected value.
     *
     * @param expectedValue The expected value of the selected option.
     */
    public void assertSelectedOption(String expectedValue) {
        WebElement dropdownElement = driver.findElement(dropdown);
        Select select = new Select(dropdownElement);
        WebElement selectedOption = select.getFirstSelectedOption();
        Assert.assertEquals(selectedOption.getAttribute("value"), expectedValue, "The selected option is not correct.");
    }
}
