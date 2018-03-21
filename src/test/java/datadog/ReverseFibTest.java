package datadog;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ReverseFibTest {

    @Test
    public void testFib() {
        ReverseFib rf = new ReverseFib();
        assertEquals(0L, rf.reverseFib(0));
        assertEquals(1L, rf.reverseFib(1));
        assertEquals(1L, rf.reverseFib(2));
        assertEquals(2L, rf.reverseFib(3));
        assertEquals(3L, rf.reverseFib(4));
        assertEquals(5L, rf.reverseFib(5));
        assertEquals(8L, rf.reverseFib(6));
        assertEquals(13L, rf.reverseFib(7));
        assertEquals(21L, rf.reverseFib(8));
        assertEquals(34L, rf.reverseFib(9));
        assertEquals(55L, rf.reverseFib(10));
        assertEquals(6765L, rf.reverseFib(20));
        assertEquals(832040L, rf.reverseFib(30));
        assertEquals(102334155L, rf.reverseFib(40));
        assertEquals(12586269025L, rf.reverseFib(50));
        assertEquals(99194853094755497L, rf.reverseFib(83));
        assertEquals(160500643816367088L, rf.reverseFib(84));
        assertEquals(679891637638612258L, rf.reverseFib(87));
        assertEquals(1100087778366101931L, rf.reverseFib(88));
        assertEquals(2880067194370816120L,  rf.reverseFib(90));
        // The last fib number that can be represented by a java long
        assertEquals(7540113804746346429L, rf.reverseFib(92));
    }

    @Test
    public void testStackFrames() {
        ReverseFib rf = new ReverseFib();
        assertEquals(6765L, rf.reverseFib(20));
        assertEquals(6765L, rf.headRecursiveFib(20));
        assertEquals(6765L, rf.tailRecursiveFib(20));
    }


}
