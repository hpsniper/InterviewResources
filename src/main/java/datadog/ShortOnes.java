package datadog;

public class ShortOnes {

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

    public void printNfactorialsToZero(int n) {
        long result = factorial(n);
        System.out.println(result);
        while(n > 1) {
            result = result / n;
            System.out.println(result);
            n--;
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

    public String runLengthEncoding(String s) {
        if(s.isEmpty()) {
            return s;
        }

        StringBuilder sb = new StringBuilder();
        char previous = s.charAt(0);
        int count = 1;
        for(int i=1;i<s.length();i++) {
            char c = s.charAt(i);
            if(c == previous) {
                count++;
            } else {
                sb.append(previous);
                sb.append(count);
                count = 1;
            }

            previous = c;
        }

        sb.append(previous);
        sb.append(count);

        return sb.toString();
    }

    public String runLengthDecoding(String s) {
       if(s.isEmpty()) {
           return s;
       }

       StringBuilder sb = new StringBuilder();
       // assumes a properly encoded string
       char previousNonInteger = s.charAt(0);
       StringBuilder intBuilder = new StringBuilder();
       for(int i=1;i<s.length();i++) {
           char c = s.charAt(i);
           if(Character.isDigit(c)) {
               intBuilder.append(c);
           } else {
               int timesToPrint = Integer.parseInt(intBuilder.toString());
               while(timesToPrint > 0) {
                   sb.append(previousNonInteger);
                   timesToPrint--;
               }
               intBuilder = new StringBuilder();
               previousNonInteger = c;
           }
       }

       int timesToPrint = Integer.parseInt(intBuilder.toString());
       while(timesToPrint > 0) {
           sb.append(previousNonInteger);
           timesToPrint--;
       }

       return sb.toString();
    }

}
