package com.kovalenko.task;

public class Main {

    private static final String INPUT_FILE_NAME = "input.txt";

    public static void main(String[] args) {
        Application application = new Application();
        application.init(INPUT_FILE_NAME);

        application.execute();

        System.out.println(application.getDataStorage());
    }

}
