package test;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import net.bytebuddy.utility.RandomString;

public class RegistrationTest {
	WebDriver chromeDriver;
	String email;
	WebDriverWait wait;
	
	
	@BeforeTest
	public void redirectToRegister() throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "D:\\Drivers\\chromeDriver\\chromedriver.exe");
		chromeDriver = new ChromeDriver();
		email = getText(8) + "@testMail.com";
		chromeDriver.get("http://automationpractice.com/index.php");
		Thread.sleep(3000L);
		chromeDriver.findElement(By.className("login")).click();
	}
	
	@Test
	public void register() throws InterruptedException {
		
		WebDriverWait wait1  = new WebDriverWait(chromeDriver,30);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("email_create")));
		
		//Filling up registration form
		chromeDriver.findElement(By.id("email_create")).sendKeys(email);
		chromeDriver.findElement(By.id("SubmitCreate")).click();
		WebDriverWait wait  = new WebDriverWait(chromeDriver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_gender1")));
		chromeDriver.findElement(By.id("id_gender1")).click();
		chromeDriver.findElement(By.id("customer_firstname")).sendKeys("Meet");
		chromeDriver.findElement(By.id("customer_lastname")).sendKeys("Shah");
		chromeDriver.findElement(By.id("passwd")).sendKeys("12345");
		chromeDriver.findElement(By.id("email")).click();
		Select day = new Select(chromeDriver.findElement(By.id("days")));
		day.selectByValue("20");
		Select month = new Select(chromeDriver.findElement(By.id("months")));
		month.selectByValue("7");
		Select year = new Select(chromeDriver.findElement(By.id("years")));
		year.selectByValue("1990");
		chromeDriver.findElement(By.id("newsletter")).click();
		chromeDriver.findElement(By.id("firstname")).click();
		chromeDriver.findElement(By.id("lastname")).click();
		chromeDriver.findElement(By.id("company")).sendKeys("XYZ");
		chromeDriver.findElement(By.id("address1")).sendKeys("A-1 Diamond Tower");
		chromeDriver.findElement(By.id("city")).sendKeys("Chicago");
		Select state = new Select(chromeDriver.findElement(By.id("id_state")));
		state.selectByVisibleText("Illinois");
		chromeDriver.findElement(By.id("postcode")).sendKeys("00000");
		chromeDriver.findElement(By.id("phone_mobile")).sendKeys("8888888889");
		
		
		//Verifying all details are properly entered
		Assert.assertTrue(chromeDriver.findElement(By.id("id_gender1")).isSelected());
		Assert.assertEquals(chromeDriver.findElement(By.id("customer_firstname")).getAttribute("value"),"Meet");
		Assert.assertEquals(chromeDriver.findElement(By.id("customer_lastname")).getAttribute("value"),"Shah");
		Assert.assertEquals(chromeDriver.findElement(By.id("days")).getAttribute("value"),"20");
		Assert.assertEquals(chromeDriver.findElement(By.id("months")).getAttribute("value"),"7");
		Assert.assertEquals(chromeDriver.findElement(By.id("years")).getAttribute("value"),"1990");
		Assert.assertEquals(chromeDriver.findElement(By.id("company")).getAttribute("value"),"XYZ");
		Assert.assertEquals(chromeDriver.findElement(By.id("address1")).getAttribute("value"),"A-1 Diamond Tower");
		Assert.assertEquals(chromeDriver.findElement(By.id("city")).getAttribute("value"),"Chicago");		
		Assert.assertEquals(chromeDriver.findElement(By.id("postcode")).getAttribute("value"),"00000");
		Assert.assertEquals(chromeDriver.findElement(By.id("phone_mobile")).getAttribute("value"),"8888888889");
		chromeDriver.findElement(By.id("submitAccount")).click();
		Thread.sleep(2000L);
		String expectedURL = "http://automationpractice.com/index.php?controller=my-account";
		String actualURL = chromeDriver.getCurrentUrl();
		Assert.assertEquals(actualURL,expectedURL);
		
	}
	
	@AfterTest
	public void closeBrowser() {
		
		chromeDriver.close();
		System.out.println("Registration test has ended");
		
	}
	
	
	
	//In order to generate random email
	public String getText(int requiredLength) {
        String SALTCHARS = "abcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < requiredLength) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String email = salt.toString();
        return email;

    }

}
