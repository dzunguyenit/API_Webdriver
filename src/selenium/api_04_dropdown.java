package selenium;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class api_04_dropdown {

	WebDriver driver;
	public String customerName, dateOfBirth, address, city, state, PIN, mobileNumber, email, username, password;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
//		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
//		driver = new ChromeDriver();

		customerName = "Automaton Testing";
		dateOfBirth = "22/01/1990";
		address = "143 Truong Tho";
		city = "TPHCM";
		state = "Thu Duc";
		PIN = "123456";
		mobileNumber = "0966888222";
		email = "automation" + randomemail() + "@gmail.com";
		username = "mngr101234";
		password = "sYgabad";

	}

	@Test
	public void TestScript01() throws Exception {
		driver.get("http://daominhdam.890m.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		Select dropdown1 = new Select(driver.findElement(By.xpath("//*[@id='job1']")));
		Assert.assertFalse(dropdown1.isMultiple());
		dropdown1.selectByVisibleText("Automation Tester");
		String DropdownSelected = dropdown1.getFirstSelectedOption().getText();

		Assert.assertEquals("Automation Tester", DropdownSelected);

		dropdown1.selectByValue("manual");
		Thread.sleep(2000);
		Assert.assertEquals("Manual Tester", dropdown1.getFirstSelectedOption().getText());

		dropdown1.selectByIndex(3);
		Thread.sleep(2000);
		Assert.assertEquals("Mobile Tester", dropdown1.getFirstSelectedOption().getText());

		Assert.assertEquals(5, dropdown1.getOptions().size());

	}

	@Test
	public void Test02_Buoi5() throws Exception {
		driver.get("http://demo.guru99.com/v4");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		driver.findElement(By.xpath("//a[@href='addcustomerpage.php']")).click();
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys(customerName);
		driver.findElement(By.xpath("//input[@id='dob']")).sendKeys(dateOfBirth);
		driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys(address);
		driver.findElement(By.xpath("//input[@name='city']")).sendKeys(city);
		driver.findElement(By.xpath("//input[@name='state']")).sendKeys(state);
		driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys(PIN);
		driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys(mobileNumber);
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@name='sub']")).click();
		Thread.sleep(3000);

		String userID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		System.out.println(userID);

		String customerNameMsg = driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td"))
				.getText();

		String addressMsg = driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText();

		driver.findElement(By.xpath("//a[@href='EditCustomer.php']")).click();
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(userID);
		driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();

		Assert.assertEquals(customerNameMsg, customerName);

		Assert.assertEquals(addressMsg, address);

		WebElement txtaddressNew = driver.findElement(By.xpath("//textarea[@name='addr']"));
		String addressNew = "144 Thang Long";
		txtaddressNew.clear();
		txtaddressNew.sendKeys(addressNew);
		WebElement txtcity = driver.findElement(By.xpath("//input[@name='city']"));
		String cityNew = "Ha Noi";
		txtcity.clear();
		txtcity.sendKeys(cityNew);
		driver.findElement(By.xpath("//input[@name='sub']")).click();
		driver.findElement(By.xpath("//a[@href='EditCustomer.php']")).click();
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(userID);
		driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
		Thread.sleep(3000);

		String adressEdited = driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText();
		String cityEdited = driver.findElement(By.xpath("//input[@name='city']")).getAttribute("value");

		Assert.assertEquals(adressEdited, addressNew);

		Assert.assertEquals(cityEdited, cityNew);

	}

	public String randomemail() {
		Random rand = new Random();
		int number = rand.nextInt(900000) + 1;
		String numberString = Integer.toString(number);
		return numberString;

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
