package ritabhSelenium.eCommerce.pageObjects;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import AbstractComponent.abstractComponent;

public class checkOutPage extends abstractComponent {
	WebDriver driver;

	public checkOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@FindBy(css = ".totalRow button")
	WebElement totalRow;

	@FindBy(css = "[placeholder='Select Country']")
	WebElement countryElement;

	By section = By.xpath("//section[@class='ta-results list-group ng-star-inserted']");

	@FindBy(xpath = "//section[@class='ta-results list-group ng-star-inserted']//button[2]")
	WebElement dropDown;

	@FindBy(xpath = "//a[contains(text(),'Place Order')]")
	WebElement placeOrder;

	@FindBy(css = ".hero-primary")
	WebElement messageElement;

	public ordersPage validateCheckout(String country) {
		totalRow.click();
		Actions action = new Actions(driver);
		action.sendKeys(countryElement, country).build().perform();
		waitForElementToAppear(section);
		dropDown.click();
		placeOrder.click();

		String confirmMessage = messageElement.getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		return new ordersPage(driver);

	}

}

