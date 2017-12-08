package strategy;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestStrategy1Case1.class, TestStrategy1Case2.class, TestStrategy2Case1.class,
		TestStrategy2Case2.class })
public class TestStrategySuite {

}
