package assignment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AutomationPractice {
	private WebDriver driver = null;
	private WebDriverWait wait;
	private String downloadPath;
	private String testData;

	@BeforeMethod
	private void initializeEnviornment() {
		try {
			/*ChromeOptions objChromeOptions = new ChromeOptions();
			objChromeOptions.setPageLoadStrategy(PageLoadStrategy.NONE);
			Map<String, Object> prefs = new HashMap<String, Object>();
			downloadPath = System.getProperty("user.dir") + File.separator + "target" + File.separator
					+ "downloadFiles";
			prefs.put("download.default_directory", downloadPath);
			prefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1);
			prefs.put("profile.default_content_settings.geolocation", 1);
			objChromeOptions.setExperimentalOption("prefs", prefs);
			driver = new ChromeDriver(objChromeOptions);*/
			

			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir")+ "\\driver\\chromedriver.exe");
			driver = new ChromeDriver();
			wait = new WebDriverWait(driver, 30);
		   driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			//driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			driver.navigate().to("http://automationpractice.com/index.php");
		} catch (Exception var4) {
			var4.printStackTrace();
		}
	}

	@Test
	private void testScript() {
		logReporter("Click on Sign In Button", click(By.xpath("//a[@title='Log in to your customer account']")));
		testData = getRandomString(10) + "@gmail.com";
		logReporter("Set Email Address in Create an coount field", testData, setText(By.id("email_create"), testData));
		logReporter("Click on Create an account button", click(By.id("SubmitCreate")));
		logReporter("Select Male Checkbox for title", click(By.id("id_gender1")));
		logReporter("Set Customer First Name", "First Name", setText(By.id("customer_firstname"), "First Name"));
		logReporter("Set Customer Last Name", "Last Name", setText(By.id("customer_lastname"), "Last Name"));
		logReporter("Set Customer Password", "Pass@123", setText(By.id("passwd"), "Pass@123"));
		logReporter("Select Date of Birth Days", "1", selectDropdown(By.id("days"), "1"));
		logReporter("Select Date of Birth Month", "2", selectDropdown(By.id("months"), "2"));
		logReporter("Select Date of Birth Year", "1990", selectDropdown(By.id("years"), "1990"));
		logReporter("Set Customer First Name Address Section", "First Name", setText(By.id("firstname"), "First Name"));
		logReporter("Set Customer Last Name Address Section", "Last Name", setText(By.id("lastname"), "Last Name"));
		logReporter("Set Customer Address in Address Section", "Address Field Value Deliver",
				setText(By.id("address1"), "Address Field Value Deliver"));
		logReporter("Set Customer City in Address Section", "MyTestCity", setText(By.id("city"), "MyTestCity"));
		logReporter("Select State Value in Address Section", "Alaska", selectDropdown(By.id("id_state"), "2"));
		logReporter("Select Pin code in Address Section", "40070", setText(By.id("postcode"), "40070"));
		logReporter("Select Pin code in Address Section", "8080808080", setText(By.id("phone_mobile"), "8080808080"));
		logReporter("Click Submit button", click(By.id("submitAccount")));
		logReporter("Click Woman Section", click(By.xpath("//a[@title='Women']")));
		logReporter("Move to Element Specific Product", "Faded Short Sleeve T-shirts", moveToElement(
				By.xpath("//h5//a[@title='Faded Short Sleeve T-shirts']//ancestor::div[@class='product-container']")));
		logReporter("Click on Add to Cart Button", click(By.xpath(
				"//h5//a[@title='Faded Short Sleeve T-shirts']//ancestor::div[@class='right-block']//a[@title='Add to cart']")));
		logReporter("Verify Product Added PopUp Displayed",
				checkElementDisplayed(By.xpath("//div[@id='layer_cart']/div[@class='clearfix']")));
		logReporter("Click Product Added PopUp Close Button", click(By.xpath("//span[@title='Close window']")));
		logReporter("Move to Element Specific Product", "Printed Dress",
				moveToElement(By.xpath("//h5//a[@title='Printed Dress']//ancestor::div[@class='product-container']")));
		logReporter("Click on Add to Cart Button", click(By.xpath(
				"//h5//a[@title='Printed Dress']//ancestor::div[@class='right-block']//a[@title='Add to cart']")));
		logReporter("Verify Product Added PopUp Displayed",
				checkElementDisplayed(By.xpath("//div[@id='layer_cart']/div[@class='clearfix']")));
		logReporter("Click Product Added PopUp Proceed to checkout Button",
				click(By.xpath("//a[@title='Proceed to checkout']")));
		testData = getText(By.id("total_price"));
		logReporter("Verify Product Total Price", testData, !testData.equals(""));

		logReporter("Click Proceed to Checkout Button",
				click(By.xpath("//p[@class='cart_navigation clearfix']//a[@title='Proceed to checkout']")));
		logReporter("Click Proceed to Checkout button", click(By.xpath("//button[@name='processAddress']")));
		logReporter("Select I Agree Terms and conditions", click(By.id("uniform-cgv")));
		logReporter("Click Proceed to Checkout Button", click(By.xpath("//button[@name='processCarrier']")));
		testData = getText(By.id("total_price"));
		logReporter("Verify Product Total Price", testData, !testData.equals(""));
		logReporter("Click Pay by Check", click(By.xpath("//a[@title='Pay by check.']")));
		logReporter("Click I Confirm my order", click(By.xpath("//p[@id='cart_navigation']//button")));
		testData = getText(By.xpath("//div[@class='box order-confirmation']/span"));
		logReporter("Verify Product Total Price", testData, !testData.equals(""));
		logReporter("Click Profile Name Label", click(By.xpath("//a[@title='View my customer account']")));
		logReporter("Click Order History", click(By.xpath("//a[@title='Orders']")));
		testData = getText(By.xpath("//td[@class='history_price']"));
		logReporter("Verify Product Total Price", testData, !testData.equals(""));

	}

	private boolean selectDropdown(By xpath, String string) {
		try {

			WebElement webElement = getElementFluent(xpath);
			Select slct = new Select(webElement);
			slct.selectByValue(string);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@AfterMethod
	private void tearDown() {
		driver.quit();

	}

	public static String getRandomString(int lenght) {
		String allowedChars = "abcdefghiklmnopqrstuvwxyz";
		String randomstring = "";
		for (int i = 0; i < lenght; i++) {
			int rnum = (int) Math.floor(Math.random() * allowedChars.length());
			randomstring += allowedChars.substring(rnum, rnum + 1);
		}
		return randomstring;
	}

	private boolean checkElementDisplayed(By xpath) {
		try {
			this.waitForElementDisplayed(xpath);
			return driver.findElement(xpath).isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}

	private boolean setText(By xpath, String string) {
		try {
			WebElement webElement = getElementFluent(xpath);
			webElement.clear();
			webElement.sendKeys(string);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	private boolean moveToElement(By xpath) {
		try {
			waitForElementDisplayed(xpath);
			Actions act = new Actions(driver);
			act.moveToElement(driver.findElement(xpath)).perform();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private void logReporter(String step, boolean resultLog) {
		String strLog = step;
		this.addAssertTakeScreenShot(step, strLog, "", "", "", resultLog);
	}

	private void logReporter(String step, String inputValue, boolean resultLog) {
		String strLog = step + " || Input Value : " + inputValue;
		this.addAssertTakeScreenShot(step, strLog, inputValue, "", "", resultLog);
	}

	private void addAssertTakeScreenShot(String step, String strLog, String inputValue, String expectedValue,
			String actualValue, boolean resultLog) {
		System.out.println("Step Description--> " + strLog);
	   Logger logger = Logger.getLogger(getClass());
		if (resultLog) {
			Reporter.log("Step Description--> " + strLog);
		logger.info("Step Description--> " + strLog);
			Assert.assertTrue(true);
		} else {
			String current_date = "";
			Date today = Calendar.getInstance().getTime();
			SimpleDateFormat formatter = new SimpleDateFormat("dd_MMM_yyyy_HH_mm_ss");
			current_date = formatter.format(today);
			String fileName = current_date + " Screenshot" + ".png";
			String fileWithPath = System.getProperty("user.dir") + "/custom-reports/Screenshots/" + fileName;
			Reporter.log("Step Description--> " + strLog);
		logger.error("Step Description--> " + strLog);
			TakesScreenshot scrShot = ((TakesScreenshot) driver);
			File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
			File destFile = new File(fileWithPath);
			try {
				FileUtils.moveFile(srcFile, destFile);
			} catch (IOException iOException) {
				iOException.printStackTrace();
			}
			Assert.assertTrue(false);
		}
	}

	private void waitForElementPresence(By locator) {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	private void waitForElementDisplayed(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	private void waitForElementClickable(By locator) {
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	private WebElement getElementFluent(final By locator) throws NoSuchElementException, TimeoutException {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(10, TimeUnit.SECONDS)
				.pollingEvery(500, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class)
				.ignoring(InvalidElementStateException.class).ignoring(StaleElementReferenceException.class);
		WebElement mobileElement = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver t) {
				return driver.findElement(locator);
			}
		});
		return mobileElement;
	}

	private String getText(By locator) {
		waitForElementPresence(locator);
		String returnString = getElementFluent(locator).getText();
		return returnString;
	}

	private boolean click(By locator) {
		try {
			waitForElementClickable(locator);
			getElementFluent(locator).click();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

}
