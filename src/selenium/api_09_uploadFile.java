package selenium;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class api_09_uploadFile {

	WebDriver driver;
	public String pathFile, fileName, email, firstName, dynamicFolder, pathFileAutoIT;

	@BeforeClass
	public void beforeClass() {
		fileName = "image.png";
		pathFile = ".\\files\\image.png";
		email = "auto" + random() + "@gmail.com";
		dynamicFolder = "dam" + random();
		firstName = "Dam Dao";
		pathFileAutoIT = ".\\uploadFile\\firefox.exe";
		dynamicFolder = "dam" + random();
		driver = new FirefoxDriver();
		// System.setProperty("webdriver.chrome.driver",
		// ".\\driver\\chromedriver.exe");
		// driver = new ChromeDriver();

	}

	@Test
	public void Testscript01_uploadBySendkeys() throws Exception {

		driver.get("http://www.helloselenium.com/2015/03/how-to-upload-file-using-sendkeys.html");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		WebElement btnBrowse = driver.findElement(By.xpath("//input[@name='uploadFileInput']"));
		btnBrowse.sendKeys(pathFile);
		WebElement btnUpload = driver.findElement(By.xpath("//input[@name='uploadFileButton']"));
		btnUpload.click();
	}

	@Test
	public void Testscript02_uploadByAutoIT() throws Exception {

		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		WebElement btnBrowse = driver.findElement(By.xpath("//input[@name='files[]']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btnBrowse);
		Runtime.getRuntime().exec(new String[] { pathFileAutoIT, pathFile });
		Thread.sleep(3000);
		WebElement result = driver.findElement(
				By.xpath("//table[@role='presentation']//p[@class='name' and contains(text(),'" + fileName + "')]"));
		Assert.assertTrue(result.isDisplayed());
		WebElement btnUpload = driver.findElement(By.xpath("//span[contains(text(),'Start upload')]"));
		js.executeScript("arguments[0].click();", btnUpload);

	}

	@Test
	public void Testscript03_uploadByRobot() throws Exception {

		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		// Specify the file location with extension
		StringSelection select = new StringSelection(pathFile);

		// Copy to clipboard
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);

		WebElement btnBrowse = driver.findElement(By.xpath("//input[@name='files[]']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btnBrowse);

		Robot robot = new Robot();
		Thread.sleep(1000);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);

		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		Thread.sleep(1000);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		WebElement result = driver.findElement(
				By.xpath("//table[@role='presentation']//p[@class='name' and contains(text(),'" + fileName + "')]"));
		Assert.assertTrue(result.isDisplayed());
	}

	@Test
	public void Testscript04_uploadByAutoIT() throws Exception {

		driver.get("https://encodable.com/uploaddemo/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		WebElement btnUpload = driver.findElement(By.xpath("//input[@id='uploadname1']"));
		btnUpload.sendKeys(pathFile);

		driver.findElement(By.xpath("//input[@id='newsubdir1']")).sendKeys(dynamicFolder);
		driver.findElement(By.xpath("//input[@name='email_address']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name='first_name']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@id='uploadbutton']")).click();

		WebElement emailVerify = driver.findElement(By.xpath("//dd[contains(text(),'Email Address: " + email + "')]"));
		Assert.assertTrue(emailVerify.isDisplayed());

		WebElement firstNameVerify = driver
				.findElement(By.xpath("//dd[contains(text(),'First Name: " + firstName + "')]"));
		Assert.assertTrue(firstNameVerify.isDisplayed());

		WebElement fileUpload = driver.findElement(By.xpath("//a[contains(text(),'" + fileName + "')]"));
		Assert.assertTrue(fileUpload.isDisplayed());

		driver.findElement(By.xpath("//a[contains(text(),'View Uploaded Files')]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'" + dynamicFolder + "')]")).click();
		String verifyfileUpload = driver.findElement(By.xpath("//*[@id='fclink-imagepng']")).getText();
		Assert.assertTrue(verifyfileUpload.contains(fileName));

	}

	public String random() {
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
