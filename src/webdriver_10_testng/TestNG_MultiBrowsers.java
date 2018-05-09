package webdriver_10_testng;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNG_MultiBrowsers {

	WebDriver driver;

	@Parameters({ "browser" })
	@BeforeClass
	public void setup(String browser) {
		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equals("ie")) {
			System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		} else if (browser.equals("firefox"))
			driver = new FirefoxDriver();
	}

	@Parameters({ "userName", "passWord" })
	@Test
	public void TestNG_01(String userName, String passWord) throws Exception {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://demo.guru99.com/v4/");
		WebElement txtUser = driver.findElement(By.xpath("//input[@name='uid']"));
		txtUser.sendKeys(userName);
		WebElement txtPass = driver.findElement(By.xpath("//input[@name='password']"));
		txtPass.sendKeys(passWord);
		WebElement btnlogin = driver.findElement(By.xpath("//input[@name='btnLogin']"));
		btnlogin.click();
		Thread.sleep(2000);
		WebElement msgSuccess = driver.findElement(By.xpath("//marquee"));
		Assert.assertEquals("Welcome To Manager's Page of Guru99 Bank", msgSuccess.getText().trim());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}