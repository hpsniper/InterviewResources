package interview.coding;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RunLengthEncodingTest {

    @Test
    public void testRunLengthEncoding() {
        RunLengthEncoding rle = new RunLengthEncoding();
        assertEquals("H1e1l2o1 1W1o1r1l1d1!1", rle.runLengthEncoding("Hello World!"));
    }

    @Test
    public void testRunLengthEncodingEnding() {
        RunLengthEncoding rle = new RunLengthEncoding();
        assertEquals("H1e1l2o1 1W1o1r1l1d1!4", rle.runLengthEncoding("Hello World!!!!"));
    }

    @Test
    public void testRunLengthEncodingUseful() {
        RunLengthEncoding rle = new RunLengthEncoding();
        assertEquals("H1h2H1e1l15o5 1W3o1r1l4d1!7", rle.runLengthEncoding("HhhHelllllllllllllllooooo WWWorlllld!!!!!!!"));
    }

    @Test
    public void testRunLengthDecoding() {
        RunLengthEncoding rle = new RunLengthEncoding();
        assertEquals("Hello World!", rle.runLengthDecoding("H1e1l2o1 1W1o1r1l1d1!1"));
    }

    @Test
    public void testRunLengthDecodingEnding() {
        RunLengthEncoding rle = new RunLengthEncoding();
        assertEquals("Hello World!!!!", rle.runLengthDecoding("H1e1l2o1 1W1o1r1l1d1!4"));
    }

    @Test
    public void testRunLengthDecodingUseful() {
        RunLengthEncoding rle = new RunLengthEncoding();
        assertEquals("HhhHelllllllllllllllooooo WWWorlllld!!!!!!!", rle.runLengthDecoding("H1h2H1e1l15o5 1W3o1r1l4d1!7"));
    }
}
