package selenium.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;

import com.lexi.comp4004.common.game.util.Config.Endpoint;

import selenium.AbstractSeleniumTest;

public class ConnectTest extends AbstractSeleniumTest {
	
	@Test
	public void testConnect() throws Exception {
		driver.get(baseUrl + Endpoint.Connect.CONNECT);
		
		System.out.println(driver.findElement(By.name("token")).toString());
		
	}
}
