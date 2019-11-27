package com.kovalenko.task.container.impl;

import com.kovalenko.task.container.Container;
import com.kovalenko.task.output.OutputWriter;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class CarsContainer extends TreeMap<String, String> implements Container, OutputWriter {

    private static final String OUTPUT_FORMAT = "%s: (%s)";

    public CarsContainer() {
        super(Comparator.reverseOrder());
    }

    @Override
    public void addItem(String item) {
        this.putIfAbsent(item, DigestUtils.md5Hex(item));
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

    private String mapToOutputLine(Map.Entry<String, String> car) {
        return String.format(OUTPUT_FORMAT, car.getKey(), car.getValue());
    }
}
