package com.kovalenko.task;

import com.kovalenko.task.storage.DataStorage;

public class Main {

    private static final String INPUT_FILE_NAME = "input.txt";
    private static final String CATEGORIES_CONFIGURATION_FILE_NAME = "categories_config.properties";

    public static void main(String[] args) {
        DataStorage storage = new DataStorage();
        Application application = new Application(storage);

        application.init(INPUT_FILE_NAME, CATEGORIES_CONFIGURATION_FILE_NAME);

        application.execute();

        System.out.println(storage);
    }

}
