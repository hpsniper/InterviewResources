package datadog;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ShortOnesTest {

    @Test
    public void testFib() {
        ShortOnes so = new ShortOnes();
        assertEquals(0L, so.reverseFib(0));
        assertEquals(1L, so.reverseFib(1));
        assertEquals(1L, so.reverseFib(2));
        assertEquals(2L, so.reverseFib(3));
        assertEquals(3L, so.reverseFib(4));
        assertEquals(5L, so.reverseFib(5));
        assertEquals(8L, so.reverseFib(6));
        assertEquals(13L, so.reverseFib(7));
        assertEquals(21L, so.reverseFib(8));
        assertEquals(34L, so.reverseFib(9));
        assertEquals(55L, so.reverseFib(10));
        assertEquals(6765L, so.reverseFib(20));
        assertEquals(832040L, so.reverseFib(30));
        assertEquals(102334155L, so.reverseFib(40));
        assertEquals(12586269025L, so.reverseFib(50));
        assertEquals(99194853094755497L, so.reverseFib(83));
        assertEquals(160500643816367088L, so.reverseFib(84));
        assertEquals(679891637638612258L, so.reverseFib(87));
        assertEquals(1100087778366101931L, so.reverseFib(88));
        assertEquals(2880067194370816120L,  so.reverseFib(90));
        // The last fib number that can be represented by a java long
        assertEquals(7540113804746346429L, so.reverseFib(92));
    }

    @Test
    public void testStackFrames() {
        ShortOnes so = new ShortOnes();
        assertEquals(6765L, so.reverseFib(20));
        assertEquals(6765L, so.headRecursiveFib(20));
        assertEquals(6765L, so.tailRecursiveFib(20));
    }

    @Test
    public void testPrintFactorials() {
        ShortOnes so = new ShortOnes();
        so.printNfactorialsToZero(6);
    }

    @Test
    public void testRunLengthEncoding() {
        ShortOnes so = new ShortOnes();
        assertEquals("H1e1l2o1 1W1o1r1l1d1!1", so.runLengthEncoding("Hello World!"));
    }

    @Test
    public void testRunLengthEncodingEnding() {
        ShortOnes so = new ShortOnes();
        assertEquals("H1e1l2o1 1W1o1r1l1d1!4", so.runLengthEncoding("Hello World!!!!"));
    }

    @Test
    public void testRunLengthEncodingUseful() {
        ShortOnes so = new ShortOnes();
        assertEquals("H1h2H1e1l15o5 1W3o1r1l4d1!7", so.runLengthEncoding("HhhHelllllllllllllllooooo WWWorlllld!!!!!!!"));
    }

    @Test
    public void testRunLengthDecoding() {
        ShortOnes so = new ShortOnes();
        assertEquals("Hello World!", so.runLengthDecoding("H1e1l2o1 1W1o1r1l1d1!1"));
    }

    @Test
    public void testRunLengthDecodingEnding() {
        ShortOnes so = new ShortOnes();
        assertEquals("Hello World!!!!", so.runLengthDecoding("H1e1l2o1 1W1o1r1l1d1!4"));
    }

    @Test
    public void testRunLengthDecodingUseful() {
        ShortOnes so = new ShortOnes();
        assertEquals("HhhHelllllllllllllllooooo WWWorlllld!!!!!!!", so.runLengthDecoding("H1h2H1e1l15o5 1W3o1r1l4d1!7"));
    }
}
