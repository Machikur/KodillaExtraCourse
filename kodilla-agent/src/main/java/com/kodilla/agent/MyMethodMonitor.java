package com.kodilla.agent;

import net.bytebuddy.asm.Advice.OnMethodEnter;
import net.bytebuddy.asm.Advice.OnMethodExit;
import net.bytebuddy.asm.Advice.Origin;

public class MyMethodMonitor {
    @OnMethodEnter
    public static void enter(@Origin Class clazz, @Origin("#m") String methodName) {
        System.out.println("Entering method: " + methodName);
    }

    @OnMethodExit
    public static void exit() {
        System.out.println("Exiting method.");
    }
}
