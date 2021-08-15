package stepdefinitions;

import com.microsoft.playwright.Tracing;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import scripts.RerunScenario;
import utilities.Driver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Hooks {

    @Before
    public void setUp(Scenario scenario) {
        String str = scenario.getName().replaceAll(" ", "_");
        Driver.path = "test-results/videos/"+str;
        Driver.setUp();
    }

    @After
    public void takeScreenShot(Scenario scenario) throws IOException {

        //attach screenshot of failed scenario before closing browser
        if (scenario.isFailed()) {
            scenario.attach(Driver.page.screenshot(), "image/png", "screenshot");

            //rerun scenario and record the video
            File folder = new File(Driver.path);
            if (!folder.exists()) {
                Files.createDirectories(Paths.get(Driver.path));
            }
            File[] listOfFiles = folder.listFiles();

            assert listOfFiles != null;
            if(listOfFiles.length==0){
                RerunScenario.runScript();
            }

            Driver.context.tracing().stop(new Tracing.StopOptions()
                    .setPath(Paths.get("trace.zip")));

            Driver.context.close();

            //attach the video record to the test report
            String videoLink = "Watch video record: "+ listOfFiles[0].getAbsolutePath();
            scenario.attach(videoLink, "text/plain", "video Link");

        }
    }

}
