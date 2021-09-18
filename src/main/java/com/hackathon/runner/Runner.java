package com.hackathon.runner;

import javafx.util.Pair;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

public class Runner {

    public void run(int threads, List<String> scenariosPath, List<Class> stepsClasses) {
        for (String scenarioPath: scenariosPath) {
            List<String> steps = GetSteps.getSteps(scenarioPath);
            run(steps, stepsClasses);
        }
    }

    private void run(List<String> steps, List<Class> stepsClasses) {
        List<Pair<Method, Class>> methodList = new LinkedList<>();
        for(String step: steps) {
            methodList.add(FindSteps.getActionStep(step, stepsClasses));
        }

        for (Pair<Method, Class> entry: methodList) {
            try {
                entry.getKey().invoke(entry.getValue().newInstance());
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}
