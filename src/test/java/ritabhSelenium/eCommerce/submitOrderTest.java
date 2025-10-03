package ritabhSelenium.eCommerce;

import java.io.IOException;
import java.util.HashMap;

import testComponent.BaseTest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ritabhSelenium.eCommerce.pageObjects.cartPage;
import ritabhSelenium.eCommerce.pageObjects.checkOutPage;
import ritabhSelenium.eCommerce.pageObjects.ordersPage;
import ritabhSelenium.eCommerce.pageObjects.productCatalog;

public class submitOrderTest extends BaseTest {

	@Test(dataProvider = "getData")
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		try {
			productCatalog productCatalog = landingPage.loginApplication(input.get("email"), input.get("password"));
			cartPage cartPage = productCatalog.addProducts(
					new String[] { input.get("productName1"), input.get("productName2") },
					new String[] { input.get("amount"), input.get("amount") });
			checkOutPage checkOutPage = cartPage.validateCartItems(
					new String[] { input.get("productName1"), input.get("productName2") },
					new String[] { input.get("amount"), input.get("amount") });
			checkOutPage.validateCheckout(input.get("country"));
		} finally {

		}

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void validateOrders() throws IOException {
		ordersPage ordersPage = launchOrderScreen();
		ordersPage.validatOrdersList(new String[] { "ADIDAS ORIGINAL", "ZARA COAT 3" },
				new String[] { " MRP $ 11500", " MRP $ 11500" });
	}

	@DataProvider
	public Object[][] getData() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "ritabh@gmail.com");
		map.put("password", "Confirm@12345");
		map.put("productName1", "ZARA COAT 3");
		map.put("productName2", "ADIDAS ORIGINAL");
		map.put("amount", " MRP $ 11500");
		map.put("country", "India");

		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("email", "anshika@gmail.com");
		map1.put("password", "Iamking@000");
		map1.put("productName1", "ZARA COAT 3");
		map1.put("productName2", "ADIDAS ORIGINAL");
		map1.put("amount", " MRP $ 11500");
		map1.put("country", "India");

		return new Object[][] { { map }, { map1 } };
	}
}
