package com.hackathon.runner;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static com.hackathon.runner.Constants.SCENARIO_EXTENSION;

class GetSteps {
    static List<String> getStringSteps(String fileName) {
        String defaultFolder = "scenarios/";
        String path = defaultFolder + fileName + SCENARIO_EXTENSION;
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        List<String> steps = new LinkedList<>();

        try {
            URI uri = ClassLoader.getSystemResource(path).toURI();
            Path filePath = Paths.get(uri);
            Stream<String> stream = Files.lines(filePath);
            stream.forEach(steps::add);
        } catch (IOException | NullPointerException | URISyntaxException e) {
            System.out.println("Step with name " + fileName + " not found.");
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

        return steps;
    }
}
