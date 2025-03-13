package pim.automation.framework;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import common_functions.BaseTest;
import pages.HomePage;
import pages.SearchPage2;

public class TC_001_Marketing extends BaseTest {

	@Test
	public void getVersion() throws IOException, InterruptedException {
		String className = this.getClass().getSimpleName();
		System.out.println(className);
		homePage = new HomePage(driver);
		SearchPage2 searchPage = new SearchPage2(driver);
		utils.waitForElement(homePage.HomePage_SearchButton(), "clickable");
		System.out.println("Home Page is displayed");
		utils.Takescreenshot();
		Thread.sleep(3000);

//	Verify that logged in user is Marketing owner
		WebElement currentloggedinuser = homePage.loggedin_User();
		System.out.println("Logged in user is  " + currentloggedinuser.getText());
		utils.Takescreenshot();

//	Click on Marketing enrichment in my todos
		homePage.enrichMarketingAttributelink().click();
		System.out.println("Clicked on Marketing Enrich link");
		Thread.sleep(3000);
		utils.waitForElement(searchPage.getgrid(), "clickable");
		System.out.println("Search page grid displayed");
		utils.Takescreenshot();
		
//		Click on the filter and search for PIM Taxonomy in the search field
		searchPage.getFilterButton().click();
		System.out.println("Clicked on Filter button");
		Thread.sleep(2000);
		utils.waitForElement(searchPage.Search_MaterialType(), "clickable");
		searchPage.Search_MaterialType().sendKeys("PIM Taxonomy");
		System.out.println("Entered PIM Taxonomy in the search criteria");
		
//		From the list after searching PIM Taxonomy click on the 1st item
//		utils.waitForElement(searchPage.PIMTaxonomy_Firstresult(), "clickable");
		Thread.sleep(5000);
		searchPage.PIMTaxonomy_Firstresult().click();
		System.out.println("Clicked on the first result of PIM taxonomy result");
		
	}
}