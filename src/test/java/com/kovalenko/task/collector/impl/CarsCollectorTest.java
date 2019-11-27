package com.kovalenko.task.collector.impl;

import com.google.common.collect.ImmutableMap;
import com.kovalenko.task.collector.Collector;
import com.kovalenko.task.entity.Categories;
import com.kovalenko.task.storage.DataStorage;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CarsCollectorTest {

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
                "vw", "7336a2c49b0045fa1340bf899f785e70",
                "opel", "f65b7d39472c52142ea2f4ea5e115d59",
                "bmw", "71913f59e458e026d6609cdb5a7cc53d",
                "audi", "4d9fa555e7c23996e99f1fb0e286aea8");

        cars.forEach(carsCollector::collect);

        Map<String, String> actualCars = (Map<String, String>) dataStorage.get(Categories.CARS);

        assertEquals(expectedCars, actualCars);
    }

    @Test
    public void shouldDoNotAddAnythingWhenListIsEmpty() {
        List<String> cars = Collections.emptyList();

        cars.forEach(carsCollector::collect);

        Map<String, String> actualResult = (Map<String, String>) dataStorage.get(Categories.CARS);

        assertTrue(actualResult.isEmpty());
    }
}