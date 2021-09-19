package hackaton.runner;

import hackaton.runner.annotations.Action;
import hackaton.runner.annotations.Check;
import hackaton.runner.exeptions.StepNotFoundException;

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

    List<Step> getSteps(List<String> stepsNames, List<Class> classes, String scenario) throws StepNotFoundException {
        List<Step> stepsList = new LinkedList<>();
        for(String step: stepsNames) {
            stepsList.add(getStep(step, classes, scenario));
        }

        return stepsList;
    }

    private Step getStep(String stepName, List<Class> classes, String scenario) throws StepNotFoundException {
        this.classes = classes;
        setPrefix(stepName);
        String stepWithoutPrefix = splitStepName(stepName);
        step = new StepBuilder().getStepObj(stepWithoutPrefix);
        List<MethodWithClass> methodWithClassList = getMethods();

        if (prefix == null) {
            Exception e = new StepNotFoundException("Step \"" + stepName + "\" in file: \"" + scenario + Constants.SCENARIO_EXTENSION + "\" has wrong start.");
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

        assert methodWithClassList != null;

        checkCountOfMethods(methodWithClassList, step.getName(), scenario);

        step.setMethod(methodWithClassList.get(0).getMethod());
        step.setClazz(methodWithClassList.get(0).getClazz());

        return step;
    }

    private List<MethodWithClass> getActionStep() {
        Class<Action> annotation = Action.class;
        List<MethodWithClass> methodList = new LinkedList<>();
        List<MethodWithClass> actionStepsMethods = getMethodsWithAnnotation(classes, annotation);
        for(MethodWithClass methodWithClass: actionStepsMethods) {
            if (methodWithClass.getMethod().getAnnotation(annotation).value().equals(step.getName())) {
                methodList.add(new MethodWithClass(methodWithClass.getMethod(), methodWithClass.getClazz()));
            }
        }

        return methodList;
    }

    private List<MethodWithClass> getCheckStep() {
        Class<Check> annotation = Check.class;
        List<MethodWithClass> methodList = new LinkedList<>();
        List<MethodWithClass> actionStepsMethods = getMethodsWithAnnotation(classes, annotation);
        for(MethodWithClass methodWithClass: actionStepsMethods) {
            if (methodWithClass.getMethod().getAnnotation(annotation).value().equals(step.getName())) {
                methodList.add(new MethodWithClass(methodWithClass.getMethod(), methodWithClass.getClazz()));
            }
        }

        return methodList;
    }

    private <T extends Annotation> List<MethodWithClass> getMethodsWithAnnotation(List<Class> clazzs, Class<T> annotation) {
        List<MethodWithClass> methodList = new LinkedList<>();
        for (Class clazz: clazzs) {
            methodList.addAll(getMethodsWithAnnotation(clazz, annotation));
        }

        return methodList;
    }

    private <T extends Annotation>List<MethodWithClass> getMethodsWithAnnotation(Class clazz, Class<T> annotation) {
        List<MethodWithClass> methodList = new LinkedList<>();
        for (Method method: clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotation) && method.getAnnotation(annotation).annotationType().equals(annotation)) {
                methodList.add(new MethodWithClass(method, clazz));
            }
        }

        return methodList;
    }

    private void checkCountOfMethods(List<MethodWithClass> methodList, String step, String scenario) throws StepNotFoundException {
        if (methodList.size() == 0) {

            throw new StepNotFoundException("Step \"" + step + "\" for scenario: \"" + scenario + Constants.SCENARIO_EXTENSION + "\" not found.");
        }

        if (methodList.size() > 1) {
            Exception e = new StepNotFoundException("Step \"" + step + "\" has more than one implementation.");
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

    private List<MethodWithClass> getMethods() {
        List<MethodWithClass> pairList = null;
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
