package datadog;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class BalancedParensTest {

    @Test
    public void noParensTest() {
        BalancedParens bp = new BalancedParens();
        assertTrue(bp.isBalanced("Hello World!"));
    }

    @Test
    public void unBalancedSingleLeftParen() {
        BalancedParens bp = new BalancedParens();
        assertFalse(bp.isBalanced("("));
    }

    @Test
    public void unBalancedSingleRightPaaren() {
        BalancedParens bp = new BalancedParens();
        assertFalse(bp.isBalanced(")"));
    }

    @Test
    public void balancedPair() {
        BalancedParens bp = new BalancedParens();
        assertTrue(bp.isBalanced("()"));
    }

    @Test
    public void unBalancedPair() {
        BalancedParens bp = new BalancedParens();
        assertFalse(bp.isBalanced(")("));
    }

    @Test
    public void balancedPairWithText() {
        BalancedParens bp = new BalancedParens();
        assertTrue(bp.isBalanced("Hello (inner-text) World!"));
    }

    @Test
    public void balancedTwoNextToEachOther() {
        BalancedParens bp = new BalancedParens();
        assertTrue(bp.isBalanced("()()"));
    }

    @Test
    public void balancedOneInsideTheOther() {
        BalancedParens bp = new BalancedParens();
        assertTrue(bp.isBalanced("(())"));
    }

    @Test
    public void unBalancedHalfInsideAnother() {
        BalancedParens bp = new BalancedParens();
        assertFalse(bp.isBalanced("(()"));
    }

    @Test
    public void difficultTest() {
        BalancedParens bp = new BalancedParens();
        assertTrue(bp.isBalanced("hello ()I came (to see() if you ( like ((())some text ())))"));
    }
}
