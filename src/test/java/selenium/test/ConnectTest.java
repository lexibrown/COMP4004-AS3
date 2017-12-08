package selenium.test;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.lexi.comp4004.common.game.util.Config.Endpoint;
import com.lexi.comp4004.server.util.Variables;

public class ConnectTest {
	
	private WebDriver driver;
	private String baseUrl;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "./libs/geckodriver.exe");
		driver = new FirefoxDriver();
		baseUrl = Variables.baseUri;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() throws Exception {
		driver.close();
		driver.quit();
	}

	@Test
	public void test() throws ClientProtocolException, IOException {
		driver.get(baseUrl);
		driver.navigate().to(Variables.baseUri + Endpoint.Connect.CONNECT + "?user=test1");
		WebElement webElement = driver.findElement(By.tagName("token"));
		WeatherApiResponse weatherApiResponse = new WeatherApiResponse();
		String ExpectedString = weatherApiResponse.GetResponse();
		System.out.println("ExpectedString is: " + ExpectedString);
		String DisplayedString = webElement.getText();
		System.out.println("DisplayedString is: " + DisplayedString);
		assertTrue(DisplayedString.equals(ExpectedString));
	}
	
	public class WeatherApiResponse {

		private final String USER_AGENT = "Mozilla/5.0";

		public String GetResponse() throws ClientProtocolException, IOException {
			StringBuffer result = new StringBuffer();
			HttpClient client = new DefaultHttpClient();
			String url = Variables.baseUri + Endpoint.Connect.CONNECT + "?user=test1";
			HttpGet request = new HttpGet(url);
			request.addHeader("User-Agent", USER_AGENT);
			HttpResponse response = client.execute(request);
			int responseCode = response.getStatusLine().getStatusCode();
			System.out.println("Response Code: " + responseCode);
			try {
				if (responseCode == 200)

				{
					System.out.println("Get Response is Successfull");
					BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
					String line = "";
					while ((line = reader.readLine()) != null) {
						result.append(line);
						System.out.println(result.toString());
					}
				}
				return result.toString();

			} catch (Exception ex) {
				result.append("Get Response Failed");
				return result.toString();
			}

		}
	}
	
}
