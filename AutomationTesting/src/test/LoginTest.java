package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTest {
	WebDriver chromeDriver;
	protected String email="y59d5h0k@testMail.com";
	protected String password = "12345";
	
	@BeforeTest
	public void redirectToLogin() throws InterruptedException
	{	System.out.println("Starting login test");
		System.setProperty("webdriver.chrome.driver", "D:\\Drivers\\chromeDriver\\chromedriver.exe");
		chromeDriver = new ChromeDriver();
		chromeDriver.get("http://automationpractice.com/index.php");
		chromeDriver.findElement(By.className("login")).click();
		Thread.sleep(5000L);
	}
	
	@Test
	public void invalidLogin() {
		
		chromeDriver.findElement(By.id("email")).sendKeys("abc1234@Testmail.com");
		chromeDriver.findElement(By.id("passwd")).sendKeys(password);
		
		chromeDriver.findElement(By.id("SubmitLogin")).click();
		String expectedURL = "http://automationpractice.com/index.php?controller=authentication";
		String actualUrl = chromeDriver.getCurrentUrl();
		Assert.assertEquals(actualUrl,expectedURL);
		System.out.println("Prevented login through fake credentails");

	}
	
	@Test
	public void login() {
		try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		chromeDriver.findElement(By.id("email")).clear();
		chromeDriver.findElement(By.id("passwd")).clear();
		//Filling up login details
		chromeDriver.findElement(By.id("email")).sendKeys(email);
		chromeDriver.findElement(By.id("passwd")).sendKeys(password);
		
		//Verifying whether values are entered properly
		Assert.assertEquals(chromeDriver.findElement(By.id("email")).getAttribute("value"),email);
		Assert.assertEquals(chromeDriver.findElement(By.id("passwd")).getAttribute("value"),"12345");
		
		chromeDriver.findElement(By.id("SubmitLogin")).click();
		
		String expectedURL = "http://automationpractice.com/index.php?controller=my-account";
		String actualURL = chromeDriver.getCurrentUrl();
		Assert.assertEquals(actualURL,expectedURL);
		System.out.println("Login through valid credentials passed");
		

		
	}
	
	
	
	@AfterTest
	public void closeBrowser() throws InterruptedException {
		Thread.sleep(2000L);
		chromeDriver.close();
		System.out.println("Login test has ended");
	}
	
	
}
