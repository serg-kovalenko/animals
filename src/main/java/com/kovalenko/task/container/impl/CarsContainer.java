package com.kovalenko.task.container.impl;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;

import com.kovalenko.task.container.Container;

public class CarsContainer implements Container<Pair<String, String>> {

    private Map<String, String> container;

    private static final String OUTPUT_FORMAT = "%s: (%s)";

    public CarsContainer() {
        this.container = new TreeMap<>(Comparator.reverseOrder());
    }

    @Override
    public void addItem(Pair<String, String> car) {
        this.container.putIfAbsent(car.getKey(), car.getValue());
    }

    @Override
    public boolean isEmpty() {
        return this.container.isEmpty();
    }

    @Override
    public String toString() {
        return this.container.entrySet().stream()
                .map(this::mapToOutputLine)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String mapToOutputLine(Map.Entry<String, String> car) {
        return String.format(OUTPUT_FORMAT, car.getKey(), car.getValue());
    }
}
