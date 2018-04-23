package interview.coding;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StockOptionsTest {

    @Test
    public void testMPUOUIFEmptyPrices() {
        StockOptions so = new StockOptions();
        assertEquals(0, so.maxProfitUnlimitedOptionsUnlimitedInFlight(new int[0]));
    }

    @Test
    public void testMPUOUIFSinglePrice() {
        StockOptions so = new StockOptions();
        assertEquals(0, so.maxProfitUnlimitedOptionsUnlimitedInFlight(new int[]{10}));
    }

    @Test
    public void testMPUOUIFTwoPricesDescending() {
        StockOptions so = new StockOptions();
        assertEquals(0, so.maxProfitUnlimitedOptionsUnlimitedInFlight(new int[]{20, 12}));
    }

    @Test
    public void testMPUOUIFTwoPricesAscending() {
        StockOptions so = new StockOptions();
        assertEquals(8, so.maxProfitUnlimitedOptionsUnlimitedInFlight(new int[]{12, 20}));
    }

    @Test
    public void testMPUOUIFInterestingExample() {
        StockOptions so = new StockOptions();
        assertEquals(113, so.maxProfitUnlimitedOptionsUnlimitedInFlight(new int[]{12, 20, 3, 45, 16, 9, 10, 1, 3, 8}));
    }

    @Test
    public void testMPUOUIFAL5AEmptyPrices() {
        StockOptions so = new StockOptions();
        assertEquals(0, so.maxProfitUnlimitedOptionsUnlimitedInFlightFiveSecondsInBetween(new int[0]));
    }

    @Test
    public void testMPUOUIFAL5ASinglePrice() {
        StockOptions so = new StockOptions();
        assertEquals(0, so.maxProfitUnlimitedOptionsUnlimitedInFlightFiveSecondsInBetween(new int[]{10}));
    }

    @Test
    public void testMPUOUIFAL5ATwoPricesDescending() {
        StockOptions so = new StockOptions();
        assertEquals(0, so.maxProfitUnlimitedOptionsUnlimitedInFlightFiveSecondsInBetween(new int[]{20, 12}));
    }

    @Test
    public void testMPUOUIFAL5ATwoPricesAscending() {
        StockOptions so = new StockOptions();
        assertEquals(0, so.maxProfitUnlimitedOptionsUnlimitedInFlightFiveSecondsInBetween(new int[]{12, 20}));
    }

    @Test
    public void testMPUOUIFAL5ASixPricesDescending() {
        StockOptions so = new StockOptions();
        assertEquals(0, so.maxProfitUnlimitedOptionsUnlimitedInFlightFiveSecondsInBetween(new int[]{17, 16, 15, 14, 13, 12}));
    }

    @Test
    public void testMPUOUIFAL5ASixPricesAscending() {
        StockOptions so = new StockOptions();
        assertEquals(5, so.maxProfitUnlimitedOptionsUnlimitedInFlightFiveSecondsInBetween(new int[]{12, 13, 14, 15, 16, 17}));
    }

    @Test
    public void testMPUOUIFAL5AInterestingExample() {
        StockOptions so = new StockOptions();
        assertEquals(5, so.maxProfitUnlimitedOptionsUnlimitedInFlightFiveSecondsInBetween(new int[]{12, 20, 3, 45, 16, 9, 10, 1, 3, 8}));
    }

    @Test
    public void testMPUOUIFAL5AInterestingExample2() {
        StockOptions so = new StockOptions();
        assertEquals(105, so.maxProfitUnlimitedOptionsUnlimitedInFlightFiveSecondsInBetween(new int[]{31, 30, 12, 20, 3, 45, 16, 9, 10, 1, 3, 8, 19, 4, 21, 18}));
    }

    @Test
    public void testMPOBOSEmptyPrices() {
        StockOptions so = new StockOptions();
        assertEquals(0, so.maxProfitOneBuyOneSell(new int[0]));
    }

    @Test
    public void testMPOBOSSinglePrice() {
        StockOptions so = new StockOptions();
        assertEquals(0, so.maxProfitOneBuyOneSell(new int[]{10}));
    }

    @Test
    public void testMPOBOSTwoPricesDescending() {
        StockOptions so = new StockOptions();
        assertEquals(0, so.maxProfitOneBuyOneSell(new int[]{20, 12}));
    }

    @Test
    public void testMPOBOSTwoPricesAscending() {
        StockOptions so = new StockOptions();
        assertEquals(8, so.maxProfitOneBuyOneSell(new int[]{12, 20}));
    }

    @Test
    public void testMPOBOSSixPricesDescending() {
        StockOptions so = new StockOptions();
        assertEquals(0, so.maxProfitOneBuyOneSell(new int[]{17, 16, 15, 14, 13, 12}));
    }

    @Test
    public void testMPOBOSSixPricesAscending() {
        StockOptions so = new StockOptions();
        assertEquals(5, so.maxProfitOneBuyOneSell(new int[]{12, 13, 14, 15, 16, 17}));
    }

    @Test
    public void testMPOBOSInterestingExample() {
        StockOptions so = new StockOptions();
        assertEquals(42, so.maxProfitOneBuyOneSell(new int[]{12, 20, 3, 45, 16, 9, 10, 1, 3, 8}));
    }

    @Test
    public void testMPOBOSInterestingExample2() {
        StockOptions so = new StockOptions();
        assertEquals(20, so.maxProfitOneBuyOneSell(new int[]{31, 30, 12, 20, 3, 11, 16, 9, 10, 1, 3, 8, 19, 4, 21, 18}));
    }

    @Test
    public void testMPUO1IFInterestingExample() {
        StockOptions so = new StockOptions();
        assertEquals(58, so.maxProfitUnlimitedOptionsOneInFlight(new int[]{12, 20, 3, 45, 16, 9, 10, 1, 3, 8}));
    }

    @Test
    public void testMPUO1IFInterestingExample2() {
        StockOptions so = new StockOptions();
        assertEquals(57, so.maxProfitUnlimitedOptionsOneInFlight(new int[]{31, 30, 12, 20, 3, 11, 16, 9, 10, 1, 3, 8, 19, 4, 21, 18}));
    }
}
