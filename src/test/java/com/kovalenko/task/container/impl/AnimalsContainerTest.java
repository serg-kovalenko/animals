package com.kovalenko.task.container.impl;

import com.kovalenko.task.container.Container;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AnimalsContainerTest {

    private static final String COW = "cow";
    private static final String HORSE = "horse";
    private static final String SHEEP = "sheep";

    private Container container;

    @Before
    public void setUp() {
        container = new AnimalsContainer();
    }

    @Test
    public void shouldContainsOnlyUniqueAnimalsIfListContainsDuplicates() {
        List<String> animals = Arrays.asList(COW, HORSE, SHEEP, HORSE);
        TreeSet<String> expectedAnimals = new TreeSet<>(animals);

        animals.forEach(container::addItem);

        assertEquals(expectedAnimals, container);
    }

    @Test
    public void shouldBeEmptyIfListIsEmpty() {
        List<String> animals = Collections.emptyList();

        animals.forEach(container::addItem);

        assertTrue(container.isEmpty());
    }

    @Test
    public void shouldReturnEachItemOnNewLine() {
        String expectedFormattedData = COW + System.lineSeparator() + HORSE + System.lineSeparator() + SHEEP;
        TreeSet<String> animals = new TreeSet<>(Arrays.asList(COW, HORSE, SHEEP));

        animals.forEach(container::addItem);

        assertEquals(expectedFormattedData, container.toString());
    }
}