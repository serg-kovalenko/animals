package com.kovalenko.task.collector.impl;

import com.kovalenko.task.collector.Collector;
import com.kovalenko.task.container.Container;
import com.kovalenko.task.container.impl.NumbersContainer;
import com.kovalenko.task.storage.DataStorage;

public class NumbersCollector implements Collector {

    private static final String CATEGORY = "numbers";

    private Container<String> numbers = new NumbersContainer();

    public NumbersCollector(DataStorage storage) {
        storage.put(CATEGORY, numbers);
    }

    @Override
    public void collect(String item) {
        numbers.addItem(item.toLowerCase());
    }
}
