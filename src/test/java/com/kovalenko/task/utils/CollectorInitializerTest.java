package com.kovalenko.task.utils;

import com.kovalenko.task.collector.Collector;
import com.kovalenko.task.collector.impl.AnimalsCollector;
import com.kovalenko.task.collector.impl.CarsCollector;
import com.kovalenko.task.collector.impl.NumbersCollector;
import com.kovalenko.task.storage.DataStorage;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Enclosed.class)
public class CollectorInitializerTest {

    private static final DataStorage DATA_STORAGE = new DataStorage();

    @RunWith(Parameterized.class)
    public static class CollectorInitializerPositiveTest {
        @Parameter
        public String className;
        @Parameter(1)
        public Collector expectedCollector;

        @Parameters
        public static List<Object[]> parameters() {
            return asList(new Object[][]{
                    {"com.kovalenko.task.collector.impl.AnimalsCollector", new AnimalsCollector(DATA_STORAGE)},
                    {"com.kovalenko.task.collector.impl.NumbersCollector", new NumbersCollector(DATA_STORAGE)},
                    {"com.kovalenko.task.collector.impl.CarsCollector", new CarsCollector(DATA_STORAGE)}
            });
        }

        @Test
        public void shouldInitCollectorIfExist() {
            Collector actualCollector = CollectorInitializer.initCollector(className, DATA_STORAGE);

            assertTrue(EqualsBuilder.reflectionEquals(expectedCollector, actualCollector));
        }
    }

    public static class CollectorInitializerNegativeTest {

        @Test(expected = IllegalArgumentException.class)
        public void shouldThrowIllegalArgumentExceptionIfCollectorIsNotExist() {
            CollectorInitializer.initCollector("com.kovalenko.task.collector.impl.UnknownCollector", DATA_STORAGE);
        }
    }
}