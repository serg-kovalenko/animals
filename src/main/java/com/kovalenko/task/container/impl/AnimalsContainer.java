package com.kovalenko.task.container.impl;

import com.kovalenko.task.container.Container;

import java.util.TreeSet;
import java.util.stream.Collectors;

public class AnimalsContainer extends TreeSet<String> implements Container<String> {

    @Override
    public void addItem(String item) {
        this.add(item);
    }

    @Override
    public String toString() {
        return this.stream()
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
