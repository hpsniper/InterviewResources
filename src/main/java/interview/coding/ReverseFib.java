package interview.coding;

/*
    Given a number n, print the nth factor of the fibonnaci sequence.
    Below are a number of ways of solving this problem. I prefer the first implementation.
*/

public class ReverseFib {
    public long reverseFib(int n) {
        if (n == 0) {
            return 0;
        } else if(n < 3) {
            return 1;
        }

        long backOne = 1;
        long backTwo = 1;
        long current = 2;
        for(int i=3;i<=n;i++) { // n=5
            current = backOne + backTwo;
            backTwo = backOne;
            backOne = current;
        }

        return current;
    }

    public long headRecursiveFib(int n) {
        if (n == 0) {
            return 0;
        } else if(n < 3) {
            return 1;
        }

        long fibTerm = headRecursiveFib(n - 1) + headRecursiveFib(n - 2);
        return fibTerm;
    }

    // Java technically doesn't have optimizations for tail recursion so, this doesn't do us much
    public long tailRecursiveFib(int n) {
        if (n == 0) {
            return 0;
        }

        return tailHelper(n, 1, 0);
    }

    private long tailHelper(int n, int a, int b) {
        if (n == 1) {
            return a;
        }

        return tailHelper(n-1, a+b, a);
    }

    private void printStackFrames() {
        System.out.println("tail StackFrames = " + Thread.currentThread().getStackTrace().length);
    }
}
