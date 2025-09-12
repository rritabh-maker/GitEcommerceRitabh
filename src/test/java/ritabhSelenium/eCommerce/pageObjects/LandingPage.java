package ritabhSelenium.eCommerce.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponent.abstractComponent;

public class LandingPage extends abstractComponent{
	
	WebDriver driver;
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(id="userEmail")
	WebElement email;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(xpath="//div[@id='toast-container']//div")
	WebElement errorLoginMessageElement;
	
	public String getLoginErrorMessage()
	{
		waitForWebElementToAppear(errorLoginMessageElement);
		String errorLoginMessage=errorLoginMessageElement.getText();
		return errorLoginMessage;
	}
	
	public productCatalog loginApplication(String emailId, String pass)
	{
		email.sendKeys(emailId);
		password.sendKeys(pass);
		submit.click();
		return new productCatalog(driver);
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
	}

}
