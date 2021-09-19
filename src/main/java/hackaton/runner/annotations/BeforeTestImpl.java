package hackaton.runner.annotations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BeforeTestImpl {
    public static void setUp(Class clazz) {
        for(Method method: clazz.getSuperclass().getMethods()) {
            if (method.isAnnotationPresent(BeforeTest.class)) {
                try {
                    method.invoke(clazz.newInstance());
                } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
