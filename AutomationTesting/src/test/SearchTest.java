package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SearchTest {
	WebDriver chromeDriver;
	
	
	@BeforeTest
	public void startWebsite() throws InterruptedException
	{	System.out.println("Starting search automation");
		System.setProperty("webdriver.chrome.driver", "D:\\Drivers\\chromeDriver\\chromedriver.exe");
		chromeDriver = new ChromeDriver();
		chromeDriver.get("http://automationpractice.com/index.php");
	}
	
	@Test
	public void searchProduct() {
		chromeDriver.findElement(By.id("search_query_top")).sendKeys("Dress");
		chromeDriver.findElement(By.name("submit_search")).click();
		String expectedURL = "http://automationpractice.com/index.php?controller=search&orderby=position&orderway=desc&search_query=Dress&submit_search=";
		String actualURL = chromeDriver.getCurrentUrl();
		Assert.assertEquals(expectedURL, actualURL);
		
	}
	
	@AfterTest
	public void closeBrowser() throws InterruptedException {
		Thread.sleep(2000L);
		chromeDriver.close();
		System.out.println("Search test has ended");
	}
}
