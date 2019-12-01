package com.kovalenko.task.container.impl;

import com.google.common.collect.ImmutableMap;
import com.kovalenko.task.container.Container;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CarsContainerTest {

    private static final Pair<String, String> AUDI = Pair.of("audi", DigestUtils.md5Hex("audi"));
    private static final Pair<String, String> BMW = Pair.of("bmw", DigestUtils.md5Hex("bmw"));
    private static final Pair<String, String> VW = Pair.of("vw", DigestUtils.md5Hex("vw"));
    private static final Pair<String, String> OPEL = Pair.of("opel", DigestUtils.md5Hex("opel"));

    private Container<Pair<String, String>> container;

    @Before
    public void setUp() {
        container = new CarsContainer();
    }

    @Test
    public void shouldAddCardAndItHashValueIfCarsIsPresent() {
        List<Pair<String, String>> actualCars = Arrays.asList(VW, OPEL, BMW, AUDI);

        Map<String, String> expectedCars = ImmutableMap.of(
                VW.getKey(), VW.getValue(),
                OPEL.getKey(), OPEL.getValue(),
                BMW.getKey(), BMW.getValue(),
                AUDI.getKey(), AUDI.getValue());

        actualCars.forEach(container::addItem);

        assertEquals(expectedCars, container);
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

    @Test
    public void shouldBeEmptyIfListIsEmpty() {
        List<Pair<String, String>> cars = Collections.emptyList();

        cars.forEach(container::addItem);

        assertTrue(container.isEmpty());
    }
}