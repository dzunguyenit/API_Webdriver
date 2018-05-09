package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class api_10_assertAndWait {

	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		// driver = new ChromeDriver();

	}

	@Test
	public void Testscript01_implicitWait() throws Exception {

		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		WebElement btnStart = driver.findElement(By.xpath("//button[contains(text(),'Start')]"));
		btnStart.click();
		WebElement waitMsg = driver.findElement(By.xpath("//h4[contains(text(),'Hello World!')]"));
		Assert.assertEquals("Hello World!", waitMsg.getText());

	}

	@Test
	public void Testscript02_explicitWait() throws Exception {

		driver.get(
				"http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[@id='ctl00_ContentPlaceholder1_ctl00_ContentPlaceholder1_RadCalendar1Panel']")));
		WebElement textDayBefore = driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceholder1_Label1']"));
		Assert.assertEquals("No Selected Dates to display.", textDayBefore.getText().trim());

		WebElement day = driver.findElement(By.xpath("//a[contains(text(),'12')]"));
		day.click();

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']")));
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[contains(@class,'rcSelected')]//a[text()='12']")));
		WebElement textDayAfter = driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceholder1_Label1']"));
		Assert.assertEquals("Tuesday, December 12, 2017", textDayAfter.getText().trim());

	}

	@Test
	public void Testscript03_fluentWait_01() throws Exception {

		driver.get("https://stuntcoders.com/snippets/javascript-countdown/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		WebElement coutDown = driver.findElement(By.xpath("//*[@id='javascript_countdown_time']"));
		wait.until(ExpectedConditions.visibilityOf(coutDown));

		new FluentWait<WebElement>(coutDown).withTimeout(10, TimeUnit.SECONDS).pollingEvery(1, TimeUnit.SECONDS)
				.until(new Function<WebElement, Boolean>() {
					public Boolean apply(WebElement element) {
						boolean flag = element.getText().endsWith("02");
						return flag;
					}
				});

	}

	@Test
	public void Testscript04_fluentWait_02() throws Exception {

		driver.get("http://toolsqa.wpengine.com/automation-practice-switch-windows/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		WebElement countdown_02 = driver.findElement(By.xpath("//*[@id='clock']"));
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(countdown_02));

		// check changeColor
		new FluentWait<WebElement>(countdown_02).withTimeout(60, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class).until(new Function<WebElement, Boolean>() {
					public Boolean apply(WebElement changeColor) {
						changeColor = driver.findElement(By.xpath("//*[@style='color: red;']"));
						return changeColor.isDisplayed();
					}
				});
		// Verify textBuzz
		WebElement coutDown_Buzz = driver.findElement(By.xpath("//*[@id='clock']"));
		new FluentWait<WebElement>(coutDown_Buzz).withTimeout(45, TimeUnit.SECONDS).pollingEvery(10, TimeUnit.SECONDS)
				.until(new Function<WebElement, Boolean>() {
					public Boolean apply(WebElement textBuzz) {
						textBuzz = driver.findElement(By.xpath("//*[@id='clock']"));
						return textBuzz.getText().contains("Buzz");
					}
				});

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
