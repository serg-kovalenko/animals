package com.kovalenko.task.storage;

import com.kovalenko.task.container.Container;
import com.kovalenko.task.entity.Categories;
import com.kovalenko.task.output.OutputWriter;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class DataStorage extends LinkedHashMap<Categories, Container> implements OutputWriter {

    private static final String OUTPUT_FORMAT = "%s:" + System.lineSeparator() + "%s";

    @Override
    public String toString() {
        return getFormattedData();
    }

    @Override
    public String getFormattedData() {
        return this.entrySet().stream()
                .filter(entry -> isNotEmptyContainer(entry.getValue()))
                .map(this::mapToOutputLine)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private boolean isNotEmptyContainer(Container container) {
        return !container.isEmpty();
    }

    private String mapToOutputLine(Map.Entry<Categories, Container> entry) {
        return String.format(OUTPUT_FORMAT, entry.getKey(), entry.getValue());
    }
}
