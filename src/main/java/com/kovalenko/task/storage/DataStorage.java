package com.kovalenko.task.storage;

import com.kovalenko.task.container.Container;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class DataStorage extends LinkedHashMap<String, Container> {

    private static final String OUTPUT_FORMAT = "%S:" + System.lineSeparator() + "%s";

    @Override
    public String toString() {
        return this.entrySet().stream()
                .filter(entry -> isNotEmptyContainer(entry.getValue()))
                .map(this::mapToOutputLine)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private boolean isNotEmptyContainer(Container container) {
        return !container.isEmpty();
    }

    private String mapToOutputLine(Map.Entry<String, Container> entry) {
        return String.format(OUTPUT_FORMAT, entry.getKey(), entry.getValue());
    }
}
