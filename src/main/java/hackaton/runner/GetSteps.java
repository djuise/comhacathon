package hackaton.runner;

import hackaton.Logger.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static hackaton.runner.Constants.SCENARIO_EXTENSION;

class GetSteps {
    static List<String> getStringSteps(String fileName) {
        String defaultFolder = "scenarios" + File.separator;
        String path = defaultFolder + fileName + SCENARIO_EXTENSION;
        List<String> steps = new LinkedList<>();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (Stream<String> stream = Files.lines(Paths.get(Objects.requireNonNull(classLoader.getResource(path)).getPath()))) {
            stream.forEach(steps::add);
        } catch (IOException | NullPointerException e) {
            new Logger().error("Step with name " + path + " not found.");
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

        return steps;
    }
}
