package selenium;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class api_07_iframe_window {

	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		// System.setProperty("webdriver.chrome.driver",
		// ".\\driver\\chromedriver.exe");
		driver = new FirefoxDriver();

	}

	@Test
	public void Testscript01_iFrame() throws Exception {

		driver.get("https://www.hdfcbank.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		WebElement iframeLookingfor = driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe"));
		driver.switchTo().frame(iframeLookingfor);
		String text = driver.findElement(By.xpath("//*[@id='messageText']")).getText();
		Assert.assertEquals("What are you looking for?", text);
		driver.switchTo().defaultContent();
		WebElement iframeBanner = driver.findElement(By.xpath("//div[@class='slidingbanners']//iframe"));
		driver.switchTo().frame(iframeBanner);
		List<WebElement> listItemsSelected = driver.findElements(By.xpath("//*[@class='bannerimage-container']"));
		int number = listItemsSelected.size();
		Assert.assertEquals(6, number);
		driver.switchTo().defaultContent();
		WebElement flipBanner = driver.findElement(By.xpath("//div[@class='flipBanner']"));
		Assert.assertTrue(flipBanner.isDisplayed());

	}

	@Test
	public void Testscript02_window() throws Exception {

		driver.get("http://daominhdam.890m.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		WebElement labelTab = driver.findElement(By.xpath("//a[@href='http://google.com.vn']"));
		labelTab.click();
		String parentID = driver.getWindowHandle();
		switchToWindowByTitle("Google");
		Assert.assertEquals("Google", driver.getTitle());
		closeAllWindowsWithoutParent(parentID);
	}

	@Test
	public void Testscript03_window() throws Exception {

		driver.get("http://www.hdfcbank.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		String parentID = driver.getWindowHandle();
		switchToWindowByTitle(parentID);
		// Click Agri link
		driver.findElement(By.xpath("//a[@href='/htdocs/common/agri/index.html']")).click();
		switchToWindowByTitle("HDFC Bank Kisan Dhan Vikas e-Kendra");
		// Click Account Detail link
		driver.findElement(By.xpath("//p[contains(text(),'Account Details')]")).click();
		switchToWindowByTitle("Welcome to HDFC Bank NetBanking");
		WebElement labelTab11 = driver.findElement(By.xpath("//frame[@name='footer']"));
		driver.switchTo().frame(labelTab11);
		// Click link Privacy Policy
		driver.findElement(By.xpath("//a[text()='Privacy Policy']")).click();
		driver.switchTo().defaultContent();
		switchToWindowByTitle(
				"HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");
		// Click link CSR
		driver.findElement(By.xpath("//a[@href='/csr/index.aspx']")).click();
		closeAllWindowsWithoutParent(parentID);
	}

	public void switchToWindowByID(String parent) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String childWindows : allWindows) {
			if (!childWindows.equals(parent)) {
				driver.switchTo().window(childWindows);
				break;
			}
		}
	}

	public void switchToWindowByTitle(String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String childWindows : allWindows) {
			driver.switchTo().window(childWindows);
			String childTitle = driver.getTitle();
			if (childTitle.equals(title)) {
				break;
			}
		}
	}

	public boolean closeAllWindowsWithoutParent(String parent) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String childWindows : allWindows) {
			if (!childWindows.equals(parent)) {
				driver.switchTo().window(childWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parent);

		if (driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
