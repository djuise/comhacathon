package com.hackathon.runner;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

class Step {
    @Getter @Setter
    private String name;
    @Getter
    private List<String> stringVars = new LinkedList<>();
    @Getter
    private List<Double> doubleVars = new LinkedList<>();
    @Getter
    private List<Integer> intVars = new LinkedList<>();
    @Getter @Setter
    private Method method;
    @Getter @Setter
    private Class clazz;

    void addStringVar(String var) {
        stringVars.add(var);
    }

    void addDoubleVar(double var) {
        doubleVars.add(var);
    }

    void addIntegerVar(int var) {
        intVars.add(var);
    }
}
