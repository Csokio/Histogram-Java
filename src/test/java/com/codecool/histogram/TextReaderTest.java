package com.codecool.histogram;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


public class TextReaderTest {

    @Test
    public void testRead() throws IOException {
        String nonExistFile = "non_exist.txt";
        TextReader text = new TextReader(nonExistFile);

        Exception exception = assertThrows(FileNotFoundException.class, () -> {
            text.read();
        });

        String expectedErrorMessage = String.format(nonExistFile + " (No such file or directory)");

        Assertions.assertEquals(expectedErrorMessage, exception.getMessage());
    }



}
