package interview.coding;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;

/* Find longest sequential sub-sequence of an array that sums to 0 */

class Answer {
    int originalStartIndex;
    int originalEndIndex;

    public Answer(int originalStartIndex, int originalEndIndex) {
        this.originalStartIndex = originalStartIndex;
        this.originalEndIndex = originalEndIndex;
    }

    public int getLength() {
        return (originalEndIndex - originalStartIndex) + 1;
    }

}

public class LongestSubsequence {

    public Optional<Answer> bruteForce(int[] array) {
        Optional<Answer> currentMaxAnswer = Optional.empty();
        int currentSum;
        for(int i=0;i<array.length;i++) {
            currentSum = array[i];
            if(currentSum == 0) {
                currentMaxAnswer = getMaxAnswer(currentMaxAnswer, new Answer(i, i));
            }
            for(int j=i+1;j<array.length;j++) {
                currentSum += array[j];
                if(currentSum == 0) {
                    currentMaxAnswer = getMaxAnswer(currentMaxAnswer, new Answer(i, j));
                }
            }
        }

        return currentMaxAnswer;
    }

    public Optional<Answer> smart(int[] array) {
        HashMap<Integer, Integer> iValueWithSum = new HashMap<>(array.length);
        iValueWithSum.put(0, -1);
        Optional<Answer> currentMaxAnswer = Optional.empty();

        int currentSum = 0;
        for(int i=0;i<array.length;i++) {
            currentSum += array[i];
            if(iValueWithSum.containsKey(currentSum)) {
                int originalStartIndex = iValueWithSum.get(currentSum) + 1;
                currentMaxAnswer = getMaxAnswer(currentMaxAnswer, new Answer(originalStartIndex, i));
            } else {
                iValueWithSum.put(currentSum, i);
            }
        }

        return currentMaxAnswer;
    }

    public Optional<Answer> getMaxAnswer(Optional<Answer> currentMaxAnswer, Answer newAnswer) {
        if(currentMaxAnswer.isPresent()) {
            return currentMaxAnswer.get().getLength() > newAnswer.getLength() ? currentMaxAnswer : Optional.of(newAnswer);
        }

        return Optional.of(newAnswer);
    }

    public void testAnswer(int[] array) {
       Optional<Answer> answerBrute = bruteForce(array);
       Optional<Answer> answerSmart = smart(array);
       System.out.println(Arrays.toString(array));
       System.out.println(String.format("Brute Force => %s;", generateAnswerOutput(array, answerBrute)));
       System.out.println(String.format("Smart       => %s;", generateAnswerOutput(array, answerSmart)));
       System.out.println("");
    }

    public String generateAnswerOutput(int[] array, Optional<Answer> maybeAnswer) {
        if(!maybeAnswer.isPresent()) {
            return "no valid answer";
        }
        Answer answer = maybeAnswer.get();
        String result = "";
        result += "startIndex=" + answer.originalStartIndex;
        result += " endIndex=" + answer.originalEndIndex;
        result += " answer length=" + answer.getLength();
        result += " [";
        for(int i=answer.originalStartIndex;i<=answer.originalEndIndex && i >= 0;i++) {
            result += array[i];
            result += i == answer.originalEndIndex ? "" : ",";
        }
        result += "]";
        return result;
    }

    public static void main(String[] args) {
        LongestSubsequence longestSubsequence = new LongestSubsequence();
        /* -- No Answer -- */
        longestSubsequence.testAnswer(new int[]{5,12,23});
        longestSubsequence.testAnswer(new int[]{5});

        /* -- Answer -- */
        longestSubsequence.testAnswer(new int[]{0});
        longestSubsequence.testAnswer(new int[]{10,12,-10,-13,10,-10,11});
        longestSubsequence.testAnswer(new int[]{12,-10,-13,10,-10,11});
        longestSubsequence.testAnswer(new int[]{20,10,12,-10,-13,10,-10,11});
        longestSubsequence.testAnswer(new int[]{5,6,9,12,-7,-14,33});
    }


}
