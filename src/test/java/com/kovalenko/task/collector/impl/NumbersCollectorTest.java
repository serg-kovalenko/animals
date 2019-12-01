package com.kovalenko.task.collector.impl;

import com.google.common.collect.ImmutableMap;
import com.kovalenko.task.collector.Collector;
import com.kovalenko.task.storage.DataStorage;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NumbersCollectorTest {

    private static final String CATEGORY_NUMBERS = "numbers";

    private DataStorage dataStorage;

    private Collector numbersCollector;

    @Before
    public void setUp() {
        dataStorage = new DataStorage();
        numbersCollector = new NumbersCollector(dataStorage);
    }

    @Test
    public void shouldCollectOnlyUniqueNumbersWhenListContainsDuplicates() {
        List<String> numbers = Arrays.asList("one", "three", "two", "one", "three", "seven", "six", "six");
        Map<String, Integer> expectedNumbers = ImmutableMap.of(
                "one", 2,
                "three", 2,
                "two", 1,
                "seven", 1,
                "six", 2);

        numbers.forEach(numbersCollector::collect);

        Map<String, Integer> actualResult = (Map<String, Integer>) dataStorage.get(CATEGORY_NUMBERS);

        assertEquals(expectedNumbers, actualResult);
        assertEquals(5, actualResult.size());
    }

    @Test
    public void shouldDoNotAddAnythingWhenListIsEmpty() {
        List<String> numbers = Collections.emptyList();

        numbers.forEach(numbersCollector::collect);

        Map<String, Integer> actualResult = (Map<String, Integer>) dataStorage.get(CATEGORY_NUMBERS);

        assertTrue(actualResult.isEmpty());
    }
}