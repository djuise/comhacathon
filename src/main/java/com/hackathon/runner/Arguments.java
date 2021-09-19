package com.hackathon.runner;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Arguments {
    @Getter
    private int threadCount = 1;
    public List<String> classList = new ArrayList<>();

    public Arguments(String[] args) {
        getCountOfThreads(args);
        getTestsClasses(args);

    }

    private void getTestsClasses(String[] args) {
        for (String s: args) {
            if (containsPackage(s))
                classList.add(addDefaultPackage(s));
        }
    }

    private void getCountOfThreads(String[] args) {
        Integer value = null;
        int counter = 0;
        while (value == null && counter < args.length) {
            value = getIntValue(args[counter]);
            counter++;
        }

        threadCount = value == null ? 1 : value;
    }

    private Integer getIntValue(String s) {
        Integer value = null;
        try {
            value = Integer.parseInt(s);
        } catch (NumberFormatException ignore) {}

        return value;
    }

    private boolean containsPackage(String argument) {
        return argument.contains(".");
    }

    private String addDefaultPackage(String classPackage) {
        String currentPackage = this.getClass().getPackage().toString();
        currentPackage = currentPackage.replace("package ", "");
        int f = currentPackage.indexOf("runner");
        String defaultPackage = currentPackage.substring(0, f);
        if (!classPackage.contains(defaultPackage)) {
            classPackage = defaultPackage + classPackage;
        }

        return classPackage;
    }
}
