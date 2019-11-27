package com.kovalenko.task.collector.impl;

import com.kovalenko.task.collector.Collector;
import com.kovalenko.task.container.Container;
import com.kovalenko.task.container.impl.NumbersContainer;
import com.kovalenko.task.entity.Categories;
import com.kovalenko.task.storage.DataStorage;

public class NumbersCollector implements Collector {

    private Container numbers = new NumbersContainer();

    public NumbersCollector(DataStorage storage) {
        storage.put(Categories.NUMBERS, numbers);
    }

    @Override
    public void collect(String item) {
        numbers.addItem(item.toLowerCase());
    }
}
