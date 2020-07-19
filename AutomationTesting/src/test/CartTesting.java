package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CartTesting {
	
WebDriver chromeDriver;
	
	
	@BeforeTest
	public void startWebsite() throws InterruptedException
	{	System.out.println("Starting add to cart automation");
		System.setProperty("webdriver.chrome.driver", "D:\\Drivers\\chromeDriver\\chromedriver.exe");
		chromeDriver = new ChromeDriver();
		chromeDriver.get("http://automationpractice.com/index.php");
		Thread.sleep(10000L);
	}
	
	@Test
	public void addToCart() throws InterruptedException {
		
		chromeDriver.findElement(By.xpath("//*[@id=\"homefeatured\"]/li[1]/div/div[2]/div[2]/a[1]")).click();
		Thread.sleep(1000L);
		boolean confirmation = chromeDriver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/h2/span[2]")).getAttribute("innerHTML").contains("There is 1 item in your cart");
		Assert.assertTrue(confirmation);

	}
	
	
	
	
	

	@AfterTest
	public void closeBrowser() throws InterruptedException {
		Thread.sleep(2000L);
		chromeDriver.close();
		System.out.println("Add to cart test has ended");
	}

}
