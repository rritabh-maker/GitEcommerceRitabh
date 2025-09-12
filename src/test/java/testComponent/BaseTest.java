package testComponent;

import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import ritabhSelenium.eCommerce.pageObjects.LandingPage;
import ritabhSelenium.eCommerce.pageObjects.ordersPage;

public class BaseTest {
	protected WebDriver driver;
	public LandingPage landingPage;
	public ordersPage ordersPage;

	public WebDriver initializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//test//java//resources//GlobalData.properties");
		prop.load(fis);
		String browsername = prop.getProperty("browser");
		if (browsername.contains("chrome")) {
			ChromeOptions options=new ChromeOptions();
			if (browsername.contains("headless"))
			{
			options.addArguments("headless");
			}
			System.setProperty("webDriver.chrome.driver", "C:/Users/z004nhyz/Documents/chromedriver.exe");
			driver = new ChromeDriver(options);
			//driver.manage().window().setSize(new Dimension(1440,900));
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}

	@BeforeMethod (alwaysRun = true)
	public LandingPage launchApp() throws IOException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}

	public ordersPage launchOrderScreen() throws IOException {
		ordersPage = new ordersPage(driver);
		ordersPage.goToOrder();
		return ordersPage;
	}
	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user.dir") + "//reports//" +testCaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" +testCaseName+".png";
	}
}
