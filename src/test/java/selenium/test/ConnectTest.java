package selenium.test;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.lexi.comp4004.common.game.util.Config.Endpoint;
import com.lexi.comp4004.common.game.util.Config.Key;
import com.lexi.comp4004.server.util.JsonUtil;

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

}
