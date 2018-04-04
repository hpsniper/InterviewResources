package interview.coding;

/*
    Given a string, provide run-length encoding and decoding
    'heeeeeeeeeyyy guysssssss!!!!' => 'h1e9y3 1g1u1y1s7!4'
    future improvement could be added to whenever the encoding is 1, just use the letter so 'he9y3 guys7!4'
*/

public class RunLengthEncoding {

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
