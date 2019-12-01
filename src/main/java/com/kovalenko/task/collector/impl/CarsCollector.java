package com.kovalenko.task.collector.impl;

import com.kovalenko.task.collector.Collector;
import com.kovalenko.task.container.Container;
import com.kovalenko.task.container.impl.CarsContainer;
import com.kovalenko.task.storage.DataStorage;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.tuple.Pair;

public class CarsCollector implements Collector {

    private static final String CATEGORY = "cars";

    private Container<Pair<String, String>> cars = new CarsContainer();

    public CarsCollector(DataStorage storage) {
        storage.put(CATEGORY, cars);
    }

    @Override
    public void collect(String item) {
        String lowerCaseCar = item.toLowerCase();
        cars.addItem(Pair.of(lowerCaseCar, DigestUtils.md5Hex(lowerCaseCar)));
    }
}
