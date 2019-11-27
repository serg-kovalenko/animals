package com.kovalenko.task.utils;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class FileReader {

    private FileReader() {
    }

    public static List<String> readFile(String fileName) {
        try (InputStream inputStream = FileReader.class.getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new IllegalArgumentException(String.format("File [%s] does not exist.", fileName));
            }
            return IOUtils.readLines(inputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
