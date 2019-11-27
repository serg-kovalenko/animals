package com.kovalenko.task.collector.impl;

import com.google.common.collect.Sets;
import com.kovalenko.task.collector.Collector;
import com.kovalenko.task.entity.Categories;
import com.kovalenko.task.storage.DataStorage;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AnimalsCollectorTest {

    private DataStorage dataStorage;

    private Collector animalsCollector;

    @Before
    public void setUp() {
        dataStorage = new DataStorage();
        animalsCollector = new AnimalsCollector(dataStorage);
    }

    @Test
    public void shouldCollectOnlyUniqueAnimalsWhenListContainsDuplicates() {
        List<String> animals = Arrays.asList("sheep", "horse", "cow", "Horse", "moose");
        Set<String> expectedAnimals = Sets.newHashSet("cow", "horse", "moose", "sheep");

        animals.forEach(animalsCollector::collect);

        Set<String> actualResult = (Set<String>) dataStorage.get(Categories.ANIMALS);

        assertEquals(expectedAnimals, actualResult);
        assertEquals(4, actualResult.size());
    }

    @Test
    public void shouldDoNotAddAnythingWhenListIsEmpty() {
        List<String> animals = Collections.emptyList();

        animals.forEach(animalsCollector::collect);

        Set<String> actualResult = (Set<String>) dataStorage.get(Categories.ANIMALS);

        assertTrue(actualResult.isEmpty());
    }
}