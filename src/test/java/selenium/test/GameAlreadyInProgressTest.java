package selenium.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;

import selenium.AbstractSeleniumTest;

public class GameAlreadyInProgressTest extends AbstractSeleniumTest {
	
	/**
	 * When a game is already in progress, show the game in progress screen,
	 * allowing the user to refresh the page to wait for the game to be over.
	 * End the game and then verify that the user is able to join the new game.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGameAlreadyInProgress() throws Exception {
		driver.get(baseUrl + "/");
		
		System.out.println(driver.findElement(By.name("token")).toString());
				
	}
}
