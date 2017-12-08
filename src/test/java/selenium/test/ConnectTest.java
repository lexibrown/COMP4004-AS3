package selenium.test;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.lexi.comp4004.common.game.util.Config.Endpoint;
import com.lexi.comp4004.common.game.util.Config.Key;
import com.lexi.comp4004.server.util.JsonUtil;

import selenium.AbstractSeleniumTest;

public class ConnectTest extends AbstractSeleniumTest {

	@Test
	public void testConnect() throws Exception {
		driver.get(baseUrl + Endpoint.Connect.CONNECT + "?user=test1");
		
		WebElement webElement = driver.findElement(By.id("content"));
		String token = webElement.getText();

		@SuppressWarnings("unchecked")
		Map<String, Object> map = JsonUtil.parse(token, Map.class);
		
		assertTrue(map.containsKey(Key.TOKEN));
		assertNotNull(map.get(Key.TOKEN));
	}	
	
	@SuppressWarnings("unchecked")
	@Test
	public void testUsernameInUse() throws Exception {
		driver.get(baseUrl + Endpoint.Connect.CONNECT + "?user=test2");
		WebElement webElement = driver.findElement(By.id("content"));
		String token = webElement.getText();

		Map<String, Object> map = JsonUtil.parse(token, Map.class);
		assertTrue(map.containsKey(Key.TOKEN));
		assertNotNull(map.get(Key.TOKEN));

		driver.get(baseUrl + Endpoint.Connect.CONNECT + "?user=test2");
		webElement = driver.findElement(By.id("content"));
		String error = webElement.getText();

		System.out.println(error);
		
		map = JsonUtil.parse(error, Map.class);
		assertTrue(map.containsKey(Key.ERROR));
		assertTrue(map.containsKey(Key.MESSAGE));
		assertNotNull(map.get(Key.ERROR));
		
	}	
	
	
	/*

	
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
		driver.get(Variables.baseUri + Endpoint.Connect.CONNECT + "?user=test1");
//		driver.navigate().to(Variables.baseUri + Endpoint.Connect.CONNECT + "?user=test1");
		WebElement webElement = driver.findElement(By.id("content"));
		String token = webElement.getText();

		System.out.println(token);
		@SuppressWarnings("unchecked")
		Map<String, Object> map = JsonUtil.parse(token, Map.class);
		System.out.println(map);
		
		assertTrue(map.containsKey(Key.TOKEN));
		assertNotNull(map.get(Key.TOKEN));
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
	*/
	
}
