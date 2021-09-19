package hackaton.runner;


import hackaton.Logger.Logger;
import hackaton.helpers.BaseTest;
import hackaton.runner.annotations.AfterTestImpl;
import hackaton.runner.annotations.BeforeTestImpl;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

public class Runner<T extends BaseTest> implements Runnable {

    private T test;
    private List<String> steps;
    private Logger logger = new Logger();

    Runner(T test) {
        this.test = test;
        steps = GetSteps.getStringSteps(test.getScenario());
    }

    @Override
    public void run() {
        logger.info("Test started: " + test.getScenario());
        runTest();
        AfterTestImpl.tearDown(test.getClass());
        logger.info("Test passed: " + test.getScenario());
    }

    private void runTest() {
        try {
            List<Step> stepList = new FindSteps().getSteps(steps, test.getClassesList(), test.getScenario());
            BeforeTestImpl.setUp(test.getClass());
            runTest(stepList);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            logger.error("Failed: " + test.getScenario());
            e.printStackTrace();
            AfterTestImpl.tearDown(test.getClass());
            System.exit(1);
        }
    }

    private void runTest(List<Step> stepList) throws Exception {
        for (Step step : stepList) {
            runWithArguments(step);
        }
    }

    private void runWithArguments(Step step) throws Exception {
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