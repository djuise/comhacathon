package com.hackathon.runner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

class GetSteps {
    static List<String> getStringSteps(String fileName) {
        String defaultFolder = "scenarios/";
        String path = defaultFolder + fileName + ".sc";
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        List<String> steps = new LinkedList<>();

        try (Stream<String> stream = Files.lines(Paths.get(Objects.requireNonNull(classLoader.getResource(path)).getPath()))) {
            stream.forEach(steps::add);
        } catch (IOException | NullPointerException exception) {
            System.out.println("Step with name " + fileName + " not found.");
            exception.printStackTrace();
            Thread.currentThread().interrupt();
        }

        return steps;
    }
}
