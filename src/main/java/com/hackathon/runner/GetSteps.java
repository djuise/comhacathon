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

class GetSteps {
    static List<String> getStringSteps(String fileName, String folderName) {
        String defaultFolder = "scenarios/";
        String folder = folderName == null ? "" : folderName;
        String path = defaultFolder + folder + fileName + ".sc";
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

    static List<String> getStringSteps(String fileName) {
        return getStringSteps(fileName, null);
    }
}
