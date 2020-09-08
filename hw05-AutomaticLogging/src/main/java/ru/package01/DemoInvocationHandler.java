package ru.package01;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DemoInvocationHandler implements InvocationHandler {
    private final MyClassInterface myClass;

    DemoInvocationHandler(MyClassInterface myClass) {
        this.myClass = myClass;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        var logListClass = new Ioc();

        System.out.println("Does " + method.getName() + "-method have an annotation @Log?  " + logListClass.Ioc().toString().contains(method.getName()));
        if (logListClass.Ioc().toString().contains(method.getName())) {
            System.out.println("Invoking method with logging: " + method.getName());
            for (int j = 0; j < args.length; j++) {
                args[j] = (Object) (((int) args[j]) * 2);         //changing param, multiplying *2
                System.out.println("parameter[" + j + "] = " + args[j].toString());
            }
        } else {
            System.out.println("Calling the same method: " + method.getName());
            for (int j = 0; j < args.length; j++) {
                System.out.println("parameter[" + j + "] = " + args[j].toString());
            }
        }
        return method.invoke(myClass, args);
    }
}
