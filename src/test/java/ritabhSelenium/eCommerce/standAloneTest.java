package ritabhSelenium.eCommerce;

import ritabhSelenium.eCommerce.pageObjects.*;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class standAloneTest {
	public static void main(String[] args) {

		System.setProperty("webDriver.chrome.driver", "C:/Users/z004nhyz/Documents/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		LandingPage landingPage = new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("ritabh@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Confirm@12345");
		driver.findElement(By.id("login")).click();

		// data to be verified
		String[] product = { "ZARA COAT 3", "ADIDAS ORIGINAL" };
		String[] productAmount = { " MRP $ 31500", " MRP $ 31500" };

		// add products to cart
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		List<WebElement> list = driver.findElements(By.cssSelector("h5"));
		for (int i = 0; i < list.size(); i++) {
			String productnames = list.get(i).getText();

			if (productnames.equals(product[i])) {
				driver.findElement(By
						.xpath("//b[contains(text(),'" + product[i] + "')]//parent::h5//following-sibling::button[2]"))
						.click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
				wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
				if (product[i].equals("ADIDAS ORIGINAL"))
					break;
			}

		}

		// validate carts item
		driver.findElement(By.xpath("//button[@routerlink ='/dashboard/cart']")).click();
		List<WebElement> cartItems = driver.findElements(By.xpath("//div[@class='cart']//h3"));
		for (int i = 0; i < cartItems.size(); i++) {
			String productName = cartItems.get(i).getText();
			String amount = driver
					.findElement(By.xpath("//h3[contains(text(),'" + product[i] + "')]//following-sibling::p"))
					.getText();
			Boolean bool = productName.equals(product[i]) && amount.trim().equals(productAmount[i].trim());
			assertEquals(true, bool);
		}
		driver.findElement(By.cssSelector(".totalRow button")).click();
		Actions action = new Actions(driver);
		action.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build()
				.perform();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//section[@class='ta-results list-group ng-star-inserted']")));
		driver.findElement(By.xpath("//section[@class='ta-results list-group ng-star-inserted']//button[2]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Place Order')]")).click();

		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();

	}
}
