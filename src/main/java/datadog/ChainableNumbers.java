package datadog;

import java.util.*;

public class ChainableNumbers {

    public boolean isChainable(int[] numbers, int start, int end) {
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

        return findChain(nodes, start, end);
    }

    private boolean findChain(HashMap<Integer, HashMap<Integer, Integer>> nodes, int start, int end) {
        LinkedList<Integer> queue = new LinkedList<>();
        HashSet<Integer> keySet = new HashSet<>(100);

        queue.add(start);
        keySet.add(getKey(start));
        while(!queue.isEmpty()) {
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

}
