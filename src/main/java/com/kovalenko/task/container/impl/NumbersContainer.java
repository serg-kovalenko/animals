package com.kovalenko.task.container.impl;

import com.kovalenko.task.container.Container;
import com.kovalenko.task.output.OutputWriter;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class NumbersContainer extends HashMap<String, Integer> implements Container, OutputWriter {

    private static final String OUTPUT_FORMAT = "%s: %d";

    @Override
    public void addItem(String item) {
        this.put(item, this.getOrDefault(item, 0) + 1);
    }

    @Override
    public String toString() {
        return getFormattedData();
    }

    @Override
    public String getFormattedData() {
        return this.entrySet().stream()
                .map(this::mapToOutputLine)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String mapToOutputLine(Map.Entry<String, Integer> number) {
        return String.format(OUTPUT_FORMAT, number.getKey(), number.getValue());
    }
}
