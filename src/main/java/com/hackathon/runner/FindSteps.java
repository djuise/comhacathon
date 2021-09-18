package com.hackathon.runner;

import com.hackathon.runner.annotations.Action;
import com.hackathon.runner.annotations.Check;
import javafx.util.Pair;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

class FindSteps {

    private static final String ACTION = "Action: ";
    private static final String CHECK = "Check: ";
    private String prefix = null;
    private List<Class> classes;
    private Step step = null;

    List<Step> getSteps(List<String> stepsNames, List<Class> classes) {
        List<Step> stepsList = new LinkedList<>();
        for(String step: stepsNames) {
            stepsList.add(getStep(step, classes));
        }

        return stepsList;
    }

    private Step getStep(String stepName, List<Class> classes) {
        this.classes = classes;
        setPrefix(stepName);
        String stepWithoutPrefix = splitStepName(stepName);
        step = new StepBuilder().getStepObj(stepWithoutPrefix);
        List<Pair<Method, Class>> pairStepList = getMethods();

        if (prefix == null) {
            Exception e = new Exception("Step \"" + stepName + "\" has wrong start.");
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

        assert pairStepList != null;

        checkCountOfMethods(pairStepList, step.getName());

        step.setMethod(pairStepList.get(0).getKey());
        step.setClazz(pairStepList.get(0).getValue());

        return step;
    }

    private List<Pair<Method, Class>> getActionStep() {
        Class<Action> annotation = Action.class;
        List<Pair<Method, Class>> methodList = new LinkedList<>();
        List<Pair<Method, Class>> actionStepsMethods = getMethodsWithAnnotation(classes, annotation);
        for(Pair<Method, Class> entry: actionStepsMethods) {
            if (entry.getKey().getAnnotation(annotation).value().equals(step.getName())) {
                methodList.add(new Pair<>(entry.getKey(), entry.getValue()));
            }
        }

        return methodList;
    }

    private List<Pair<Method, Class>> getCheckStep() {
        Class<Check> annotation = Check.class;
        List<Pair<Method, Class>> methodList = new LinkedList<>();
        List<Pair<Method, Class>> actionStepsMethods = getMethodsWithAnnotation(classes, annotation);
        for(Pair<Method, Class> entry: actionStepsMethods) {
            if (entry.getKey().getAnnotation(annotation).value().equals(step.getName())) {
                methodList.add(new Pair<>(entry.getKey(), entry.getValue()));
            }
        }

        return methodList;
    }

    private <T extends Annotation> List<Pair<Method, Class>> getMethodsWithAnnotation(List<Class> clazzs, Class<T> annotation) {
        List<Pair<Method, Class>> methodList = new LinkedList<>();
        for (Class clazz: clazzs) {
            methodList.addAll(getMethodsWithAnnotation(clazz, annotation));
        }

        return methodList;
    }

    private <T extends Annotation>List<Pair<Method, Class>> getMethodsWithAnnotation(Class clazz, Class<T> annotation) {
        List<Pair<Method, Class>> methodList = new LinkedList<>();
        for (Method method: clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotation) && method.getAnnotation(annotation).annotationType().equals(annotation)) {
                methodList.add(new Pair<>(method, clazz));
            }
        }

        return methodList;
    }

    private void checkCountOfMethods(List<Pair<Method, Class>> methodList, String step) {
        if (methodList.size() == 0) {
            Exception e = new Exception("Step \"" + step + "\" not found.");
            e.printStackTrace();

            System.exit(1);
        }

        if (methodList.size() > 1) {
            Exception e = new Exception("Step \"" + step + "\" has more than one implementation.");
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    private String splitStepName(String step) {
        int removedPosition = step.indexOf(prefix) + prefix.length();

        return step.substring(removedPosition);
    }

    private void setPrefix(String stepName) {
        if (stepName.startsWith(ACTION)) {
            prefix = ACTION;
        } else if (stepName.startsWith(CHECK)) {
            prefix = CHECK;
        }
    }

    private List<Pair<Method, Class>> getMethods() {
        List<Pair<Method, Class>> pairList = null;
        switch (prefix) {
            case ACTION:
                pairList = getActionStep();
                break;
            case CHECK:
                pairList = getCheckStep();
                break;
        }

        return pairList;
    }
}
