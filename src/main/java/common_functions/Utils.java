package common_functions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils extends BaseTest {
//	private WebDriver driver;
//	private WebDriverWait wait;

	static WebDriver driver;
	WebDriverWait wait;

	public Utils(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
	}

//	 Generic wait function
	public WebElement waitForElement(WebElement element, String conditionType) {
		switch (conditionType) {
		case "clickable":
			return wait.until(ExpectedConditions.elementToBeClickable(element));
		case "visible":
			return wait.until(ExpectedConditions.visibilityOf(element));
		case "invisibility":
			wait.until(ExpectedConditions.invisibilityOf(element));
			return null;
		default:
			throw new IllegalArgumentException("Invalid wait condition type: " + conditionType);
		}
	}

	public static String Takescreenshot(WebDriver driver) throws IOException {
		Date today = new Date();
		SimpleDateFormat SIMPDFORMAT = new SimpleDateFormat("ddMMYY_HHmmss");
		String date = SIMPDFORMAT.format(today);

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File Destinationfilepath = new File("./Screenshots/ " + date + ".png");
		FileUtils.copyFile(src, Destinationfilepath);
		String destinationpath = Destinationfilepath.getAbsolutePath();
		return destinationpath;
	}

	public String getClassName(Object obj) {
//		return new Exception().getStackTrace()[1].getClassName().getClass().getName();
		return obj.getClass().getSimpleName();
	}
}