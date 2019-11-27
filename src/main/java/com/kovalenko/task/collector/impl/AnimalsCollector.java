package com.kovalenko.task.collector.impl;

import com.kovalenko.task.entity.Categories;
import com.kovalenko.task.collector.Collector;
import com.kovalenko.task.container.Container;
import com.kovalenko.task.container.impl.AnimalsContainer;
import com.kovalenko.task.storage.DataStorage;

public class AnimalsCollector implements Collector {

    private Container animals = new AnimalsContainer();

    public AnimalsCollector(DataStorage storage) {
        storage.put(Categories.ANIMALS, animals);
    }

    @Override
    public void collect(String item) {
        animals.addItem(item.toLowerCase());
    }
}
