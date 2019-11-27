package com.kovalenko.task;

import com.google.common.collect.ImmutableMap;
import com.kovalenko.task.collector.Collector;
import com.kovalenko.task.collector.impl.AnimalsCollector;
import com.kovalenko.task.collector.impl.CarsCollector;
import com.kovalenko.task.collector.impl.NumbersCollector;
import com.kovalenko.task.entity.Categories;
import com.kovalenko.task.storage.DataStorage;
import com.kovalenko.task.utils.FileReader;

import java.util.List;
import java.util.Map;

public class Application {

    private final DataStorage dataStorage = new DataStorage();

    private final Map<String, Collector> collectors = ImmutableMap.of(
            Categories.ANIMALS.getValue(), new AnimalsCollector(dataStorage),
            Categories.NUMBERS.getValue(), new NumbersCollector(dataStorage),
            Categories.CARS.getValue(), new CarsCollector(dataStorage)
    );

    private List<String> fileData;

    public void init(String inputFileName) {
        this.fileData = FileReader.readFile(inputFileName);
    }

    public void execute() {
        Collector collector = null;

        for (String item : fileData) {
            String normalizedItem = item.toLowerCase();
            if (isCategoryItem(normalizedItem) && collector != null) {
                collector.collect(item);
                continue;
            }
            collector = collectors.get(normalizedItem);
        }
    }

    private boolean isCategoryItem(String item) {
        return !collectors.containsKey(item);
    }

    public DataStorage getDataStorage() {
        return dataStorage;
    }
}
