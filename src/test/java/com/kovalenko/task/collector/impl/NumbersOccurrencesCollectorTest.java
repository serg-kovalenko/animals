package com.kovalenko.task.collector.impl;

import com.google.common.collect.ImmutableMap;
import com.kovalenko.task.storage.DataStorage;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NumbersOccurrencesCollectorTest {

    private static final String CATEGORY_NUMBERS = "numbers occurrences";

    private DataStorage dataStorage;

    private NumbersOccurrencesCollector collector;

    @Before
    public void setUp() {
        dataStorage = new DataStorage();
        collector = new NumbersOccurrencesCollector(dataStorage);
    }

    @Test
    public void shouldCollectNumbersAndMapValueToYesNo() {
        List<String> numbers = Arrays.asList("six: 4", "one: 3", "seven: 6", "three: 2", "two: 1");
        Map<String, String> expectedNumbers = ImmutableMap.of(
                "six", "yes",
                "one", "no",
                "seven", "yes",
                "three", "no",
                "two", "no");

        numbers.forEach(collector::collect);

        Map<String, String> actualResult = (Map<String, String>) dataStorage.get(CATEGORY_NUMBERS);

        assertEquals(expectedNumbers, actualResult);
    }

    @Test
    public void shouldDoNotAddAnythingWhenListIsEmpty() {
        List<String> numbers = Collections.emptyList();

        numbers.forEach(collector::collect);

        Map<String, Integer> actualResult = (Map<String, Integer>) dataStorage.get(CATEGORY_NUMBERS);

        assertTrue(actualResult.isEmpty());
    }

    @Test
    public void shouldReturnPairNumberYesIfValueGreaterThenThree() {
        Pair<String, String> expectedResult = Pair.of("six", "yes");

        assertEquals(expectedResult, collector.getNumberPair("six: 5"));
    }

    @Test
    public void shouldReturnPairNumberNoIfValueLessThenThree() {
        Pair<String, String> expectedResult = Pair.of("one", "no");

        assertEquals(expectedResult, collector.getNumberPair("one: 2"));
    }

    @Test
    public void shouldReturnPairNumberNoIfValueIsThree() {
        Pair<String, String> expectedResult = Pair.of("seven", "no");

        assertEquals(expectedResult, collector.getNumberPair("seven: 3"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfRowHasIncorrectFormat() {
        collector.getNumberPair("one;1");
    }

    @Test
    public void shouldReturnYesForNumberGreaterThanThree() {
        assertEquals("yes", collector.mapToYesNo("4"));
    }

    @Test
    public void shouldReturnNoForNumberThree() {
        assertEquals("no", collector.mapToYesNo("3"));
    }

    @Test
    public void shouldReturnNoForNumberLessThanThree() {
        assertEquals("no", collector.mapToYesNo("2"));
    }
}