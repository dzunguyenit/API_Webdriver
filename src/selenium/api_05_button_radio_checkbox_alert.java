package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class api_05_button_radio_checkbox_alert {

	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
	}

	@Test
	public void Test01_button() throws Exception {
		driver.get("http://daominhdam.890m.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//button[@id='button-enabled']")).click();
		Assert.assertEquals("http://daominhdam.890m.com/#", driver.getCurrentUrl());
		driver.navigate().back();
		WebElement buttonEnable = driver.findElement(By.xpath("//button[@id='button-enabled']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", buttonEnable);
		Assert.assertEquals("http://daominhdam.890m.com/#", driver.getCurrentUrl());

	}

	@Test
	public void Test02_checkBox() throws Exception {
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		WebElement checkbox = driver
				.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);

		Assert.assertTrue(checkbox.isSelected());

		if (checkbox.isSelected()) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
			Assert.assertFalse(checkbox.isSelected());
		}
	}

	@Test
	public void Test03_radioButton() throws Exception {
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		WebElement radio = driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", radio);

		Assert.assertTrue(radio.isSelected());

		if (!radio.isSelected()) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", radio);
			Assert.assertFalse(radio.isSelected());
		}

	}

	@Test
	public void Test04_jsAlert() throws Exception {
		driver.get("http://daominhdam.890m.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//button[contains(text(),'Click for JS Alert')]")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("I am a JS Alert", alert.getText());
		alert.accept();
		String textResult = driver.findElement(By.xpath("//p[@id='result']")).getText();
		Assert.assertEquals("You clicked an alert successfully", textResult);

	}

	@Test
	public void Test05_jsConfirm() throws Exception {
		driver.get("http://daominhdam.890m.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//button[contains(text(),'Click for JS Confirm')]")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("I am a JS Confirm", alert.getText());
		alert.dismiss();
		String textResult = driver.findElement(By.xpath("//p[@id='result']")).getText();
		Assert.assertEquals("You clicked: Cancel", textResult);

	}

	@Test
	public void Test06_jsConfirm() throws Exception {
		driver.get("http://daominhdam.890m.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//button[contains(text(),'Click for JS Prompt')]")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("I am a JS prompt", alert.getText());
		String textInput = "Vu Nguyen";
		alert.sendKeys(textInput);
		alert.accept();
		String textResult = driver.findElement(By.xpath("//p[@id='result']")).getText();
		Assert.assertEquals("You entered: " + textInput, textResult);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
