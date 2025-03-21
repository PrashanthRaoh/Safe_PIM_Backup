package pim.automation.framework;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import common_functions.BaseTest;
import common_functions.Utils;
import pages.HomePage;
import pages.SearchPage2;

public class TC_001_Marketing_Owner extends BaseTest {

	public ExtentTest test;

	@Test
	public void Marketing_Owner() throws IOException, InterruptedException {
		String className = this.getClass().getSimpleName();
		System.out.println(className);

		test = BaseTest.extentreport.createTest(this.getClass().getSimpleName());
		homePage = new HomePage(driver);
		SearchPage2 searchPage = new SearchPage2(driver);

		utils.waitForElement(homePage.HomePage_SearchButton(), "clickable");
		test.pass("Home Page is displayed");
		test.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());
		Thread.sleep(3000);
		/**************************************************
		 ***** Verify that logged in user is Marketing owner****************
		 **************************************************/
		WebElement currentloggedinuser = homePage.loggedin_User();
		System.out.println("Logged in user is  " + currentloggedinuser.getText());
		test.pass("Current user logged in is " + currentloggedinuser.getText());
		test.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());

		/// *************************************************
//		 *    ------- Click on Marketing enrichment link in my todos ---------  *
//		 ************************************************/

		homePage.enrichMarketingAttributelink().click();
		System.out.println("Clicked on Marketing Enrich link");
		Thread.sleep(3000);
		utils.waitForElement(searchPage.getgrid(), "clickable");
		System.out.println("Search page grid displayed");
		test.pass("Search page grid displayed");
		test.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());

		/**************************************************
		 ***** Click on the filter and search for PIM Taxonomy in the search
		 * field****************
		 **************************************************/
		searchPage.getFilterButton().click();
		System.out.println("Clicked on Filter button");
		Thread.sleep(2000);
		utils.waitForElement(searchPage.Search_MaterialType(), "clickable");
		searchPage.Search_MaterialType().sendKeys("PIM Taxonomy");
		System.out.println("Entered PIM Taxonomy in the search criteria");
		test.pass("Entered PIM Taxonomy in the search criteria");
		test.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());
//		
		/**************************************************
		 ***** Select PIM taxonomy Filter****************
		 **************************************************/
		Thread.sleep(5000);
		searchPage.PIMTaxonomy_Firstresult().click();
		System.out.println("Clicked on the PIM taxonomy Filter");
		utils.waitForElement(searchPage.Classification_Search_inputbox(), "clickable");

		/**************************************************
		 ***** Waiting for Classification window ****************
		 **************************************************/
		System.out.println("Classification window appeared");
		test.pass("Classification window appeared");
		test.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());

		/**************************************************
		 ***** Entered wide inner rings further filtration***
		 **************************************************/
		searchPage.Classification_Search_inputbox().sendKeys("wide inner rings");
		searchPage.Classification_Search_inputbox().sendKeys(Keys.ENTER);
		Thread.sleep(2000);

		/**************************************************
		 ***** Checkbox wide inner rings checked************
		 **************************************************/
		utils.waitForElement(searchPage.widerangeCheckbox(), "clickable");
		searchPage.widerangeCheckbox().click();
		System.out.println("Clicked on wide range checkbox");
		Thread.sleep(3000);

		/*****************************************************************************************************
		 ***** Apply button on the filtration window clicked and Waiting for the search
		 * results to display**
		 *****************************************************************************************************/
		searchPage.classficationwindow_Applybtn().click();
		System.out.println("Apply button clicked");
		Thread.sleep(3000);
		utils.waitForElement(searchPage.getgrid(), "clickable");
		System.out.println("Search page grid displayed after entering the wide range filter criteria");
//		
		/*
		 * // /************************************************ 
		 * --------- Get Row count------- *
		 ************************************************/
		Actions actions = new Actions(driver);
		List<WebElement> rows = searchPage.getgrid().findElements(By.cssSelector("[role='row']"));
		System.out.println("Total rows after entering the wide range filter criteria is " + rows.size());
		test.pass("Classification window appeared");
		test.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());

		WebElement RowByRow = rows.get(0);
		String SellableMaterialDescription = RowByRow
				.findElement(By.cssSelector("div[col-id='sellablematerialdescription']")).getText();
		String matid = RowByRow.findElement(By.cssSelector("div[col-id='sellablematerialid']")).getText();
	
		System.out.println("Material ID -- " + matid + " Material Description --" + SellableMaterialDescription);
		/*
		 * // /************************************************ 
		 * --------- Click on the materialid from the result------- *
		 ************************************************/
		WebElement matidElement = RowByRow.findElement(By.cssSelector("div[col-id='sellablematerialid']"));
		actions.moveToElement(RowByRow).build().perform();
		Thread.sleep(2000);
		matidElement.click();
		Thread.sleep(3000);
		test.pass("Material ID -- " + matid + " Material Description --" + SellableMaterialDescription
				+ " is selected for completion");
		test.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());
		
		
		/************************************************* 
		 * --------- Capture % completion------- *
		 ************************************************/
		String percentagecompletion = searchPage.ProgressRing().getText();
		System.out.println("percentagecompletion is " + percentagecompletion);
		List<WebElement> bussrule = searchPage.BusinessRule().getShadowRoot()
				.findElements(By.cssSelector("#accordion\\ 0 > div > div > div"));
		System.out.println("There are" + bussrule.size());
	}
}