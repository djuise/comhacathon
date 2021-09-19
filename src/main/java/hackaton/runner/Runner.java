package hackaton.runner;


import hackaton.Logger.Logger;
import hackaton.helpers.BaseTest;
import hackaton.runner.annotations.AfterTestImpl;
import hackaton.runner.annotations.BeforeTestImpl;
import hackaton.runner.exeptions.StepNotFoundException;
import lombok.Getter;
import lombok.SneakyThrows;

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
        logger.info("Test started: " + test.getClass().getName());
        try {
            runTest();
        } catch (StepNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            logger.error("Test failed: " + test.getClass().getName());
            e.printStackTrace();
        }

        AfterTestImpl.tearDown(test.getClass());
        logger.info("Test passed: " + test.getClass().getName());
    }

    private void runTest() throws StepNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        List<Step> stepList = new FindSteps().getSteps(steps, test.getClassesList(), test.getScenario());
        BeforeTestImpl.setUp(test.getClass());
        runTest(stepList);
    }

    private void runTest(List<Step> stepList) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        for (Step step : stepList) {
            runWithArguments(step);
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