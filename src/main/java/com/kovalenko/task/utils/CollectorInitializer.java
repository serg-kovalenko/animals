package com.kovalenko.task.utils;

import com.google.common.base.Preconditions;
import com.kovalenko.task.collector.Collector;
import com.kovalenko.task.storage.DataStorage;

import java.lang.reflect.Constructor;

public class CollectorInitializer {

    private CollectorInitializer() {
    }

    public static Collector initCollector(String className, DataStorage dataStorage) {
        try {
            Class<?> collectorClass = Class.forName(className);
            Preconditions.checkArgument(Collector.class.isAssignableFrom(collectorClass), "Class [{}] is not collector", className);
            Constructor<?> constructor = collectorClass.getConstructor(DataStorage.class);
            return (Collector) constructor.newInstance(dataStorage);
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format("Cannot initialize collector for class [%s]", className), e);
        }
    }
}
