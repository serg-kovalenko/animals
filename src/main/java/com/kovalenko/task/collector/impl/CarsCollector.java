package com.kovalenko.task.collector.impl;

import com.kovalenko.task.collector.Collector;
import com.kovalenko.task.container.Container;
import com.kovalenko.task.container.impl.CarsContainer;
import com.kovalenko.task.entity.Categories;
import com.kovalenko.task.storage.DataStorage;

public class CarsCollector implements Collector {

    private Container cars = new CarsContainer();

    public CarsCollector(DataStorage storage) {
        storage.put(Categories.CARS, cars);
    }

    @Override
    public void collect(String item) {
        cars.addItem(item.toLowerCase());
    }
}
