package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class api_02_locator {

	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("http://live.guru99.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_LoginWithEmptyInformation() {

		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.id("pass")).sendKeys("");
		// Step 03 - Để trống Username/ Password - Click Login button
		driver.findElement(By.id("send2")).click();

		String errorEmail = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		Assert.assertEquals("This is a required field.", errorEmail);

		String errorPassword = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
		Assert.assertEquals("This is a required field.", errorPassword);
	}

	@Test
	public void TC_02_LoginWithEmailInvalid() {

		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("abc@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123456");
		// Nhập username không hợp lệ: abc@gmail.com
		// Nhập password hợp lệ
		// Click Login button
		driver.findElement(By.id("send2")).click();

		// Verify message thông báo lỗi
		String errorEmail = driver.findElement(By.xpath("//span[text()='Invalid login or password.']")).getText();
		Assert.assertEquals("Invalid login or password.", errorEmail);

	}

	@Test
	public void TC_03_LoginWithPasswordIncorrect() {
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("dzunguyenit@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123456789");
		// Nhập username hợp lệ: dzunguyenit@gmail.com
		// Nhập password không hợp lệ
		// Click Login button
		driver.findElement(By.id("send2")).click();

		String errorPassword = driver.findElement(By.xpath("//span[text()='Invalid login or password.']")).getText();
		Assert.assertEquals("Invalid login or password.", errorPassword);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
