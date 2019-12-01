package com.kovalenko.task.container.impl;

import com.kovalenko.task.container.Container;
import org.apache.commons.lang3.tuple.Pair;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class NumbersOccurrencesContainer extends LinkedHashMap<String, String> implements Container<Pair<String, String>> {

    private static final String OUTPUT_FORMAT = "%s: %s";

    @Override
    public void addItem(Pair<String, String> item) {
        this.put(item.getKey(), item.getValue());
    }

    @Override
    public String toString() {
        return this.entrySet().stream()
                .map(this::mapToOutputLine)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String mapToOutputLine(Map.Entry<String, String> number) {
        return String.format(OUTPUT_FORMAT, number.getKey(), number.getValue());
    }
}
