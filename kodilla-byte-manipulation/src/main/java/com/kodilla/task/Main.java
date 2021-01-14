package com.kodilla.task;

import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        Student student = new Student();
        Field indexField = Student.class.getDeclaredField("indexNumber");
        indexField.setAccessible(true);
        String indexNumber = (String) indexField.get(student);
        System.out.println(indexNumber);
    }
}
