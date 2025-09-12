package ritabhSelenium.eCommerce.pageObjects;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import AbstractComponent.abstractComponent;

public class productCatalog extends abstractComponent {
	WebDriver driver;

	public productCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// List<WebElement> list = driver.findElements(By.cssSelector("h5"));

	@FindBy(css = "h5")
	List<WebElement> list;

	By toastMessage = By.cssSelector("#toast-container");

	@FindBy(css = ".ng-animating")
	WebElement animated;

	public List<WebElement> getProductList() {
		return list;
	}

	public cartPage addProducts(String[] product, String[] productAmount) throws InterruptedException {
		for (int i = 0; i < list.size(); i++) {
			String productnames = list.get(i).getText();

			if (productnames.equals(product[i])) {
				driver.findElement(By
						.xpath("//b[contains(text(),'" + product[i] + "')]//parent::h5//following-sibling::button[2]"))
						.click();
//				waitForElementToAppear(toastMessage);
//				waitForElementToDisappear(animated);
				Thread.sleep(5000);
				

				if (product[i].equals("ADIDAS ORIGINAL"))
					break;
			}

		}
		return new cartPage(driver);
	}
	

}
