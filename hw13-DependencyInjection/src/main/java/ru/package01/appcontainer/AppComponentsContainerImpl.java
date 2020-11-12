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

    public AppComponentsContainerImpl(Class<?> initialConfigClass) throws NoSuchMethodException {
        processConfig(initialConfigClass);
    }

    private void processConfig(Class<?> configClass) throws NoSuchMethodException {
        checkConfigClass(configClass);

        Method[] methods = configClass.getDeclaredMethods();
        List<Method> methodsInAppConfig = new ArrayList<>();

        for (int i = 0; i < methods.length; i++) {
            if (configClass.getMethod(methods[i].getName(), methods[i].getParameterTypes()).isAnnotationPresent(AppComponent.class)) {
                methodsInAppConfig.add(methods[i]);
            }
        }

        methodsInAppConfig.sort(Comparator.comparing(method -> method.getAnnotation(AppComponent.class).order()));

        for (int i = 0; i < methodsInAppConfig.size(); i++) {
            Object[] arg = getObjectToParameters(methodsInAppConfig.get(i).getParameters());
            try {
                Object appComponent = methodsInAppConfig.get(i).invoke(configClass.getDeclaredConstructor().newInstance(), arg);
                appComponents.add(appComponent);
                appComponentsByName.put(methodsInAppConfig.get(i).getAnnotation(AppComponent.class).name(), appComponent);
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    private Object[] getObjectToParameters(Parameter[] methodParameters) {
        List<Object> arg = new ArrayList<>();
        for (int i = 0; i < methodParameters.length; i++) {
            Optional<Object> objectInComponent = getObjectFromComponent(methodParameters[i].getType());
            objectInComponent.ifPresent(arg::add);
        }
        return arg.toArray();
    }

    private Optional<Object> getObjectFromComponent(Class<?> classString) {
        return appComponents.stream()
                .filter(obj -> obj.getClass().equals(classString)
                        || (classString.isInterface()
                        && classString.isAssignableFrom(obj.getClass())))
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
        return null;
    }

    @Override
    public <C> C getAppComponent(String componentName) {
        return (C) Optional.ofNullable(appComponentsByName.get(componentName)).get();
    }
}
