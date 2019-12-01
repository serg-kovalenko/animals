package com.kovalenko.task.container.impl;

import com.kovalenko.task.container.Container;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class CarsContainer extends TreeMap<String, String> implements Container<Pair<String, String>> {

    private static final String OUTPUT_FORMAT = "%s: (%s)";

    public CarsContainer() {
        super(Comparator.reverseOrder());
    }

    @Override
    public void addItem(Pair<String, String> car) {
        this.putIfAbsent(car.getKey(), car.getValue());
    }

    @Override
    public String toString() {
        return this.entrySet().stream()
                .map(this::mapToOutputLine)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String mapToOutputLine(Map.Entry<String, String> car) {
        return String.format(OUTPUT_FORMAT, car.getKey(), car.getValue());
    }
}
