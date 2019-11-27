package com.kovalenko.task.utils;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FileReaderTest {

    @Test
    public void shouldReadAllStringsFromFileToList() {
        List<String> expectedWords = Arrays.asList("NUMBERS", "one", "three", "two", "ANIMALS", "sheep", "cow");

        List<String> actualWords = FileReader.readFile("file_reader.txt");

        assertEquals(expectedWords, actualWords);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfFileNoExist() {
        FileReader.readFile("non_existing_file.txt");
    }
}