package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import javax.naming.NamingException;

public abstract class BrowserFactory {
    /**
     * Setting up Driver
     * @param type Browser type
     * @return WebDriver
     */
    public static WebDriver setUp(final Browsers type) {

        WebDriver driver = null;

        switch(type) {
            case CHROME:
                System.setProperty("webdriver.chrome.driver", Browser.props.getProperty("chromeDriverPath"));
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", Browser.props.getProperty("firefoxDriverPath"));
                driver = new FirefoxDriver();
                break;
            default:
                break;
        }
        return driver;
    }

    /**
     * Setting up Driver
     * @param type Browser type
     * @return WebDriver
     * @throws NamingException NamingException
     */
    public static WebDriver setUp(final String type) throws NamingException {
        for (Browsers t : Browsers.values()) {
            if (t.toString().equalsIgnoreCase(type)) {
                return setUp(t);
            }
        }
        throw new NamingException("Unexpected value, browser is not supported: " + type);
    }
}
