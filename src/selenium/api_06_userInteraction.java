package selenium;

import static org.testng.Assert.assertEquals;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class api_06_userInteraction {

	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		driver = new ChromeDriver();

	}

	@Test
	public void Testscript01_TC01_hoverMouse() throws Exception {

		driver.get("http://daominhdam.890m.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		WebElement elementTooltip = driver.findElement(By.xpath("//a[contains(text(),'Hover over me')]"));
		Assert.assertTrue(elementTooltip.isDisplayed());

		Actions action = new Actions(driver);
		action.moveToElement(elementTooltip).perform();
		WebElement hoverTooltip = driver.findElement(By.xpath("//div[contains(text(),'Hooray!')]"));
		assertEquals("Hooray!", hoverTooltip.getText());

	}

	@Test
	public void Testscript01_TC02_moveMouse() throws Exception {

		driver.get("http://www.myntra.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		WebElement informPersionnal = driver.findElement(By.xpath("//*[@data-reactid='493']"));
		Actions action = new Actions(driver);
		action.moveToElement(informPersionnal).perform();

		WebElement btnlogin = driver.findElement(By.xpath("//a[contains(text(),'login')]"));
		action.moveToElement(btnlogin).perform();
		action.click().build().perform();
		WebElement formLogin = driver.findElement(By.xpath("//div[@class='login-box']"));
		Assert.assertTrue(formLogin.isDisplayed());
	}

	@Test
	public void Testscript02_clickAndHold() throws Exception {

		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		List<WebElement> listItems = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		Actions action = new Actions(driver);
		action.clickAndHold(listItems.get(0)).clickAndHold(listItems.get(3)).click().perform();
		action.release();
		List<WebElement> listItemsSelected = driver
				.findElements(By.xpath("//*[@id='selectable']/li[@class='ui-state-default ui-selectee ui-selected']"));
		int number = listItemsSelected.size();
		assertEquals(4, number);
	}

	@Test
	public void Testscript03_doubleClick() throws Exception {

		driver.get("http://www.seleniumlearn.com/double-click");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		WebElement btnDouble = driver.findElement(By.xpath("//button[contains(text(),'Double-Click Me!')]"));
		Actions action = new Actions(driver);
		action.doubleClick(btnDouble).perform();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("The Button was double-clicked.", alert.getText());
		alert.accept();
	}

	@Test
	public void Testscript04_rightClick() throws Exception {

		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		WebElement btnDouble = driver.findElement(By.xpath("//span[contains(text(),'right click me')]"));
		Actions action = new Actions(driver).contextClick(btnDouble);
		action.build().perform();
		WebElement btnQuit = driver.findElement(By.xpath("//span[text()='Quit']"));
		action.moveToElement(btnQuit).perform();
		Assert.assertTrue(btnQuit.isDisplayed());
		btnQuit.click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("clicked: quit", alert.getText());
		alert.accept();
	}

	@Test
	public void Testscript05_TC01_dragAndDrop() throws Exception {

		driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		WebElement dragFrom = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement target = driver.findElement(By.xpath("//div[@id='droptarget']"));
		Actions builder = new Actions(driver);
		Action dragAndDrop = builder.clickAndHold(dragFrom).moveToElement(target).release(target).build();
		dragAndDrop.perform();
		assertEquals("You did great!", target.getText());
	}

	@Test
	public void Testscript05_TC02_dragAndDrop() throws Exception {

		driver.get("http://jqueryui.com/resources/demos/droppable/default.html");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		WebElement dragFrom = driver
				.findElement(By.xpath("//div[@class='ui-widget-content ui-draggable ui-draggable-handle']"));
		WebElement target = driver.findElement(By.xpath("//div[@class='ui-widget-header ui-droppable']"));
		WebElement text = driver.findElement(By.xpath("//div[@class='ui-widget-header ui-droppable']/p"));

		Actions builder = new Actions(driver);
		Action dragAndDrop = builder.clickAndHold(dragFrom).moveToElement(target).release(target).build();
		dragAndDrop.perform();
		assertEquals("Dropped!", text.getText());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
