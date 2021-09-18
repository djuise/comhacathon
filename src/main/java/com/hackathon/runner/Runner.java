package com.hackathon.runner;

import com.hackathon.helpers.BaseTest;
import com.hackathon.runner.annotations.AfterTestImpl;
import com.hackathon.runner.annotations.BeforeTestImpl;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

public class Runner {

    public <T extends BaseTest> void run(T test) {
        List<String> steps = GetSteps.getStringSteps(test.scenario);
        BeforeTestImpl.setUp(test.getClass());
        run(steps, test.classesList);
        AfterTestImpl.tearDown(test.getClass());
    }

    private void run(List<String> steps, List<Class> stepsClasses) {
        List<Step> stepList = new FindSteps().getSteps(steps, stepsClasses);
        for (Step step : stepList) {
            try {
                runWithArguments(step);
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    private void runWithArguments(Step step) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        int stringCounter = 0;
        int doubleCounter = 0;
        int intCounter = 0;
        List<Object> paramsList = new LinkedList<>();
        Class<?>[] paramsCount = step.getMethod().getParameterTypes();
        for (Class clazz : paramsCount) {
            if (clazz.getTypeName().equals(String.class.getTypeName())) {
                paramsList.add(step.getStringVars().get(stringCounter));
                stringCounter++;
            } else if (clazz.getTypeName().equals(double.class.getTypeName())) {
                paramsList.add(step.getDoubleVars().get(doubleCounter));
                doubleCounter++;
            } else if (clazz.getTypeName().equals(int.class.getTypeName())) {
                paramsList.add(step.getIntVars().get(intCounter));
                intCounter++;
            }
        }

        step.getMethod().invoke(step.getClazz().newInstance(), paramsList.toArray());
    }
}