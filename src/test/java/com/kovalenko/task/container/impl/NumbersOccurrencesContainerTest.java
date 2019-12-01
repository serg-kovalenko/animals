package com.kovalenko.task.container.impl;

import com.google.common.collect.ImmutableMap;
import com.kovalenko.task.container.Container;
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

public class NumbersOccurrencesContainerTest {

    private static final Pair<String, String> ONE = Pair.of("one", "yes");
    private static final Pair<String, String> TWO = Pair.of("two", "no");
    private static final Pair<String, String> SIX = Pair.of("six", "yes");

    private Container<Pair<String, String>> container;

    @Before
    public void setUp() {
        container = new NumbersOccurrencesContainer();
    }

    @Test
    public void name() {
        List<Pair<String, String>> numbers = Arrays.asList(ONE, TWO, SIX);

        Map<String, String> expectedNumbers = ImmutableMap.of(
                ONE.getKey(), ONE.getValue(),
                TWO.getKey(), TWO.getValue(),
                SIX.getKey(), SIX.getValue()
        );

        numbers.forEach(container::addItem);

        assertEquals(expectedNumbers, container);
        assertEquals(getFormatterData(expectedNumbers), container.toString());
    }

    private String getFormatterData(Map<String, String> numbers) {
        return numbers.entrySet().stream()
                .map(this::mapToOutputLine)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String mapToOutputLine(Map.Entry<String, String> number) {
        return String.format("%s: %s", number.getKey(), number.getValue());
    }

    @Test
    public void shouldBeEmptyIfListIsEmpty() {
        List<Pair<String, String>> numbers = Collections.emptyList();

        numbers.forEach(container::addItem);

        assertTrue(container.isEmpty());
    }
}