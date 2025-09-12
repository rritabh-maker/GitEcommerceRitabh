package AbstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ritabhSelenium.eCommerce.pageObjects.cartPage;
import ritabhSelenium.eCommerce.pageObjects.ordersPage;

public class abstractComponent {
	WebDriver driver;
	
	public abstractComponent(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void waitForElementToAppear(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));   //".mb-3"
	}
	public void waitForWebElementToAppear(WebElement findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));   
	}
	public void waitForElementToDisappear(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	@FindBy(xpath="//button[@routerlink ='/dashboard/cart']")
	WebElement cartElement;
	
	public cartPage goToCart()
	{
		cartElement.click();
		cartPage cartPage=new cartPage(driver);
		return cartPage;
	}

	

}
