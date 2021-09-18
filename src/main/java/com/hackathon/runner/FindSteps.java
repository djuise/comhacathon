package com.hackathon.runner;

import javafx.util.Pair;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

class FindSteps {

    static Pair<Method, Class> getActionStep(String stepname, List<Class> clazz) {
        Class<Action> annotation = Action.class;
        String step = getStepName(stepname);
        List<Pair<Method, Class>> methodList = new LinkedList<>();
        List<Pair<Method, Class>> actionStepsMethods = getMethodsWithAnnotation(clazz, annotation);
        for(Pair<Method, Class> entry: actionStepsMethods) {
            if (entry.getKey().getAnnotation(annotation).value().equals(step)) {
                methodList.add(new Pair<>(entry.getKey(), entry.getValue()));
            }
        }
        checkCountOfMethods(methodList, step);

        return methodList.get(0);
    }

    private static <T extends Annotation> List<Pair<Method, Class>> getMethodsWithAnnotation(List<Class> clazzs, Class<T> annotation) {
        List<Pair<Method, Class>> methodList = new LinkedList<>();
        for (Class clazz: clazzs) {
            methodList.addAll(getMethodsWithAnnotation(clazz, annotation));
        }

        return methodList;
    }

    private static <T extends Annotation>List<Pair<Method, Class>> getMethodsWithAnnotation(Class clazz, Class<T> annotation) {
        List<Pair<Method, Class>> methodList = new LinkedList<>();
        for (Method method: clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotation) && method.getAnnotation(annotation).annotationType().equals(annotation)) {
                methodList.add(new Pair<>(method, clazz));
            }
        }

        return methodList;
    }

    private static void checkCountOfMethods(List<Pair<Method, Class>> methodList, String step) {
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

    private static String getStepName(String step) {
        String action = "Action: ";
        String removedPart = null;
        if (step.startsWith(action)) {
            removedPart = action;
        }

        if (removedPart == null) {
            Exception e = new Exception("Step \"" + step + "\" has wrong start.");
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

        return splitStepName(step, removedPart);
    }

    private static String splitStepName(String step, String removedSubstring) {
        int removedPosition = step.indexOf(removedSubstring) + removedSubstring.length();

        return step.substring(removedPosition);
    }
}
