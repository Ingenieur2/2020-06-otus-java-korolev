package ru.package01;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;


class Ioc {

    public List Ioc() {
        Class<?> classString = TestLogging.class;
        Method[] methodsAllClass = classString.getDeclaredMethods();

        List<Method> logListClass = new ArrayList<>();          //creating list of methods with @Log annotation
        for (int i = 0; i < methodsAllClass.length; i++) {
            if (methodsAllClass[i].isAnnotationPresent(Log.class)) {
                logListClass.add(methodsAllClass[i]);
            }
        }
        System.out.println("-------------");
        return logListClass;
    }

    static MyClassInterface createMyClass() {
        int param1 = 0;
        int param2 = 0;

        InvocationHandler handler = new DemoInvocationHandler(new TestLogging(param1, param2));
        var proxiedHandler = (MyClassInterface) Proxy.newProxyInstance(Ioc.class.getClassLoader(),
                new Class<?>[]{MyClassInterface.class},
                handler);
        return proxiedHandler;
    }
}