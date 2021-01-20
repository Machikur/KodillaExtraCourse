package com.kodilla;

import net.bytebuddy.asm.Advice.OnMethodEnter;
import net.bytebuddy.asm.Advice.OnMethodExit;
import net.bytebuddy.asm.Advice.Origin;

public class MonitorClass {

    @OnMethodExit
    public static void exit(@Origin("#m") String methodName) {
        System.out.println("Exiting method " + methodName);
    }

    @OnMethodEnter
    public void enterMethod(@Origin Class<?> clazz, @Origin("#m") String methodName) {
        System.out.println("Name of method is " + methodName);
    }
}
