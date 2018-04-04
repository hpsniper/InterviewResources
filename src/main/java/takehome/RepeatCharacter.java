package takehome;

import java.util.*;

public class RepeatCharacter {

  /*
   Given a string s and an integer k, find all substrings of s of size k that have exactly 1 duplicate character
   */

  public static List<String> singleDuplicateCharacterSubstring(String inputString, int k) {
    List<String> result = new ArrayList<>();

    if(inputString.length() < k) {
      return result;
    }

    int uniqueCharacterCount = 0;
    int[] characterCounts = new int[26];
    String substr = "";
    int i;
    // build our initial k length substring
    for(i=0;i<k;i++) {
      char character = inputString.charAt(i);
      characterCounts[getIndex(character)]++;
      if(isUnique(characterCounts, character)) {
        uniqueCharacterCount++;
      }
      substr = substr + character;
    }

    if(uniqueCharacterCount == k - 1) {
      result.add(substr);
    }

    // create a scrolling window removing 1 char from the beginning, and adding 1 char to the end of our initial string
    while(i < inputString.length()) {
      // remove the start of our window
      char start = substr.charAt(0);
      if(isUnique(characterCounts, start)) {
        uniqueCharacterCount--;
      }
      characterCounts[getIndex(start)]--;
      substr = substr.substring(1, k);

      // add a character to the end of our window
      char character = inputString.charAt(i);
      characterCounts[getIndex(character)]++;
      if(isUnique(characterCounts, character)) {
        uniqueCharacterCount++;
      }
      substr = substr + character;

      if(uniqueCharacterCount == k - 1) {
        result.add(substr);
      }
      i++;
    }
    return result;
  }

  private static boolean isUnique(int[] characterCount, char character) {
    return characterCount[getIndex(character)] == 1;
  }

  //the [character - 'a'] is to subtract 1 from a characters natural index to fit in an array that starts at 0
  private static int getIndex(char character) {
      return character - 'a';
  }

  public static void testRepeat(String inputString, int k, List<String> expectedResult) {
    List<String> actualResult = singleDuplicateCharacterSubstring(inputString, k);
    if(!expectedResult.equals(actualResult)) {
      System.out.println("\tERROR");
      System.out.println("\texpected="+expectedResult);
      System.out.println("\tactual="+actualResult);
    } else {
      System.out.println("singleDuplicateCharacterSubstring(\""+inputString+"\", "+k+") success.");
    }
  }

  public static void main(String[] args) {
    String inputString = "democracy";
    int k = 5;
    List<String> expectedResult = new ArrayList<>();
    expectedResult.add("ocrac");
    expectedResult.add("cracy");
    testRepeat(inputString, k, expectedResult);

    inputString = "";
    k = 1;
    expectedResult = new ArrayList<>();
    testRepeat(inputString, k, expectedResult);

    inputString = "d";
    k = 3;
    expectedResult = new ArrayList<>();
    testRepeat(inputString, k, expectedResult);

    inputString = "democray";
    k = 5;
    expectedResult = new ArrayList<>();
    testRepeat(inputString, k, expectedResult);

    inputString = "aaaaaa";
    k = 2;
    expectedResult = new ArrayList<>();
    expectedResult.add("aa");
    expectedResult.add("aa");
    expectedResult.add("aa");
    expectedResult.add("aa");
    expectedResult.add("aa");
    testRepeat(inputString, k, expectedResult);
  }
}
