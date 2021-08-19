package scripts;

import utilities.Context;
import utilities.Driver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RerunScenario {

//    public static void main(String[] args) {
//        runScript();
//    }

    public static void runScript() {
        Driver.closeDriver();

        ProcessBuilder processBuilder = new ProcessBuilder();

        Context.isFailure = true;
        String msg = "echo re-running the failed scenario/s...";
//        String mvnCommand = "mv target/rerun.txt rerun.txt && mvn clean verify -Dcucumber.options='@rerun.txt'";
        String mvnCommand = "mvn verify -Dcucumber.options='@rerun/failed_scenarios.txt'";

        // Run shell command
        String osName = System.getProperty("os.name");
        if (osName.contains("Windows")) {
            System.out.println("osName 00= " + osName);
            processBuilder.command("cmd.exe", "/c", msg + " && " + mvnCommand);
            System.out.println("mvnCommand = " + mvnCommand);
        } else {
            System.out.println("osName 11= " + osName);
            processBuilder.command("bash", "-c", msg + " && " + mvnCommand);
        }

        try {
            Process process = processBuilder.start();

            StringBuilder output = new StringBuilder();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

            int exitVal = process.waitFor();
            System.out.println("exitVal = " + exitVal);
            if (exitVal == 0) {
                System.out.println(output);
                System.exit(0);
            } else {
                //abnormal...
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
