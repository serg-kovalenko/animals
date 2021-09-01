package com.kovalenko.task.container.impl;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.kovalenko.task.container.Container;

public class NumbersContainer implements Container<String> {

    private static final String OUTPUT_FORMAT = "%s: %d";

    private Map<String, Integer> container;

    public NumbersContainer() {
        this.container = new LinkedHashMap<>();
    }

    @Override
    public void addItem(String item) {
        this.container.put(item, this.container.getOrDefault(item, 0) + 1);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public String toString() {
        return this.container.entrySet().stream()
                .map(this::mapToOutputLine)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String mapToOutputLine(Map.Entry<String, Integer> number) {
        return String.format(OUTPUT_FORMAT, number.getKey(), number.getValue());
    }
}
