package com.kovalenko.task.container.impl;

import com.google.common.collect.ImmutableMap;
import com.kovalenko.task.container.Container;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NumbersContainerTest {

    private static final String ONE = "one";
    private static final String TWO = "two";
    private static final String SIX = "six";

    private Container container;

    @Before
    public void setUp() {
        container = new NumbersContainer();
    }

    @Test
    public void shouldContainsOnlyUniqueNumbersAndItsCountIfListContainsDuplicates() {
        List<String> numbers = Arrays.asList(ONE, TWO, SIX, SIX);

        Map<String, Integer> expectedNumbers = ImmutableMap.of(
                ONE, 1,
                TWO, 1,
                SIX, 2);

        numbers.forEach(container::addItem);

        assertEquals(expectedNumbers, container);
    }

    @Test
    public void shouldContainsOnlyUniqueNumbersAndItsCountIfListNoContainsDuplicates() {
        List<String> numbers = Arrays.asList(ONE, TWO, SIX);

        Map<String, Integer> expectedNumbers = ImmutableMap.of(
                ONE, 1,
                TWO, 1,
                SIX, 1);

        numbers.forEach(container::addItem);

        assertEquals(expectedNumbers, container);
    }

    @Test
    public void shouldBeEmptyIfListIsEmpty() {
        List<String> numbers = Collections.emptyList();

        numbers.forEach(container::addItem);

        assertTrue(container.isEmpty());
    }
}