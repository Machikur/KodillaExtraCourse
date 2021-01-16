package com.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.logging.Level;
import java.util.logging.Logger;


public class BeanMonitor implements BeanPostProcessor {

    private final Logger LOGGER = Logger.getLogger(BeanMonitor.class.getName());

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws
            BeansException {

        System.out.println("Before initialization of bean: " + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        LOGGER.log(Level.INFO, String.format("bean: %s is already created ", beanName));
        return bean;
    }

}
