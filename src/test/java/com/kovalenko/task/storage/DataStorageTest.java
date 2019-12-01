package com.kovalenko.task.storage;

import com.kovalenko.task.container.Container;
import com.kovalenko.task.container.impl.AnimalsContainer;
import com.kovalenko.task.container.impl.NumbersContainer;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DataStorageTest {

    private static final String OUTPUT_FORMAT = "%S:" + System.lineSeparator() + "%s";

    private static final String CATEGORY_ANIMALS = "animals";
    private static final String CATEGORY_NUMBERS = "numbers";

    private DataStorage dataStorage;

    @Before
    public void setUp() {
        dataStorage = new DataStorage();
    }

    @Test
    public void shouldOutputCategoryInUpperCaseAndItItemsBelow() {
        Container<String> animalsContainer = new AnimalsContainer();
        animalsContainer.addItem("cow");
        animalsContainer.addItem("horse");

        dataStorage.put(CATEGORY_ANIMALS, animalsContainer);

        assertEquals(getOutputString(CATEGORY_ANIMALS, animalsContainer), dataStorage.toString());
    }

    @Test
    public void shouldOutputCategoryInUpperCaseAndItItemsBelowWithoutEmptyCategory() {
        Container<String> animalsContainer = new AnimalsContainer();
        animalsContainer.addItem("cow");
        animalsContainer.addItem("horse");

        dataStorage.put(CATEGORY_ANIMALS, animalsContainer);
        dataStorage.put(CATEGORY_NUMBERS, new NumbersContainer());

        assertEquals(getOutputString(CATEGORY_ANIMALS, animalsContainer), dataStorage.toString());
    }

    @Test
    public void name() {
        Container<String> animalsContainer = new AnimalsContainer();
        animalsContainer.addItem("cow");

        Container<String> numbersContainer = new NumbersContainer();
        numbersContainer.addItem("one");

        dataStorage.put(CATEGORY_ANIMALS, animalsContainer);
        dataStorage.put(CATEGORY_NUMBERS, numbersContainer);

        String expectedOutput = getOutputString(CATEGORY_ANIMALS, animalsContainer) + System.lineSeparator() + getOutputString(CATEGORY_NUMBERS, numbersContainer);

        assertEquals(expectedOutput, dataStorage.toString());
    }

    @Test
    public void shouldOutputNothingIfContainerIsEmpty() {
        dataStorage.put(CATEGORY_ANIMALS, new AnimalsContainer());

        assertEquals(StringUtils.EMPTY, dataStorage.toString());
    }

    @Test
    public void shouldOutputNothingIfStorageIsEmpty() {
        assertEquals(StringUtils.EMPTY, dataStorage.toString());
    }

    private String getOutputString(String category, Container container) {
        return String.format(OUTPUT_FORMAT, category, container);
    }
}