package scripts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RerunScenario {

    private static ProcessBuilder processBuilder = new ProcessBuilder();

    public static void runScript() {
        String msg = "echo re-running the failed scenario/s...";
        String mvnCommand = "mv target/rerun.txt rerun.txt && mvn clean verify -Dcucumber.options='@rerun.txt'";

        // Run shell command
        processBuilder.command("bash", "-c", msg+" && "+mvnCommand);

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
            if (exitVal == 0) {
                System.out.println(output);
                //System.exit(0);
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
