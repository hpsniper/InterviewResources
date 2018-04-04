package takehome;

import java.util.*;

/*
  given a list of tags as our target ['hello', 'goodbye']
  and a list of available tags ['hello', 'at', 'once', 'goodbye', 'cat', 'hello']
  return the smallest sequential list of tags from our available tags that cover our target as a start and end index
  return [3,5]
  Not [0,3] as  3   >   2
              (3-0) > (5-3)
*/

public class TagList {


  public static List<Integer> findTagListBF(List<String> targetTags, List<String> availableTags) {
    List<Integer> tagRange = new ArrayList<>();
    if(targetTags.size() == 0 || targetTags.size() > availableTags.size()) {
      return tagRange;
    }

    for(int i=0;i<availableTags.size();i++) {
      HashSet<String> targetSet= new HashSet<>(targetTags.size());
      for (String str : targetTags) {
        targetSet.add(str);
      }

      for(int j=i;j<availableTags.size();j++) {
        if(isOutsideMinimumRange(tagRange, i, j)) {
          break;
        }

        targetSet.remove(availableTags.get(j));
        if(targetSet.isEmpty()) {
          tagRange = getMinimumRange(tagRange, i, j);
          break;
        }
      }
    }

    return tagRange;
  }

  private static boolean isOutsideMinimumRange(List<Integer> currentRange, int b1, int b2) {
    if (currentRange.isEmpty()) {
      return false;
    }

    return (b2 - b1) > (currentRange.get(1) - currentRange.get(0));
  }

  private static List<Integer> getMinimumRange(List<Integer> currentRange, int b1, int b2) {
    if (currentRange.isEmpty()) {
      currentRange.add(b1);
      currentRange.add(b2);

      return currentRange;
    }

    if ( (b2 - b1) < (currentRange.get(1) - currentRange.get(0)) ) {
      currentRange.set(0, b1);
      currentRange.set(1, b2);
    }

    return currentRange;
  }

  public static void testTags(List<String> targetTags, List<String> availableTags, List<Integer> expectedResult) {
    List<Integer> actualResult = findTagListBF(targetTags, availableTags);
    if(!expectedResult.equals(actualResult)) {
      System.out.println("\tERROR");
      System.out.println("\texpected="+expectedResult);
      System.out.println("\tactual="+actualResult);
    } else {
      System.out.println("findTags(\""+targetTags.toString()+"\", "+availableTags.toString()+") => "+actualResult.toString()+" success.");
    }
  }

  public static void main(String[] args) {
    List<String> targetTags = new ArrayList<>();
    targetTags.add("in");
    targetTags.add("the");
    targetTags.add("spain");

    List<String> availableTags = new ArrayList<>();
    availableTags.add("spain");
    availableTags.add("in");
    availableTags.add("to");
    availableTags.add("where");
    availableTags.add("the");
    availableTags.add("what");
    availableTags.add("in");
    availableTags.add("spain");
    availableTags.add("in");
    availableTags.add("when");

    List<Integer> expectedResult = new ArrayList<>();
    expectedResult.add(4);
    expectedResult.add(7);
    testTags(targetTags, availableTags, expectedResult);


    targetTags = new ArrayList<>();
    targetTags.add("in");
    targetTags.add("the");
    targetTags.add("spain");

    availableTags = new ArrayList<>();
    availableTags.add("spain");
    availableTags.add("spain");
    availableTags.add("to");
    availableTags.add("where");
    availableTags.add("the");
    availableTags.add("what");
    availableTags.add("in");
    availableTags.add("spain");
    availableTags.add("in");
    availableTags.add("the");

    expectedResult = new ArrayList<>();
    expectedResult.add(7);
    expectedResult.add(9);
    testTags(targetTags, availableTags, expectedResult);


    targetTags = new ArrayList<>();
    targetTags.add("in");

    availableTags = new ArrayList<>();
    availableTags.add("spain");
    availableTags.add("spain");
    availableTags.add("to");
    availableTags.add("where");
    availableTags.add("the");
    availableTags.add("what");
    availableTags.add("in");
    availableTags.add("spain");
    availableTags.add("in");
    availableTags.add("the");

    expectedResult = new ArrayList<>();
    expectedResult.add(6);
    expectedResult.add(6);
    testTags(targetTags, availableTags, expectedResult);


    targetTags = new ArrayList<>();
    targetTags.add("in");

    availableTags = new ArrayList<>();
    availableTags.add("spain");
    availableTags.add("spain");
    availableTags.add("to");
    availableTags.add("where");
    availableTags.add("the");
    availableTags.add("what");
    availableTags.add("spain");
    availableTags.add("the");

    expectedResult = new ArrayList<>();
    testTags(targetTags, availableTags, expectedResult);
  }
}
