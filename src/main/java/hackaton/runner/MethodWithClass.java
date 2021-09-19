package hackaton.runner;

import lombok.Getter;

import java.lang.reflect.Method;

class MethodWithClass {
    @Getter
    private Method method;
    @Getter
    private Class clazz;

    MethodWithClass(Method method, Class clazz) {
        this.method = method;
        this.clazz = clazz;
    }
}
