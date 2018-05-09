package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class api_08_javaScript {

	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		// System.setProperty("webdriver.chrome.driver",
		// ".\\driver\\chromedriver.exe");
		System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		/// driver = new ChromeDriver();

	}

	@Test
	public void TC_01_JsExecutor() throws Exception {

		driver.get("http://live.guru99.com/");
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		String domain = (String) executeForBrowserElement(driver, "return document.domain");
		Assert.assertEquals("live.guru99.com", domain);

		String URL = executeForBrowserElement(driver, "return document.URL").toString();
		Assert.assertEquals("http://live.guru99.com/", URL);

		WebElement mobile = driver.findElement(By.xpath("//a[contains(text(),'Mobile')]"));
		highlightElement(driver, mobile);
		executeForWebElement(driver, mobile);
		Thread.sleep(4000);

		WebElement SonyCartButton = driver.findElement(By.xpath(
				"//h2[a[contains(text(),'Sony Xperia')]]/following-sibling::div//button[contains(.,'Add to Cart')]"));
		highlightElement(driver, SonyCartButton);

		executeForWebElement(driver, SonyCartButton);
		Thread.sleep(4000);

		String innerText = executeForBrowserElement(driver, "return document.documentElement.innerText;").toString();
		Assert.assertTrue(innerText.contains("Sony Xperia was added to your shopping cart."));

		WebElement privacyLink = driver.findElement(By.xpath("//a[contains(text(),'Privacy Policy')]"));
		executeForWebElement(driver, privacyLink);
		Thread.sleep(4000);

		String privacyPageTtitle = executeForBrowserElement(driver, "return document.title;").toString();
		Assert.assertEquals("Privacy Policy", privacyPageTtitle);

		scrollToBottomPage(driver);

		WebElement CNTMsg = driver.findElement(By.xpath("//th[text()='WISHLIST_CNT']/following-sibling::td"));
		Assert.assertTrue(CNTMsg.isDisplayed());

		executeForBrowserElement(driver, "window.location = 'http://demo.guru99.com/v4/'");
		Thread.sleep(4000);
		String domainFinal = executeForBrowserElement(driver, "return document.domain").toString();
		Assert.assertEquals("demo.guru99.com", domainFinal);

	}

	@Test
	public void TC_02_removeAttribute() throws Exception {

		driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled");

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		WebElement iframeLookingfor = driver.findElement(By.xpath("//div[@id='iframewrapper']//iframe"));
		driver.switchTo().frame(iframeLookingfor);
		// input[@name='lname']
		WebElement lnameDisable = driver.findElement(By.xpath("//input[@name='lname']"));
		removeAttributeInDOM(driver, lnameDisable, "disabled");
		String textInput = "Automation Testing";
		lnameDisable.sendKeys(textInput);

		driver.findElement(By.xpath("//input[@value='Submit']")).click();

		String textSubmit = driver.findElement(By.xpath("//*[contains(text(),'lname=')]")).getText().substring(0, 31);
		driver.switchTo().defaultContent();
		Assert.assertEquals("fname=&lname=" + textInput, textSubmit);

	}

	// Common Function

	public static void highlightElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='6px groove red'", element);
	}

	public Object executeForWebElement(WebDriver driver, WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public Object removeAttributeInDOM(WebDriver driver, WebElement element, String attribute) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public Object executeForBrowserElement(WebDriver driver, String javaSript) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript(javaSript);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public Object scrollToBottomPage(WebDriver driver) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
