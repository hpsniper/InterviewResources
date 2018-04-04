package interview.coding;

import java.util.Stack;

/*
    Given a number n, print (n)!\n(n-1)!\n...(1)!
    Below are 2 implemenetations, one using division and one using a stack to avoid duplicate computation
*/

public class PrintFactorial {

    public void printNfactorialsToZero(int n) {
        long result = factorial(n);
        System.out.println(result);
        while(n > 1) {
            result = result / n;
            System.out.println(result);
            n--;
        }
    }

    public void printNFactorialsToZeroUsingStack(int n) {
        Stack<Long> values = new Stack<>();
        if(n == 0) {
            System.out.println(0L);
            return;
        }

        long result = 1L;
        for(int i=1;i<=n;i++) {
            result = result * i;
            values.push(result);
        }

        while(!values.empty()) {
            System.out.println(values.pop());
        }
    }

    private long factorial(int n) {
        if (n == 0) {
            return 0L;
        }

        long result = 1L;
        while(n > 0) {
            result = result * n;
            n--;
        }

        return result;
    }

}
