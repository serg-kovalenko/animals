package com.kovalenko.task;

import com.kovalenko.task.container.Container;
import com.kovalenko.task.container.impl.AnimalsContainer;
import com.kovalenko.task.container.impl.CarsContainer;
import com.kovalenko.task.container.impl.NumbersContainer;
import com.kovalenko.task.entity.Categories;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ApplicationTest {

    private Application application;

    private static final List<String> ANIMALS = asList("cow", "horse", "moose", "sheep");
    private static final List<String> NUMBERS = asList("one", "three", "two", "one", "three", "seven", "six", "six");
    private static final List<String> CARS = asList("audi", "bmw", "vw", "opel");

    @Parameter
    public String inputFileName;
    @Parameter(1)
    public List<String> animals;
    @Parameter(2)
    public List<String> numbers;
    @Parameter(3)
    public List<String> cars;

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
        application = new Application();
    }

    @Test
    public void test() {
        Map<Categories, Container> expectedResults = new LinkedHashMap<>();
        expectedResults.put(Categories.ANIMALS, getExpectedContainer(animals, new AnimalsContainer()));
        expectedResults.put(Categories.NUMBERS, getExpectedContainer(numbers, new NumbersContainer()));
        expectedResults.put(Categories.CARS, getExpectedContainer(cars, new CarsContainer()));

        application.init(inputFileName);
        application.execute();

        assertEquals(expectedResults, application.getDataStorage());
    }

    private Container getExpectedContainer(List<String> data, Container container) {
        data.forEach(container::addItem);
        return container;
    }
}