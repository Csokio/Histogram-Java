package com.codecool.histogram;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HistogramTest {

    Histogram wordCounter = new Histogram();

    @Test
    public void testPositiveParameters() {
        Histogram generator = new Histogram();
        List<Range> ranges = generator.generateRanges(1, 5);
    }

    @Test
    public void testNegativeStepParameter() {
        Histogram generator = new Histogram();
        assertThrows(IllegalArgumentException.class, () -> {
                    List<Range> rangeList = generator.generateRanges(-1, 5);
                }
        );
    }

    @Test
    public void testNegativeAmountParameter() {
        Histogram generator = new Histogram();
        assertThrows(IllegalArgumentException.class, () -> {
                    List<Range> rangeList = generator.generateRanges(3, -5);
                }
        );
    }

    @Test
    public void testAllWordsInRange() {
        String text = "This is a test sentence.";
        List<Range> ranges = Arrays.asList(new Range(1, 3), new Range(4, 5), new Range(7, 8));

        Map<Range, Integer> expected = new HashMap<>();
        expected.put(new Range(1, 3), 2);
        expected.put(new Range(4, 5), 2);
        expected.put(new Range(7, 8), 1);

        Map<Range, Integer> actual = wordCounter.generate(text, ranges);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGenerate_wordsOutOfRange() {
        String text = "The quick brown fox jumps over the lazy dog.";
        List<Range> ranges = Arrays.asList(new Range(1, 2), new Range(3, 3), new Range(4, 6), new Range(7, 7));

        Map<Range, Integer> expected = new HashMap<>();
        expected.put(new Range(4, 6), 5);
        expected.put(new Range(3, 3), 4);


        Map<Range, Integer> actual = wordCounter.generate(text, ranges);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testGenerate_emptyText() {
        String text = "";
        List<Range> ranges = Arrays.asList(new Range(1, 3), new Range(4, 5), new Range(6, 7));

        Map<Range, Integer> expected = new HashMap<>();

        Map<Range, Integer> actual = wordCounter.generate(text, ranges);

        Assertions.assertEquals(expected, actual);
    }


    @Test
    void testGenerate_nullText() {
        String text = null;
        List<Range> ranges = Arrays.asList(new Range(1, 3), new Range(4, 5), new Range(6, 7));

        Assertions.assertThrows(IllegalArgumentException.class, () -> wordCounter.generate(text, ranges));
    }

    @Test
    void testGenerate_nullRanges() {
        String text = "This is a test sentence.";
        List<Range> ranges = null;

        Assertions.assertThrows(IllegalArgumentException.class, () -> wordCounter.generate(text, ranges));
    }

    @Test
    void testGenerate_generateHistogramMultipleTimes() {
        String text1 = "The quick brown fox jumps over the lazy dog.";
        String text2 = "This is a test sentence.";
        List<Range> ranges = Arrays.asList(new Range(1, 3), new Range(4, 5), new Range(6, 7));

        // Generate histogram for first text
        Map<Range, Integer> expected1 = Map.of(new Range(1, 3), 4, new Range(4, 5), 5);
        Map<Range, Integer> actual1 = wordCounter.generate(text1, ranges);
        Assertions.assertEquals(expected1, actual1);

        // Generate histogram for second text
        Map<Range, Integer> expected2 = Map.of(new Range(1, 3), 2, new Range(4, 5), 2);
        Map<Range, Integer> actual2 = wordCounter.generate(text2, ranges);
        Assertions.assertEquals(expected2, actual2);
    }


    @Test
    public void testGetHistogram_beforeGeneratingHistogram() {
        Histogram histogram = new Histogram();
        Map<Range, Integer> expected = new HashMap<>();
        Map<Range, Integer> actual = histogram.getHistogram();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetHistogram_afterGeneratingHistogram() {
        Histogram histogram = new Histogram();

        String text = "This is a test sentence.";
        List<Range> ranges = Arrays.asList(new Range(1, 3), new Range(4, 5), new Range(7, 8));
        histogram.generate(text, ranges);

        Map<Range, Integer> expected = new HashMap<>();
        expected.put(new Range(1, 3), 2);
        expected.put(new Range(4, 5), 2);
        expected.put(new Range(7, 8), 1);

        Map<Range, Integer> actual = histogram.getHistogram();

        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void testGetHistogram_afterMultipleCallsOfGenerateHistogram()
    {
        String text1 = "The quick brown fox jumps over the lazy dog.";
        String text2 = "This is a test sentence.";
        Histogram histogram = new Histogram();
        List<Range> ranges = Arrays.asList(new Range(1, 3), new Range(4, 5), new Range(6, 7));
        histogram.generate(text1, ranges);

        // Generate histogram for first text
        Map<Range, Integer> expected1 = Map.of(new Range(1, 3), 4, new Range(4, 5), 5);
        Map<Range, Integer> actual1 = histogram.getHistogram();
        Assertions.assertEquals(expected1, actual1);

        histogram.generate(text2, ranges);
        // Generate histogram for second text
        Map<Range, Integer> expected2 = Map.of(new Range(1, 3), 2, new Range(4, 5), 2);
        Map<Range, Integer> actual2 = histogram.getHistogram();
        Assertions.assertEquals(expected2, actual2);

    }


    @Test
    public void testToStringBeforeGenerating() {

        Map<Range, Integer> expected = new HashMap<>();

        Assertions.assertEquals(expected, wordCounter.getHistogram());
    }

    @Test
    public void testToStringAfterGenerating() {
        String text = "The quick brown fox jumps over the lazy dog.";
        List<Range> ranges = Arrays.asList(new Range(1, 3), new Range(4, 5), new Range(6, 7));

        // Generate histogram for the given text and ranges
        wordCounter.generate(text, ranges);

        // Get the string representation of histogram after generating
        String actual = wordCounter.getHistogram().toString();

        // Define the expected output based on the given text and ranges
        String expected = "{4  - 5 =5, 1  - 3 =4}";

        // Compare the expected and actual outputs
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetMin()
    {
        Histogram histogram = new Histogram();
        List<Range> ranges = Arrays.asList(new Range(1, 3), new Range(4, 6), new Range(7, 9));
        histogram.generate("cat dog horse elephant mouse lion tiger bear wolf", ranges);

        Integer expected = 1;
        Integer actual = histogram.getMin();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetMax()
    {
        Histogram histogram = new Histogram();
        List<Range> ranges = Arrays.asList(new Range(1, 3), new Range(4, 6), new Range(7, 9));
        histogram.generate("cat dog horse elephant mouse lion tiger bear wolf", ranges);



        Integer expected = 6;
        Integer actual = histogram.getMax();
        assertEquals(expected, actual);
    }

    @Test
    public void testNormalizesValues()
    {
        Histogram histogram = new Histogram();
        List<Range> ranges = Arrays.asList(new Range(1, 3), new Range(4, 6), new Range(7, 9));
        histogram.generate("cat dog horse elephant mouse lion tiger bear wolf", ranges);


        Map<Range, Integer> expected = Map.of(new Range(1, 3), 20, new Range(4, 6), 100,new Range(7, 9), 0);

        histogram.normalizesValues();

        assertEquals(expected, histogram.getHistogram());



    }


}
