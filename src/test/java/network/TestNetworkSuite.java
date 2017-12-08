package network;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestConnection.class, TestStartGameHumans.class, TestStartGameAi.class, TestStartGameDuplicate.class,
		TestStartGameSetUpDuplicate.class, TestStartGameNotFull.class, TestStartGameFull.class,
		TestStartGameDevAi.class, TestStartGameDevHumans.class })
public class TestNetworkSuite {

}
