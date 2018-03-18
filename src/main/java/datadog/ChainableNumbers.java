package datadog;

import java.util.*;

public class ChainableNumbers {
    int outerQueueIterations = 0;
    int innerLoopIterations = 0;
    long duration = 0;

    public boolean isChainable(int[] numbers, int start, int end) {
        long startTime = System.currentTimeMillis();

        if(start == end) {
            return true;
        }

        HashMap<Integer, HashMap<Integer, Integer>> nodes = new HashMap<>(numbers.length);
        boolean containsStart = false;
        boolean containsEnd = false;

        // setup nodes and set if our map contains start and end
        for(int i=0;i<numbers.length;i++) {
            int num = numbers[i];
            if(!isFourDigits(num)) {
                continue;
            }

            if (num == start) {
                containsStart = true;
            } else if(num == end) {
                containsEnd = true;
            }

            int lock = getLock(num);
            if(!nodes.containsKey(lock)) {
                nodes.put(lock, new HashMap<>());
            }

            int key = getKey(num);
            HashMap<Integer, Integer> map = nodes.get(lock);
            map.put(key, num);
            nodes.put(lock, map);
        }

        if(!containsStart || !containsEnd) {
            return false;
        }

        boolean result = findChain(nodes, start, end);
        long endTime = System.currentTimeMillis();

        duration = (endTime - startTime);
        return result;
    }

    private boolean findChain(HashMap<Integer, HashMap<Integer, Integer>> nodes, int start, int end) {
        LinkedList<Integer> queue = new LinkedList<>();
        HashSet<Integer> keySet = new HashSet<>(100);

        queue.add(start);
        keySet.add(getKey(start));
        while(!queue.isEmpty()) {
            outerQueueIterations++;
            int current = queue.poll();
            if (current == end) {
                return true;
            }

            int currentKey = getKey(current);
            if(!nodes.containsKey(currentKey)) {
                continue;
            }

            HashMap<Integer, Integer> keyMap = nodes.get(currentKey);
            // Our currentKey can get us to our end
            if(currentKey == getLock(end)) {
                // We have found our end, put it at the front of our queue
                if(keyMap.containsKey(getKey(end))) {
                    queue.push(keyMap.get(getKey(end)));
                    keySet.add(getKey(end));
                    continue;
                } else {
                    // Should never get here, otherwise it would mean our end isn't in our map which we've checked for
                    return false;
                }
            // we have a key to a node that holds a key to our end, put that at the front of our queue
            } else if(keyMap.containsKey(getLock(end))) {
                queue.push(keyMap.get(getLock(end)));
                keySet.add(getLock(end));
                continue;
            }

            for(Map.Entry<Integer, Integer> entry : keyMap.entrySet()) {
                innerLoopIterations++;
                int newKey = entry.getKey();
                if(keySet.contains(newKey)) {
                    continue;
                }

                keySet.add(newKey);
                queue.add(entry.getValue());
            }
        }

        return false;
    }

    private boolean isFourDigits(int num) {
        return num > 999 && num < 10000;
    }

    private int getKey(int number) {
        return number % 100;
    }

    private int getLock(int number) {
        return number / 100;
    }

    private static void printTest(int[] numbers, int start, int end, boolean expected) {
        ChainableNumbers cn = new ChainableNumbers();
        boolean actual = cn.isChainable(numbers, start, end);

        if(expected != actual) {
            System.out.println(String.format("ERROR: Expected: %b Actual: %b", expected, actual));
        }

        System.out.println(String.format("outerQueues = %d, innerLoops = %d, Seconds: %02d.%d",
                cn.outerQueueIterations,
                cn.innerLoopIterations,
                (cn.duration / 1000) % 60,
                cn.duration % 1000
        ));
    }

    public static void main(String[] args) {
        // example 1: 8183 => 8363 => 6388
        printTest(new int[]{8363, 6388, 8183, 5364, 8353, 8365, 9380}, 8183, 6388, true);

        // example 2: 8183 => 8363 => 6388 => 8899 => 9953 => 5364
        printTest(new int[]{8363, 6388, 8183, 5364, 8365, 9380, 8899, 9953}, 8183, 5364, true);

        // example 3: 1234 => 3456 => 5678
        printTest(new int[]{1234, 3400, 3456, 5678}, 1234, 5678, true);

        // example 4: 9911 => 1122 => 2233 => 3344 => 4455 => 5566 => 6677 => 7788 => 8899
        printTest(new int[]{1122, 2233, 3344, 4455, 5566, 6677, 7788, 8899, 9911}, 9911, 8899, true);

        // example 5: 8899 => 9911
        printTest(new int[]{1122, 2233, 3344, 4455, 5566, 6677, 8899, 9911}, 8899, 9911, true);

        // example 6:
        printTest(new int[]{1111, 1122, 1133, 2211, 2222, 2233, 3311, 3322, 3333, 4411}, 1111, 4411, false);

        // example 7:
        printTest(new int[]{1111, 1122, 1133, 2211, 2222, 2233, 3311, 3322, 3333, 3344, 4411}, 1111, 4411, true);

        int[] lotsInts = new int[]{
                1110, 1111, 1112, 1113, 1114, 1115, 1116, 1117, 1118, 1119,
                1210, 1211, 1212, 1213, 1214, 1215, 1216, 1217, 1218, 1219,
                1310, 1311, 1312, 1313, 1314, 1315, 1316, 1317, 1318, 1319,
                1410, 1411, 1412, 1413, 1414, 1415, 1416, 1417, 1418, 1419,
                9999
        };
        // example 6:
        printTest(lotsInts, 1110, 9999, false);

        // example 7:
        printTest(lotsInts, 1111, 9999, false);

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
        printTest(no99Key, 1111, 9999, false);


        int[] allNums = new int[9000];
        for(int i=0;i<9000;i++) {
            allNums[i] = i+1000;
        }

        printTest(allNums, 1234, 5678, true);

    }
}
