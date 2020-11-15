package ru.package01.appcontainer;

import ru.package01.appcontainer.api.AppComponent;
import ru.package01.appcontainer.api.AppComponentsContainer;
import ru.package01.appcontainer.api.AppComponentsContainerConfig;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByName = new HashMap<>();

    public AppComponentsContainerImpl(Class<?> initialConfigClass) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        processConfig(initialConfigClass);
    }

    private void processConfig(Class<?> configClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        checkConfigClass(configClass);

        Method[] methods = configClass.getDeclaredMethods();
        List<Method> methodsInAppConfig = new ArrayList<>();

        for (int i = 0; i < methods.length; i++) {
            if (methods[i].isAnnotationPresent(AppComponent.class)) {
                methodsInAppConfig.add(methods[i]);
            }
        }
        methodsInAppConfig.sort(Comparator.comparing(method -> method.getAnnotation(AppComponent.class).order()));

        for (int i = 0; i < methodsInAppConfig.size(); i++) {
            Object[] arg = getObjectToParameters(methodsInAppConfig.get(i).getParameters());
                Object appComponent = methodsInAppConfig.get(i).invoke(configClass.getDeclaredConstructor().newInstance(), arg);
                appComponents.add(appComponent);
                appComponentsByName.put(methodsInAppConfig.get(i).getAnnotation(AppComponent.class).name(), appComponent);
        }
    }

    private Object[] getObjectToParameters(Parameter[] methodParameters) throws NullPointerException {
        List<Object> arg = new ArrayList<>();
        for (int i = 0; i < methodParameters.length; i++) {
            arg.add(getObjectFromComponent(methodParameters[i].getType()).get());
        }
        return arg.toArray();
    }

    private Optional<Object> getObjectFromComponent(Class<?> clazz) {
        return appComponents.stream()
                .filter(obj -> clazz.isAssignableFrom(obj.getClass()))
                .findFirst();
    }

    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
        }
    }

    @Override
    public <C> C getAppComponent(Class<C> componentClass) {
        for (int i = 0; i < appComponents.size(); i++) {
            if (componentClass.isAssignableFrom(appComponents.get(i).getClass())) {
                return (C) appComponents.get(i);
            }
        }
        throw new RuntimeException(String.format("Error of getting AppComponent from Class: %s", componentClass));
    }

    @Override
    public <C> C getAppComponent(String componentName) {
        if (appComponentsByName.get(componentName) == null) {
            throw new RuntimeException(String.format("Error of getting AppComponent from String: %s", componentName));
        }
        return (C) appComponentsByName.get(componentName);
    }
}
