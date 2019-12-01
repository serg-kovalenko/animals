package com.kovalenko.task.collector.impl;

import com.google.common.collect.ImmutableMap;
import com.kovalenko.task.collector.Collector;
import com.kovalenko.task.storage.DataStorage;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CarsCollectorTest {

    private static final String CATEGORY_CARS = "cars";

    private DataStorage dataStorage;

    private Collector carsCollector;

    @Before
    public void setUp() {
        dataStorage = new DataStorage();
        carsCollector = new CarsCollector(dataStorage);
    }

    @Test
    public void shouldCollectOnlyUniqueCarsWhenListContainsDuplicates() {
        List<String> cars = Arrays.asList("AUDI", "BMW", "Audi", "VW", "OPEL", "Opel");
        Map<String, String> expectedCars = ImmutableMap.of(
                "vw", DigestUtils.md5Hex("vw"),
                "opel", DigestUtils.md5Hex("opel"),
                "bmw", DigestUtils.md5Hex("bmw"),
                "audi", DigestUtils.md5Hex("audi"));

        cars.forEach(carsCollector::collect);

        Map<String, String> actualCars = (Map<String, String>) dataStorage.get(CATEGORY_CARS);

        assertEquals(expectedCars, actualCars);
    }

    @Test
    public void shouldDoNotAddAnythingWhenListIsEmpty() {
        List<String> cars = Collections.emptyList();

        cars.forEach(carsCollector::collect);

        Map<String, String> actualResult = (Map<String, String>) dataStorage.get(CATEGORY_CARS);

        assertTrue(actualResult.isEmpty());
    }
}