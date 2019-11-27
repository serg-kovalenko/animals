package com.kovalenko.task.container.impl;

import com.google.common.collect.ImmutableMap;
import com.kovalenko.task.container.Container;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CarsContainerTest {

    private static final String AUDI = "audi";
    private static final String BMW = "bmw";
    private static final String VW = "vw";
    private static final String OPEL = "opel";

    private Container container;

    @Before
    public void setUp() {
        container = new CarsContainer();
    }

    @Test
    public void shouldAddCardAndItHashValueIfCarsIsPresent() {
        Map<String, String> expectedCars = ImmutableMap.of(
                VW, DigestUtils.md5Hex(VW),
                OPEL, DigestUtils.md5Hex(OPEL),
                BMW, DigestUtils.md5Hex(BMW),
                AUDI, DigestUtils.md5Hex(AUDI));

        expectedCars.keySet().forEach(container::addItem);

        assertEquals(expectedCars, container);
    }

    @Test
    public void shouldBeEmptyIfListIsEmpty() {
        List<String> cars = Collections.emptyList();

        cars.forEach(container::addItem);

        assertTrue(container.isEmpty());
    }

    @Test
    public void shouldReturnEachItemOnNewLineWithTheirHash() {
        Map<String, String> expectedCars = ImmutableMap.of(
                VW, DigestUtils.md5Hex(VW),
                OPEL, DigestUtils.md5Hex(OPEL),
                BMW, DigestUtils.md5Hex(BMW),
                AUDI, DigestUtils.md5Hex(AUDI));

        expectedCars.keySet().forEach(container::addItem);

        assertEquals(getFormattedData(expectedCars), container.toString());
    }

    private String getFormattedData(Map<String, String> cars) {
        return cars.entrySet().stream()
                .map(this::mapToOutputLine)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String mapToOutputLine(Map.Entry<String, String> car) {
        return String.format("%s: (%s)", car.getKey(), car.getValue());
    }
}