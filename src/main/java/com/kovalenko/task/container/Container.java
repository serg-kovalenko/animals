package com.kovalenko.task.container;

public interface Container<T> {

    void addItem(T item);

    boolean isEmpty();
}
