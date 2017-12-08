package selenium.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.lexi.comp4004.common.game.util.Config.Endpoint;

import selenium.AbstractSeleniumTest;

public class HealthCheckTest extends AbstractSeleniumTest {

	@Test
	public void testDisconnect() throws Exception {
		driver.get(baseUrl + Endpoint.Disconnect.DISCONNECT);

		WebElement webElement = driver.findElement(By.tagName("pre"));
		String service = webElement.getText();
		assertTrue(service.contains("service"));
	}

	@Test
	public void testLobby() throws Exception {
		driver.get(baseUrl + Endpoint.Lobby.LOBBY);

		WebElement webElement = driver.findElement(By.tagName("pre"));
		String service = webElement.getText();
		assertTrue(service.contains("service"));
	}

	@Test
	public void testGame() throws Exception {
		driver.get(baseUrl + Endpoint.Game.GAME);

		WebElement webElement = driver.findElement(By.tagName("pre"));
		String service = webElement.getText();
		assertTrue(service.contains("service"));
	}

	@Test
	public void testDev() throws Exception {
		driver.get(baseUrl + Endpoint.Dev.DEV);

		WebElement webElement = driver.findElement(By.tagName("pre"));
		String service = webElement.getText();
		assertTrue(service.contains("service"));
	}

	@Test
	public void testAI() throws Exception {
		driver.get(baseUrl + Endpoint.AI.AI);

		WebElement webElement = driver.findElement(By.tagName("pre"));
		String service = webElement.getText();
		assertTrue(service.contains("service"));
	}

}
