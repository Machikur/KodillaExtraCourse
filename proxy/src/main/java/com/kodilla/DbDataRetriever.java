package com.kodilla;

public interface DbDataRetriever {
    int getFirstValue() throws InterruptedException;

    int getSecondValue() throws InterruptedException;

    int getThirdValue() throws InterruptedException;
}
