package common_functions;

import org.testng.annotations.BeforeMethod;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import pages.HomePage;
import pages.Login_Page;

public class BaseTest {
	protected static WebDriver driver;
	protected static Utils utils;
	protected HomePage homePage;

	public void BaseTest() {
		this.driver = driver;
	}

	@BeforeMethod
	@BeforeTest
	public void setUp() throws IOException {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(90));
		driver.manage().window().maximize();
		utils = new Utils(driver);
		Login_Page loginPage = new Login_Page(driver);
		loginPage.LogintoPIM();
		homePage = new HomePage(driver);
	}

	@AfterTest
	public void teardown() {
		driver.quit();
	}
}
