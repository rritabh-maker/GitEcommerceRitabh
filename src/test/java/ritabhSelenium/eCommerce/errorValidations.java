package ritabhSelenium.eCommerce;
import java.io.IOException;
import testComponent.BaseTest;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.sun.net.httpserver.Authenticator.Retry;

public class errorValidations extends BaseTest 
{
	@Test (groups= {"errorHandling"})
	public void validateErrors() throws IOException {
		landingPage.loginApplication("ritabh@gmail.com", "Confirm@123");
		Assert.assertEquals("Incorrect email or password.", landingPage.getLoginErrorMessage());
		
		
	}
}
