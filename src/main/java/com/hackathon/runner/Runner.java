package com.hackathon.runner;

import com.hackathon.helpers.BaseTest;
import com.hackathon.runner.annotations.AfterTestImpl;
import com.hackathon.runner.annotations.BeforeTestImpl;
import com.hackathon.runner.exeptions.StepNotFoundException;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

public class Runner<T extends BaseTest> implements Runnable {

    private T test;
    private List<String> steps;

    public Runner(T test) {
        this.test = test;
        steps = GetSteps.getStringSteps(test.scenario);
    }

    @Override
    public void run() {
        BeforeTestImpl.setUp(test.getClass());
        runTest();
        AfterTestImpl.tearDown(test.getClass());
    }

    private void runTest() {
        try {
            List<Step> stepList = new FindSteps().getSteps(steps, test.classesList, test.scenario);
            runTest(stepList);
        } catch (StepNotFoundException e) {
            e.printStackTrace();
            AfterTestImpl.tearDown(test.getClass());
            Thread.currentThread().interrupt();
        }
    }

    private void runTest(List<Step> stepList) {
        for (Step step : stepList) {
            try {
                runWithArguments(step);
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
                AfterTestImpl.tearDown(test.getClass());
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