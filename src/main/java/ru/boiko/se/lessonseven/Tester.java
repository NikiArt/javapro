package ru.boiko.se.lessonseven;

import lombok.SneakyThrows;
import ru.boiko.se.lessonseven.annotations.MethodTestAnno;
import ru.boiko.se.lessonseven.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tester {
    final List<Method> beforeSuiteMethod = new ArrayList<>();
    final List<Method> afterSuiteMethod = new ArrayList<>();
    final List<MethodTestAnno> testList = new ArrayList<>();
    private int passed = 0;
    private int failed = 0;
    private Class testClass;
    private Object instance;

    @SneakyThrows
    public void start(final Class testClass){
        this.testClass = testClass;
        Constructor constructor = testClass.getConstructor();
        instance = constructor.newInstance();

        final Method[] methods = testClass.getDeclaredMethods();


        for (Method method : methods) {
            final Annotation[] annotations = method.getDeclaredAnnotations();
            fillLists(method, annotations);
        }
        Collections.sort(testList);


        if (beforeSuiteMethod.size() > 1 || afterSuiteMethod.size() > 1) {
            final String currentException = "Аннотация " + ((beforeSuiteMethod.size() > 1) ? "@beforeSuite " : "@afterSuite ") + "встречается более одного раза";
            throw new RuntimeException(currentException);
        }

        if (beforeSuiteMethod.size() > 0) performMethod(beforeSuiteMethod.get(0));

        for (MethodTestAnno methodTestAnno : testList) {
            performMethod(methodTestAnno.getMethod());
        }

        if (afterSuiteMethod.size() > 0) performMethod(afterSuiteMethod.get(0));

        System.out.println("Тестов пройдено успешно - " + passed);
        System.out.println("Тестов провалено - " + failed);

    }

    private void fillLists(final Method method, final Annotation[] annotations) {
        for (Annotation annotation : annotations) {
            System.out.println(annotation.toString());
            switch (annotation.annotationType().getSimpleName()) {
                case "BeforeSuite":
                    beforeSuiteMethod.add(method);
                    break;
                case "AfterSuite":
                    afterSuiteMethod.add(method);
                    break;
                case "Test":
                    testList.add(new MethodTestAnno(method.getAnnotation(Test.class).priority(), method));
                    break;
            }
        }
    }

    private void performMethod(Method method) {
        try {
            method.invoke(instance);
            System.out.println("test passed");
            passed++;
        } catch (Exception e) {
            System.out.println("test failed");
            failed++;
        }
    }
}
