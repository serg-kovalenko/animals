package com.kovalenko.task.container.impl;

import com.kovalenko.task.container.Container;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class NumbersContainer extends LinkedHashMap<String, Integer> implements Container<String> {

    private static final String OUTPUT_FORMAT = "%s: %d";

    @Override
    public void addItem(String item) {
        this.put(item, this.getOrDefault(item, 0) + 1);
    }

    @Override
    public String toString() {
        return this.entrySet().stream()
                .map(this::mapToOutputLine)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String mapToOutputLine(Map.Entry<String, Integer> number) {
        return String.format(OUTPUT_FORMAT, number.getKey(), number.getValue());
    }
}
