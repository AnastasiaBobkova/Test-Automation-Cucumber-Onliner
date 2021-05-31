package onliner.hooks;

import framework.Browser;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hook {
    private static Browser browser = Browser.getInstance();

    /**
     * Method to run before each scenario
     */
    @Before
    public void setup() {
        browser.windowMaximize();
    }

    /**
     * Method to run after each scenario
     */
    @After
    public void teardown() {
        browser.exit();
    }
}