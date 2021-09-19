package com.hackathon.runner;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;

public class MethodWithClass {
    @Getter
    private Method method;
    @Getter
    private Class clazz;

    MethodWithClass(Method method, Class clazz) {
        this.method = method;
        this.clazz = clazz;
    }
}
