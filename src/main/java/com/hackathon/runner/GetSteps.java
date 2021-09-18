package com.hackathon.runner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class GetSteps {


    static List<String> getSteps(String fileName, String folderName) {
        String defaultFolder = "scenarios/";
        String folder = folderName == null ? "" : folderName;
        String path = defaultFolder + folder + fileName + ".sc";
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        List<String> steps = new LinkedList<>();

        try (Stream<String> stream = Files.lines(Paths.get(classLoader.getResource(path).getPath()))) {
            stream.forEach(steps::add);
        } catch (IOException | NullPointerException exception) {
            System.out.println("Step with name " + fileName + " not found.");
            steps = null;
            exception.printStackTrace();
            Thread.currentThread().interrupt();
        }

        return steps;
    }

    static List<String> getSteps(String fileName) {
        return getSteps(fileName, null);
    }
}
