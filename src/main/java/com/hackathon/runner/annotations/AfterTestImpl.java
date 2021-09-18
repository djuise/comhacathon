package com.hackathon.runner.annotations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AfterTestImpl {

    public static void tearDown(Class clazz) throws InvocationTargetException, IllegalAccessException {
//        for (Method method: clazz.getMethods()) {
//            if (method.isAnnotationPresent(AfterTest.class)) {
//                method.invoke(obj);
//            }
//        }
        Class o = clazz.getSuperclass();
        for (Method method: o.getDeclaredMethods()) {
            if (method.isAnnotationPresent(AfterTest.class)) {
                method.invoke(o);
            }
        }
    }
}
