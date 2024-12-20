package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.CommonMethods;

import java.time.Duration;

public class Hooks extends CommonMethods {

    @Before
    public void start() {
        openBrowserAndLaunchApplication();

    }

    @After
    public void end(Scenario scenario) {

        byte[] pic;
        if (scenario.isFailed()) {
            pic = takeScreenshot("failed/" + scenario.getName());
        } else {
            pic = takeScreenshot("passed/" + scenario.getName());
        }
        scenario.attach(pic, "image/png", scenario.getName());

        try {
            System.out.println("Closing the browser..");
            closeBrowser();
            System.out.println("Browser closed successfully.");
        } catch (Exception e) {
            System.err.println("Error while closing the browser: " + e.getMessage());
        }


    }


}
