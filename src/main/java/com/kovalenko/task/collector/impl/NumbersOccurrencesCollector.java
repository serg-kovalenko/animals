package com.kovalenko.task.collector.impl;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.kovalenko.task.collector.Collector;
import com.kovalenko.task.container.Container;
import com.kovalenko.task.container.impl.NumbersOccurrencesContainer;
import com.kovalenko.task.storage.DataStorage;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Optional;

public class NumbersOccurrencesCollector implements Collector {

    private static final String CATEGORY = "numbers occurrences";
    private static final String ITEM_MATCH_PATTERN = "\\w+\\s*:\\s*\\d+";
    private static final String ITEM_SPLIT_PATTERN = "\\s*:\\s*";

    private static final int MIN_OCCURRENCE_TO_YES = 3;

    private Container<Pair<String, String>> numbers = new NumbersOccurrencesContainer();

    public NumbersOccurrencesCollector(DataStorage storage) {
        storage.put(CATEGORY, numbers);
    }

    @Override
    public void collect(String item) {
        numbers.addItem(getNumberPair(item));
    }

    @VisibleForTesting
    Pair<String, String> getNumberPair(String row) {
        Preconditions.checkArgument(row.matches(ITEM_MATCH_PATTERN), "Item [%s] has wrong format. Format should be {key:value}", row);

        return Optional.of(row)
                .map(item -> item.split(ITEM_SPLIT_PATTERN))
                .map(pair -> Pair.of(pair[0], mapToYesNo(pair[1])))
                .get();
    }

    @VisibleForTesting
    String mapToYesNo(String value) {
        return BooleanUtils.toStringYesNo(NumberUtils.toLong(value) > MIN_OCCURRENCE_TO_YES).toLowerCase();
    }
}
