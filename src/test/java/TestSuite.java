

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import game.TestGameObjects;
import game.TestGameResults;

@RunWith(Suite.class)
@SuiteClasses({ TestGameResults.class, TestGameObjects.class })
public class TestSuite {
}
