package com.kovalenko.task;

import com.kovalenko.task.collector.Collector;
import com.kovalenko.task.collector.impl.AnimalsCollector;
import com.kovalenko.task.collector.impl.CarsCollector;
import com.kovalenko.task.collector.impl.NumbersCollector;
import com.kovalenko.task.container.Container;
import com.kovalenko.task.container.impl.AnimalsContainer;
import com.kovalenko.task.container.impl.CarsContainer;
import com.kovalenko.task.container.impl.NumbersContainer;
import com.kovalenko.task.storage.DataStorage;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertTrue;

@RunWith(Enclosed.class)
public class ApplicationTest {

    @RunWith(Parameterized.class)
    public static class ApplicationPositiveTest {

        private static final List<String> ANIMALS = asList("cow", "horse", "moose", "sheep");
        private static final List<String> NUMBERS = asList("one", "three", "two", "one", "three", "seven", "six", "six");
        private static final List<Pair<String, String>> CARS = asList(
                Pair.of("audi", DigestUtils.md5Hex("audi")),
                Pair.of("bmw", DigestUtils.md5Hex("bmw")),
                Pair.of("vw", DigestUtils.md5Hex("vw")),
                Pair.of("opel", DigestUtils.md5Hex("opel")));

        static final String CATEGORY_NUMBERS = "numbers";
        static final String CATEGORY_ANIMALS = "animals";
        static final String CATEGORY_CARS = "cars";

        private DataStorage dataStorage;
        private Application application;

        @Parameter
        public String inputFileName;
        @Parameter(1)
        public List<String> animals;
        @Parameter(2)
        public List<String> numbers;
        @Parameter(3)
        public List<Pair<String, String>> cars;

        @Parameters
        public static List<Object[]> parameters() {
            return asList(new Object[][]{
                    {"numbers_animals_cars.txt", ANIMALS, NUMBERS, CARS},
                    {"numbers_animals.txt", ANIMALS, NUMBERS, emptyList()},
                    {"numbers_cars.txt", emptyList(), NUMBERS, CARS},
                    {"animals_cars.txt", ANIMALS, emptyList(), CARS},
                    {"animals.txt", ANIMALS, emptyList(), emptyList()},
                    {"numbers.txt", emptyList(), NUMBERS, emptyList()},
                    {"cars.txt", emptyList(), emptyList(), CARS},
                    {"empty.txt", emptyList(), emptyList(), emptyList()},
            });
        }

        @Before
        public void setUp() {
            dataStorage = new DataStorage();
            application = new Application(dataStorage);
        }

        @Test
        public void test() {
            Map<String, Container> expectedResults = new LinkedHashMap<>();
            expectedResults.put(CATEGORY_ANIMALS, getExpectedContainer(animals, new AnimalsContainer()));
            expectedResults.put(CATEGORY_NUMBERS, getExpectedContainer(numbers, new NumbersContainer()));
            expectedResults.put(CATEGORY_CARS, getExpectedContainer(cars, new CarsContainer()));

            application.init(inputFileName, "categories_config_correct.properties");
            application.execute();

            assertTrue(EqualsBuilder.reflectionEquals(expectedResults, dataStorage));
        }

        private Container getExpectedContainer(List data, Container container) {
            data.forEach(container::addItem);
            return container;
        }
    }

    public static class ApplicationCollectorsInitTest {

        private DataStorage dataStorage;
        private Application application;

        @Before
        public void setUp() {
            dataStorage = new DataStorage();
            application = new Application(dataStorage);
        }

        @Test
        public void shouldReturnCategoriesCollectorsMappingIfConfigHasCorrectFormat() {
            Map<String, Collector> expectedCollectors = new HashMap<>();
            expectedCollectors.put(ApplicationPositiveTest.CATEGORY_ANIMALS, new AnimalsCollector(dataStorage));
            expectedCollectors.put(ApplicationPositiveTest.CATEGORY_NUMBERS, new NumbersCollector(dataStorage));
            expectedCollectors.put(ApplicationPositiveTest.CATEGORY_CARS, new CarsCollector(dataStorage));

            Map<String, Collector> actualCollectors = application.getCollectors("categories_config_correct.properties");

            assertTrue(EqualsBuilder.reflectionEquals(expectedCollectors, actualCollectors));
        }

        @Test
        public void shouldReturnEmptyCategoriesCollectorsMappingIfConfigHasIncorrectFormat() {
            Map<String, Collector> expectedCollectors = new HashMap<>();

            Map<String, Collector> actualCollectors = application.getCollectors("categories_config_incorrect_mapping.properties");

            assertTrue(EqualsBuilder.reflectionEquals(expectedCollectors, actualCollectors));
        }
    }
}