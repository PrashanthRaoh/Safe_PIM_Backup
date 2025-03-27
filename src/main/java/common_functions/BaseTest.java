package common_functions;

import org.testng.annotations.BeforeMethod;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import pages.HomePage;
import pages.Login_Page;

public class BaseTest {
	public static WebDriver driver;
	public static Utils utils;
	public HomePage homePage;
	public static ExtentReports extentreport;
	public static ExtentTest extentTest;
	public static final String filepathname = "target/PIM_Report.html";

	public void BaseTest() {
		this.driver = driver;
	}

	@BeforeTest
	public void setUp() throws IOException {
		extentreport = new ExtentReports();
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(filepathname);
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setDocumentTitle("PIM Automation Report");
		extentreport.attachReporter(sparkReporter);

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(150));
		driver.manage().window().maximize();
		utils = new Utils(driver);
		Login_Page loginPage = new Login_Page(driver);
		loginPage.LogintoPIM();
		homePage = new HomePage(driver);
	}

	@AfterTest
	public void teardown() throws IOException {
		extentreport.flush();
		Desktop.getDesktop().browse(new File(filepathname).toURI());
	}
}
