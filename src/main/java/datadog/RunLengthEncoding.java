package datadog;

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
