package webdriver_10_testng;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNG_DataProviders {

	WebDriver driver;

	@BeforeClass
	public void setup() {
		driver = new FirefoxDriver();

	}

	@Test(dataProvider = "Username/Password")
	public void TestNG_01(String userName, String passWord) throws Exception {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://demo.guru99.com/v4/");
		WebElement txtUser = driver.findElement(By.xpath("//input[@name='uid']"));
		txtUser.sendKeys(userName);
		WebElement txtPass = driver.findElement(By.xpath("//input[@name='password']"));
		txtPass.sendKeys(passWord);
		WebElement btnlogin = driver.findElement(By.xpath("//input[@name='btnLogin']"));
		btnlogin.click();
		WebElement msgSuccess = driver.findElement(By.xpath("//marquee"));
		Assert.assertEquals("Welcome To Manager's Page of Guru99 Bank", msgSuccess.getText().trim());
	}

	@Test(dataProvider = "Username/Password")
	public void TestNG_02(String userName, String passWord) throws Exception {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://demo.guru99.com/v4/");
		WebElement txtUser = driver.findElement(By.xpath("//input[@name='uid']"));
		txtUser.sendKeys(userName);
		WebElement txtPass = driver.findElement(By.xpath("//input[@name='password']"));
		txtPass.sendKeys(passWord);
		WebElement btnlogin = driver.findElement(By.xpath("//input[@name='btnLogin']"));
		btnlogin.click();
		WebElement msgSuccess = driver.findElement(By.xpath("//marquee"));
		Assert.assertEquals("Welcome To Manager's Page of Guru99 Bank", msgSuccess.getText().trim());
	}

	@DataProvider(name = "Username/Password")
	public static Object[][] userandpass(Method method) {
		Object[][] result = null;
		if (method.getName().equals("TestNG_01")) {
			result = new Object[][] { { "mngr109856", "qArejUn" }, { "mngr109859", "mUbypaz" } };
		} else {
			result = new Object[][] { { "mngr109860", "taqYsyd" }, { "mngr109861", "UzahajY" },
					{ "mngr109862", "YmEtymu" } };
		}
		return result;
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}