package interview.coding;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class ChainableNumbersTest {

    private ChainableNumbers getChainableNumbers() {
        return new ChainableNumbers();
    }

    private int[] getAllFourDigitInts() {
        int[] allNums = new int[9000];
        for(int i=0;i<9000;i++) {
            allNums[i] = i+1000;
        }

        return allNums;
    }

    @Test
    public void emptyNumbersList() {
        ChainableNumbers cn = getChainableNumbers();
        assertFalse(cn.isChainable(new int[0], 1234, 5678));
    }

    @Test
    public void noChains() {
        ChainableNumbers cn = getChainableNumbers();
        assertFalse(cn.isChainable(new int[]{1234, 5678}, 1234, 5678));
    }

    @Test
    public void zeros() {
        ChainableNumbers cn = getChainableNumbers();
        assertFalse(cn.isChainable(new int[]{1200, 12}, 1200, 12));
    }

    @Test
    public void startEqualToEnd() {
        ChainableNumbers cn = getChainableNumbers();
        assertTrue(cn.isChainable(new int[]{1234, 3456}, 1234, 1234));
    }

    @Test
    public void singleChain() {
        ChainableNumbers cn = getChainableNumbers();
        // 1234 => 3456
        assertTrue(cn.isChainable(new int[]{1234, 3456}, 1234, 3456));
    }

    @Test
    public void doubleChain() {
        ChainableNumbers cn = getChainableNumbers();
        // 8183 => 8363 => 6388
        assertTrue(cn.isChainable(new int[]{8363, 6388, 8183, 5364, 8353, 8365, 9380}, 8183, 6388));
    }

    @Test
    public void fiveChain() {
        ChainableNumbers cn = getChainableNumbers();
        // 8183 => 8363 => 6388 => 8899 => 9953 => 5364
        assertTrue(cn.isChainable(new int[]{8363, 6388, 8183, 5364, 8365, 9380, 8899, 9953}, 8183, 5364));
    }

    @Test
    public void eightChain() {
        ChainableNumbers cn = getChainableNumbers();
        // 9911 => 1122 => 2233 => 3344 => 4455 => 5566 => 6677 => 7788 => 8899
        assertTrue(cn.isChainable(new int[]{1122, 2233, 3344, 4455, 5566, 6677, 7788, 8899, 9911}, 9911, 8899));
    }

    @Test
    public void allNumsTripleChain() {
        ChainableNumbers cn = getChainableNumbers();
        // 1234 => 3456 => 5678
        assertTrue(cn.isChainable(getAllFourDigitInts(), 1234, 5678));
    }

    @Test
    public void no99Key() {
        ChainableNumbers cn = getChainableNumbers();
        int[] no99Key = new int[8911];
        int arrayIndex = 0;
        int number = 1000;
        while(number < 10000) {
            if( (number % 100) == 99) {
                number++;
            } else {
                no99Key[arrayIndex] = number;

                number++;
                arrayIndex++;
            }
        }

        no99Key[no99Key.length - 1] = 9999;
        assertFalse(cn.isChainable(no99Key, 1111, 9999));
    }
}
