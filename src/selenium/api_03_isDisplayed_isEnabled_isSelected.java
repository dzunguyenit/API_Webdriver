package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class api_03_isDisplayed_isEnabled_isSelected {

	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		// driver = new ChromeDriver();
		driver.get("http://daominhdam.890m.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void Test01_ElementIsDisplayed() throws Exception {
		String txtemail = "//input[@id='mail']";

		if (isElementDisplayed(driver, txtemail)) {
			driver.findElement(By.xpath("//input[@id='mail']")).sendKeys("dzunguyenit@gmail.com");
		} else {
			System.out.println("Textbox email isn't displayed");
		}
		String radioAge = "//input[@id='under_18']";

		if (isElementDisplayed(driver, radioAge)) {
			driver.findElement(By.xpath("//input[@id='under_18']")).click();
		} else {
			System.out.println("radioAge isn't displayed");
		}
		String txteducation = "//textarea[@id='edu']";

		if (isElementDisplayed(driver, txteducation)) {
			driver.findElement(By.xpath("//textarea[@id='edu']")).sendKeys("Input Textarea");
		} else {
			System.out.println("txteducation isn't displayed");
		}
	}

	@Test
	public void Test02_ElementIsEnableDisable() throws Exception {
		String txtemail = "//input[@id='mail']";

		if (isElementEnabled(driver, txtemail)) {
			System.out.println("Textbox email is enabled");
			//
		} else {
			System.out.println("Textbox email is disabled");
		}

		String radioAge = "//input[@id='under_18']";

		if (isElementEnabled(driver, radioAge)) {

			System.out.println("RadioAge is enabled");

		} else {
			System.out.println("RadioAge is disabled");
		}

		String txteducation = "//textarea[@id='edu']";

		if (isElementEnabled(driver, txteducation)) {
			System.out.println("Textbox education is enabled");
		} else {
			System.out.println("Textbox education is disabled");
		}

		String DropdowJobRole01 = "//select[@id='job1']";

		if (isElementEnabled(driver, DropdowJobRole01)) {
			System.out.println("DropdowJobRole01 is enabled");
		} else {
			System.out.println("DropdowJobRole01 is disabled");
		}

		String CkDevelopment = "//input[@id='development']";

		if (isElementEnabled(driver, CkDevelopment)) {
			System.out.println("CkDevelopment is enabled");
		} else {
			System.out.println("CkDevelopment is disabled");
		}

		String Slider01 = "//input[@id='slider-1']";

		if (isElementEnabled(driver, Slider01)) {
			System.out.println("Slider01 is enabled");
		} else {
			System.out.println("Slider01 is disabled");
		}

		String btnEnable = "//button[@id='button-enabled']";

		if (isElementEnabled(driver, btnEnable)) {
			System.out.println("BtnEnable is enabled");
		} else {
			System.out.println("BtnEnable is disabled");
		}

		String txtPassword = "//input[@id='password']";

		if (isElementEnabled(driver, txtPassword)) {
			System.out.println("txtPassword is enabled");
		} else {
			System.out.println("txtPassword is disabled");
		}

		String radioDisable = "//input[@id='radio-disabled']";

		if (isElementEnabled(driver, radioDisable)) {
			System.out.println("radioDisable is enabled");
		} else {
			System.out.println("radioDisable is disabled");
		}

		String TextareaBiography = "//textarea[@id='bio']";

		if (isElementEnabled(driver, TextareaBiography)) {
			System.out.println("TextareaBiography is enabled");
		} else {
			System.out.println("TextareaBiography is disabled");
		}
		String dropdowJobRole02 = "//select[@id='job2']";

		if (isElementEnabled(driver, dropdowJobRole02)) {
			System.out.println("dropdowJobRole02 is enabled");
		} else {
			System.out.println("dropdowJobRole02 is disabled");
		}
		String Ckdisable = "//input[@id='check-disbaled']";

		if (isElementEnabled(driver, Ckdisable)) {
			System.out.println("Ckdisable is enabled");
		} else {
			System.out.println("Ckdisable is disabled");
		}
		String Slider2 = "//input[@id='check-disbaled']";

		if (isElementEnabled(driver, Slider2)) {
			System.out.println("Slider2 is enabled");
		} else {
			System.out.println("Slider2 is disabled");
		}
		String btndisable = "//button[@id='button-disabled']";

		if (isElementEnabled(driver, btndisable)) {
			System.out.println("btndisable is enabled");
		} else {
			System.out.println("btndisable is disabled");
		}

	}

	@Test
	public void Test03_ElementIsSelected() throws Exception {

		String radioUnder18 = "//input[@id='under_18']";
		WebElement elementradio = driver.findElement(By.xpath(radioUnder18));

		if (isElementSelected(driver, radioUnder18)) {
			System.out.println("radioUnder18 is selected");
		} else {
			System.out.println("radioUnder18 isn't selected");
			elementradio.click();
		}
		String Ckdevelopment = "//input[@id='development']";
		WebElement ckdev = driver.findElement(By.xpath(Ckdevelopment));

		if (isElementSelected(driver, Ckdevelopment)) {
			System.out.println("Ckdevelopment is selected");
		} else {
			System.out.println("Ckdevelopment isn't selected");
			ckdev.click();

		}

	}

	public boolean isElementDisplayed(WebDriver driver, String yourLocator) {
		try {
			WebElement locator;
			locator = driver.findElement(By.xpath(yourLocator));
			return locator.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public boolean isElementEnabled(WebDriver driver, String yourLocator) {
		try {
			WebElement locator;
			locator = driver.findElement(By.xpath(yourLocator));
			return locator.isEnabled();

		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public boolean isElementSelected(WebDriver driver, String yourLocator) {
		try {
			WebElement locator;
			locator = driver.findElement(By.xpath(yourLocator));
			return locator.isSelected();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
