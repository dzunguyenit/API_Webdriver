package webdriver_10_testng;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestNG_PriorityAndGroup {

	WebDriver driver;

	@BeforeClass(groups = { "LoginInvalidInfor", "LoginEmpty" })
	public void beforeClass() {
		driver = new FirefoxDriver();

	}

	@Test(groups = "LoginEmpty", priority = 3, description = "Login With Empty Information")
	public void TC_01_LoginWithEmptyInformation() {
		driver.get("http://live.guru99.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.id("pass")).sendKeys("");
		driver.findElement(By.id("send2")).click();

		String errorEmail = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		Assert.assertEquals("This is a required field.", errorEmail);

		String errorPassword = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
		Assert.assertEquals("This is a required field.", errorPassword);
	}

	@Test(groups = "LoginInvalidInfor", priority = 2, description = "Login With Invalid Email")
	public void TC_02_LoginWithEmailInvalid() {
		driver.get("http://live.guru99.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("abc@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123456");
		// Click Login button
		driver.findElement(By.id("send2")).click();

		String errorEmail = driver.findElement(By.xpath("//span[text()='Invalid login or password.']")).getText();
		Assert.assertEquals("Invalid login or password.", errorEmail);

	}

	@Test(groups = "LoginInvalidInfor", priority = 1, description = "Login With Invalid Password")
	public void TC_03_LoginWithPasswordIncorrect() {
		driver.get("http://live.guru99.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("dzunguyenit@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123456789");
		// Click Login button
		driver.findElement(By.id("send2")).click();

		String errorPassword = driver.findElement(By.xpath("//span[text()='Invalid login or password.']")).getText();
		Assert.assertEquals("Invalid login or password.", errorPassword);

	}

	@AfterClass(groups = { "LoginInvalidInfor", "LoginEmpty" })
	public void afterClass() {
		driver.quit();
	}

}
