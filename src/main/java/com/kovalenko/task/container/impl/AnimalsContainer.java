package com.kovalenko.task.container.impl;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.kovalenko.task.container.Container;

public class AnimalsContainer implements Container<String> {

    private Set<String> container;

    public AnimalsContainer() {
        this.container = new TreeSet<>();
    }

    @Override
    public void addItem(String item) {
        this.container.add(item);
    }

    @Override
    public boolean isEmpty() {
        return this.container.isEmpty();
    }

    @Override
    public String toString() {
        return this.container.stream()
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
