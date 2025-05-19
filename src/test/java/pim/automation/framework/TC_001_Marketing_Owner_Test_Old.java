//package pim.automation.framework;
//
//import static org.junit.Assert.assertTrue;
//import static org.testng.Assert.assertEquals;
//
//import java.io.IOException;
//import java.time.Duration;
//import java.util.List;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.MediaEntityBuilder;
//import com.aventstack.extentreports.Status;
//
//import common_functions.BaseTest;
//import common_functions.Utils;
//import pages.HomePage;
//import pages.SearchPage2;
//
//public class TC_001_Marketing_Owner_Test extends BaseTest {
//
//	public ExtentTest test;
//
//	@Test
//	public void Marketing_Owner() throws IOException, InterruptedException {
//		String className = this.getClass().getSimpleName();
//		System.out.println(className);
//
//		test = BaseTest.extentreport.createTest(this.getClass().getSimpleName());
//		homePage = new HomePage(driver);
//		SearchPage2 searchPage = new SearchPage2(driver);
//
//		utils.waitForElement(homePage.HomePage_SearchButton(), "clickable");
//		test.pass("Home Page is displayed");
//		test.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());
//		Thread.sleep(3000);
//		/**************************************************
//		 ***** Verify that logged in user is Marketing owner****************
//		 **************************************************/
//		WebElement currentloggedinuser = homePage.loggedin_User();
//		System.out.println("Logged in user is  " + currentloggedinuser.getText());
//		test.pass("Current user logged in is " + currentloggedinuser.getText());
//		test.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());
//		assertTrue("Logged-in user should be Marketing owner",currentloggedinuser.getText().contains("attributeownermarketing.test1"));
//
//		/// *************************************************
////		 *    ------- Click on Marketing enrichment link in my todos ---------  *
////		 ************************************************/
//		homePage.enrichMarketingAttributelink().click();
//		System.out.println("Clicked on Marketing Enrich link");
//		Thread.sleep(3000);
//		utils.waitForElement(searchPage.getgrid(), "clickable");
//		System.out.println("Search page grid displayed");
//		test.pass("Search page grid displayed");
//		test.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());
//
//		/**************************************************
//		 ***** Click on the filter and search for PIM Taxonomy in the search
//		 * field****************
//		 **************************************************/
//		searchPage.getFilterButton().click();
//		System.out.println("Clicked on Filter button");
//		Thread.sleep(2000);
//		utils.waitForElement(searchPage.Search_MaterialType(), "clickable");
//		searchPage.Search_MaterialType().sendKeys("PIM Taxonomy");
//		System.out.println("Entered PIM Taxonomy in the search criteria");
//		test.pass("Entered PIM Taxonomy in the search criteria");
//		test.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());
//
//		/**************************************************
//		 ***** Select PIM taxonomy Filter****************
//		 **************************************************/
//		Thread.sleep(5000);
//		searchPage.PIMTaxonomy_Firstresult().click();
//		System.out.println("Clicked on the PIM taxonomy Filter");
//		utils.waitForElement(searchPage.Classification_Search_inputbox(), "clickable");
//
//		/**************************************************
//		 ***** Waiting for Classification window ****************
//		 **************************************************/
//		Thread.sleep(2000);
//		System.out.println("Classification window appeared");
//		test.pass("Classification window appeared");
//		test.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());
//
//		/**************************************************
//		 ***** Entered wide inner rings further filtration***
//		 **************************************************/
//		searchPage.Classification_Search_inputbox().sendKeys("wide inner rings");
//		searchPage.Classification_Search_inputbox().sendKeys(Keys.ENTER);
//		Thread.sleep(2000);
//
//		/**************************************************
//		 ***** Checkbox wide inner rings checked************
//		 **************************************************/
//		utils.waitForElement(searchPage.widerangeCheckbox(), "clickable");
//		searchPage.widerangeCheckbox().click();
//		System.out.println("Clicked on wide range checkbox");
//		Thread.sleep(3000);
//		/*****************************************************************************************************
//		 ***** Apply button on the filtration window clicked and Waiting for the search
//		 * results to display**
//		 *****************************************************************************************************/
//		searchPage.classficationwindow_Applybtn().click();
//		System.out.println("Apply button clicked");
//		Thread.sleep(3000);
//		utils.waitForElement(searchPage.getgrid(), "clickable");
//		System.out.println("Search page grid displayed after entering the wide range filter criteria");
//		Thread.sleep(5000);
//
//		/*****************************************************************************************************
//		 ***** Enter 00000 in the search field**
//		 *****************************************************************************************************/
////		searchPage.searchthingdomain_Input_Mat_Id().click();
////		searchPage.searchthingdomain_Input_Mat_Id().clear();
////		searchPage.searchthingdomain_Input_Mat_Id().sendKeys("000000");
////		searchPage.searchthingdomain_Input_Mat_Id().sendKeys(Keys.ENTER);
//		searchPage.getFilterButton().click();
//		System.out.println("Clicked on Filter button");
//		Thread.sleep(2000);
//		searchPage.ATO_Marketing_Enrichment_Buss_Condition_Filter().click();
//		Thread.sleep(2000);
//		searchPage.Failed_Buss_Condition().click();
//		Thread.sleep(5000);
//		utils.waitForElement(searchPage.getgrid(), "clickable");
//		
//		/**
//		 * // /************************************************
//		 *  --------- Get Row count------- *
//		 ********************************************************/
//		Actions actions = new Actions(driver);
//		WebElement rowsredefined = driver.findElement(By.cssSelector("#app")).getShadowRoot()
//				.findElement(By.cssSelector("#contentViewManager")).getShadowRoot()
//				.findElement(By.cssSelector("[id^='currentApp_search-thing_']")).getShadowRoot()
//				.findElement(By.cssSelector("[id^='app-entity-discovery-component-']")).getShadowRoot()
//				.findElement(By.cssSelector("#entitySearchDiscoveryGrid")).getShadowRoot()
//				.findElement(By.cssSelector("#entitySearchGrid")).getShadowRoot()
//				.findElement(By.cssSelector("#entityGrid")).getShadowRoot()
//				.findElement(By.cssSelector("#pebbleGridContainer > pebble-grid")).getShadowRoot()
//				.findElement(By.cssSelector("#grid"));
//
//		List<WebElement> arrrowsdefined = rowsredefined.getShadowRoot().findElements(By.cssSelector(
//				"#lit-grid > div > div.ag-root-wrapper-body.ag-layout-normal.ag-focus-managed > div.ag-root.ag-unselectable.ag-layout-normal > div.ag-body-viewport.ag-layout-normal.ag-row-no-animation > div.ag-center-cols-clipper > div > div> div.ag-row.ag-row-even.ag-row-level-0"));
//
//		System.out.println("Total rows after entering the wide range filter criteria and 000s is " + arrrowsdefined.size());
//		test.pass("Rows after entering 0s appeared");
//		test.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());
//		assertTrue("There should be results after applying filters", arrrowsdefined.size() > 0);
//
//		WebElement RowByRow = arrrowsdefined.get(0);
//		String SellableMaterialDescription = RowByRow.findElement(By.cssSelector("div[col-id='sellablematerialdescription']")).getText();
//		String matid = RowByRow.findElement(By.cssSelector("div[col-id='sellablematerialid']")).getText();
//		System.out.println("Material ID -- " + matid + " Material Description --" + SellableMaterialDescription);
//		
////		/*
////		 * // /************************************************ 
////		 * --------- Click on the materialid from the result------- *
////		 ************************************************/
//		WebElement matidElement = RowByRow.findElement(By.cssSelector("div[col-id='sellablematerialid']"));
//		actions.moveToElement(RowByRow).build().perform();
//		Thread.sleep(2000);
//		matidElement.click();
//		Thread.sleep(3000);
//		test.pass("Material ID -- " + matid + " Material Description --" + SellableMaterialDescription
//				+ " is selected for completion");
//		test.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());
//		
//		/*************************************************
//		 * --------- Capture % completion------- *
//		 ************************************************/
//		utils.waitForElement(searchPage.ProgressRing(), "visible");
//		String percentagecompletion = searchPage.ProgressRing().getText();
//		System.out.println("percentagecompletion is " + percentagecompletion);
//		Assert.assertEquals(percentagecompletion.trim(), "0", "Percentagecompletion should be 0");
//
//		List<WebElement> bussrule = searchPage.BusinessRule().getShadowRoot()
//				.findElements(By.cssSelector("#accordion\\ 0 > div > div > div"));
//		System.out.println("There are  " + bussrule.size() + " bussrule");
//		Assert.assertEquals(bussrule.size(), 1, "The size of bussrule should be 1");
//		
//		/*************************************************
//		 * --------- Click on the business rule------- *
//		 ************************************************/
//		bussrule.getFirst().click();
//		Thread.sleep(4000);
//		utils.waitForElement(searchPage.shortDescription(), "clickable");
//		test.pass("Summary page of the material displayed");
//		test.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());
//		
//		/*************************************************
//		 * --------- Enter mandatory fields to complete the transaction------- *
//		 ************************************************/
//		searchPage.shortDescription().sendKeys("Short description to complete transaction");
//		Thread.sleep(1000);
//		searchPage.LongDescription().sendKeys("This is long description");
//		Thread.sleep(1000);
//		searchPage.SaveTransaction_btn().click();
//	}
//}