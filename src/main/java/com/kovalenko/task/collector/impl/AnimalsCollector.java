package com.kovalenko.task.collector.impl;

import com.kovalenko.task.collector.Collector;
import com.kovalenko.task.container.Container;
import com.kovalenko.task.container.impl.AnimalsContainer;
import com.kovalenko.task.storage.DataStorage;

public class AnimalsCollector implements Collector {

    private static final String CATEGORY = "animals";

    private Container<String> animals = new AnimalsContainer();

    public AnimalsCollector(DataStorage storage) {
        storage.put(CATEGORY, animals);
    }

    @Override
    public void collect(String item) {
        animals.addItem(item.toLowerCase());
    }
}
