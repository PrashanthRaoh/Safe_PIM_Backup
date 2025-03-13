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

public class Utils {
	private WebDriver driver;
	private WebDriverWait wait;

	public Utils(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(150));
	}

//	 Generic wait function
	public WebElement waitForElement(WebElement element, String conditionType) {
		switch (conditionType) 
		{
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
	
	public void Takescreenshot() throws IOException {
		Date today = new Date();
		SimpleDateFormat SIMPDFORMAT = new SimpleDateFormat("ddMMYY_HHmmss");
		String date =  SIMPDFORMAT.format(today);
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("./Screenshots/ " + date + ".png"));
	}
	
	public String getClassName(Object obj) {
//		return new Exception().getStackTrace()[1].getClassName().getClass().getName();
		return obj.getClass().getSimpleName();
	}
}