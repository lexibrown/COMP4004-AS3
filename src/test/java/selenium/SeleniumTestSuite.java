package selenium;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import selenium.test.ConnectTest;
import selenium.test.HealthCheckTest;

@RunWith(Suite.class)
@SuiteClasses({ ConnectTest.class, HealthCheckTest.class })
public class SeleniumTestSuite {
}