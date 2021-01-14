package com.core;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class CoreApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfiguration.class);
        context.refresh();

        LibraryManager libraryManager = context.getBean(LibraryManager.class);
        System.out.println("Context and beans are set up and ready to work");
        System.out.println(CoreApplication.class.getClassLoader());
        System.out.println(Throwable.class.getClassLoader());
        context.close();
    }

}
