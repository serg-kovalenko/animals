package com.kovalenko.task.utils;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({FileReader.class, IOUtils.class})
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

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfCanNotReadLinesFromInputStream() throws IOException {
        mockStatic(IOUtils.class);

        when(IOUtils.readLines(any(InputStream.class), eq(StandardCharsets.UTF_8))).thenThrow(IOException.class);

        FileReader.readFile("file_reader.txt");
    }
}