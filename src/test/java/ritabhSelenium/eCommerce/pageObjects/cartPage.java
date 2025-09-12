package ritabhSelenium.eCommerce.pageObjects;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import AbstractComponent.abstractComponent;

public class cartPage extends abstractComponent {
	WebDriver driver;

	public cartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@FindBy(xpath = "//div[@class='cart']//h3")
	List<WebElement> cartItems;

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

	public checkOutPage validateCartItems(String[] product, String[] productAmount) {
		goToCart();
		for (int i = 0; i < cartItems.size(); i++) {
			String productName = cartItems.get(i).getText();
			String amount = driver
					.findElement(By.xpath("//h3[contains(text(),'" + product[i] + "')]//following-sibling::p"))
					.getText();
			Boolean bool = productName.equals(product[i]) && amount.trim().equals(productAmount[i].trim());
			assertEquals(true, bool);
		}
		return new checkOutPage(driver);
	}
	

}
