package selenium;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.lexi.comp4004.server.util.Variables;

public abstract class AbstractSeleniumTest {

	protected static WebDriver driver;
	protected static String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeClass
	public static void beforeClass() {
		System.setProperty("webdriver.gecko.driver", "./libs/geckodriver.exe");
		driver = new FirefoxDriver();
		baseUrl = Variables.baseUri;
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}

	/**
	 * Setup the Firefox driver
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		reset();
	}

	/**
	 * Call the game's internal reset. Sets the game back to a fresh state.
	 */
	protected void reset() {
		// driver.get(baseUrl + "admin/reset");
	}

	/**
	 * Tear down the Firefox driver, fail the test if any errors occur
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	@AfterClass
	public static void afterClass() {
		driver.quit();
	}

	protected boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	protected boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	protected String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
