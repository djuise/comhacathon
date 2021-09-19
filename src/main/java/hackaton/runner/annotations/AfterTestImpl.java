package hackaton.runner.annotations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AfterTestImpl {
    public static void tearDown(Class clazz) {
        for(Method method: clazz.getSuperclass().getMethods()) {
            if (method.isAnnotationPresent(AfterTest.class)) {
                try {
                    method.invoke(clazz.newInstance());
                } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
