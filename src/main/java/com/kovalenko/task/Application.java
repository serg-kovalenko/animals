package com.kovalenko.task;

import com.google.common.annotations.VisibleForTesting;
import com.kovalenko.task.collector.Collector;
import com.kovalenko.task.storage.DataStorage;
import com.kovalenko.task.utils.CollectorInitializer;
import com.kovalenko.task.utils.Constant;
import com.kovalenko.task.utils.FileReader;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Application {

    private static final String CATEGORY_COLLECTOR_PATTERN = "\\w+=.+";

    private final DataStorage dataStorage;

    private Map<String, Collector> collectors;

    private List<String> fileData;

    public Application(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    public void init(String inputFileName, String applicationConfigFileName) {
        this.fileData = FileReader.readFile(inputFileName);
        this.collectors = getCollectors(applicationConfigFileName);
    }

    public void execute() {
        Collector collector = null;

        for (String item : fileData) {
            collector = collectors.getOrDefault(item.toLowerCase(), collector);

            Optional.of(item)
                    .filter(this::isCategoryItem)
                    .ifPresent(collector::collect);
        }
    }

    private boolean isCategoryItem(String item) {
        return !collectors.containsKey(item.toLowerCase());
    }

    @VisibleForTesting
    Map<String, Collector> getCollectors(String applicationConfigFileName) {
        return FileReader.readFile(applicationConfigFileName).stream()
                .filter(config -> config.matches(CATEGORY_COLLECTOR_PATTERN))
                .map(config -> config.split(Constant.EQUALS, 2))
                .collect(Collectors.toMap(pair -> pair[0], pair -> CollectorInitializer.initCollector(pair[1], dataStorage)));
    }
}
