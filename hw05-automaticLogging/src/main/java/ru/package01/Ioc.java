package ru.package01;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;


class Ioc {

    private Ioc() {
    }

    static MyClassInterface createMyClass() {
        int param1 = 0;
        int param2 = 0;
        InvocationHandler handler = new DemoInvocationHandler(new TestLogging(param1, param2));
        var proxiedHandler = (MyClassInterface) Proxy.newProxyInstance(Ioc.class.getClassLoader(), new Class<?>[]{MyClassInterface.class}, handler);
        return proxiedHandler;
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final MyClassInterface myClass;

        DemoInvocationHandler(MyClassInterface myClass) {
            this.myClass = myClass;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Class<?> classString = TestLogging.class;
            Method[] methodsAllClass = classString.getDeclaredMethods();

            List<Method> LogListClass = new ArrayList<>();          //creating list of methods with @Log annotation
            for (int i = 0; i < methodsAllClass.length; i++) {
                if (methodsAllClass[i].isAnnotationPresent(Log.class)) {
                    LogListClass.add(methodsAllClass[i]);
                }
            }
            System.out.println("-------------");
            System.out.println(LogListClass);
            System.out.println("calling method: " + method.getName());
            for (int i = 0; i < LogListClass.size(); i++) {
                if (LogListClass.get(i).getName().contentEquals(method.getName())) {  //checking method IS annotated with @Log
                    System.out.println("Invoking method with logging: " + method.getName());
                    for (int j = 0; j < args.length; j++) {
                        args[j] = (Object) (((int) args[j]) * 2);         //changing param, multiplying *2
                        System.out.println("parameter[" + j + "] = " + args[j].toString());

                    }
                    return method.invoke(myClass, args);
                } else {
                    System.out.println("Calling the same method: " + method.getName());
                    for (int j = 0; j < args.length; j++) {
                        System.out.println("parameter[" + j + "] = " + args[j].toString());
                    }
                    return method.invoke(myClass, args);
                }
            }
            return null;
        }
    }
}