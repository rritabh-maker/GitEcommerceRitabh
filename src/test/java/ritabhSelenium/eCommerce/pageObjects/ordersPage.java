package ritabhSelenium.eCommerce.pageObjects;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import AbstractComponent.abstractComponent;
public class ordersPage extends abstractComponent {
	WebDriver driver;
	public ordersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	@FindBy(xpath = "//td[2]")
	List<WebElement> orderItems;
	
	@FindBy(xpath = "//td[3]")
	List<WebElement> orderPrices;
	
	
	@FindBy(xpath = "//button[@routerlink=\"/dashboard/myorders\"]")
	WebElement orderElement;

	public void goToOrder() 
	{
		orderElement.click();
	}
  public ordersPage validatOrdersList(String[] product, String[] productAmount )
  {
	  for(int i=0;i<orderItems.size();i++)
	  {
		  String orderNames=orderItems.get(i).getText();
		  Boolean bool=orderNames.contains(product[i]);
		  assertEquals(true, bool);
	  }
	 
	  return new ordersPage(driver);
  }

}
