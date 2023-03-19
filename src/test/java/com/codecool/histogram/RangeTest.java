package com.codecool.histogram;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;


public class RangeTest {

    Range range = null;

    /*@BeforeEach
    public Range Init(){
        range = new Range(3, 5);
    }*/
    @Test
    public void testRange()
    {
        int from = -1;
        int to = -2;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Range(from, to));
    }






    @Test
    public void testIsInRangeForWordLengthInRange()
    {
        String word = "test";
        int from = 2;
        int to = 4;

        boolean result = word.length() >= from && word.length() <= to;

        assertTrue(result);
    }


    @Test
    public void testIsInRangeForWordLengthEqualsToRangeFrom() {
        String word = "te";
        int from = 2;
        int to = 4;

        boolean result = word.length() >= from && word.length() <= to;

        assertTrue(result);
    }

    @Test
    public void testIsInRangeForWordLengthOutOfRange() {
        String word = "internationalization";
        int from = 2;
        int to = 4;

        boolean result = word.length() >= from && word.length() <= to;

        assertFalse(result);
    }
    @Test
    public void testToString()
    {
        Range range = new Range(1, 10);
        String expectedResult = "1  - 10";

        String actualResult = range.toString();

        assertEquals(expectedResult, actualResult);
    }
    
}
