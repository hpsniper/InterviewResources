package interview.coding;

import com.google.common.collect.Sets;

import java.util.*;

/*
    Given an array of four digit numbers [8363, 6388, 8183, 5364, 8353, 8365, 9380]
    and two four digit numbers start 8183 and end 6388
    indicate if A and B are chainable.
    True (8183 => 8363 => 6388)
    * Any numbers X and Y are chainable if the last two digits of X are the first two digits of Y, with any number of chainable numbers in between.
*/

public class ChainableNumbers {

    public boolean isChainable(int[] numbers, int start, int end) {
        if(start == end) {
            return true;
        }

        HashMap<Integer, HashMap<Integer, Integer>> graph = new HashMap<>(numbers.length);
        boolean containsStart = false;
        boolean containsEnd = false;
        boolean containsMagicNumber = false;
        // if start = 1155 and end = 2266 our magicNumber = 5522
        int magicNumber = getKey(start) * 100 + getLock(end);

        // setup "graph" and set if our number list contains start and end
        // Also, check to see if we have the magic number that connects start and end
        for(int i=0;i<numbers.length;i++) {
            int num = numbers[i];
            if(!isFourDigits(num)) {
                continue;
            }

            // can terminate early and return true if all 3 of these are true
            if (num == start) {
                containsStart = true;
            } else if(num == end) {
                containsEnd = true;
            } else if(num == magicNumber) {
                containsMagicNumber = true;
            }

            int lock = getLock(num);
            if(!graph.containsKey(lock)) {
                graph.put(lock, new HashMap<>());
            }

            int key = getKey(num);
            HashMap<Integer, Integer> map = graph.get(lock);
            map.put(key, num);
            graph.put(lock, map);
        }

        if(!containsStart || !containsEnd) {
            return false;
        } else if (containsMagicNumber) {
            return true;
        }

        return findChain(graph, start, end);
    }

    private boolean findChain(HashMap<Integer, HashMap<Integer, Integer>> graph, int start, int end) {
        LinkedList<Integer> queue = new LinkedList<>();
        HashSet<Integer> keyInventory = new HashSet<>(100);

        queue.add(start);
        keyInventory.add(getKey(start));
        while(!queue.isEmpty()) {
            int current = queue.poll();
            if (current == end) {
                return true;
            }

            int currentKey = getKey(current);
            if(!graph.containsKey(currentKey)) {
                continue;
            }

            HashMap<Integer, Integer> keyMap = graph.get(currentKey);
            // Our currentKey can get us to our end
            if(currentKey == getLock(end)) {
                // We have found our end, put it at the front of our queue
                if(keyMap.containsKey(getKey(end))) {
                    queue.push(keyMap.get(getKey(end)));
                    keyInventory.add(getKey(end));
                    continue;
                } else {
                    // Should never get here, otherwise it would mean our end isn't in our map which we've checked for
                    return false;
                }
            // we have a key to a number that holds a key to our end, put that at the front of our queue
            } else if(keyMap.containsKey(getLock(end))) {
                queue.push(keyMap.get(getLock(end)));
                keyInventory.add(getLock(end));
                continue;
            }

            // we only need to queue numbers that give us new keys in our inventory
            // The Sets.SetView will update as we add things to keyInventory
            // this just uses a while loop under the hood, so its still is an O(N) search
            for(Integer newKey : Sets.difference(keyMap.keySet(), keyInventory)) {
                keyInventory.add(newKey);
                queue.add(keyMap.get(newKey));
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
