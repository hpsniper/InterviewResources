package trees;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class BTree {
    AbstractBNode head;

    BNode add(BNode node) {
        if(head.isEmpty()) {
            head = node;
            return node;
        }

        return add((BNode) head, node);
    }

    BNode add(BNode treeNode, BNode node) {
        Queue<BNode> queue = new PriorityQueue<>();
        queue.add(treeNode);

        BNode workingNode;
        while(!queue.isEmpty()) {
            workingNode = queue.poll();
            if(workingNode.leftNode.isEmpty()) {
                workingNode.leftNode = node;
                return node;
            }

            if(workingNode.rightNode.isEmpty()) {
                workingNode.rightNode = node;
                return node;
            }

            queue.add((BNode) workingNode.leftNode);
            queue.add((BNode) workingNode.rightNode);
        }

        throw new RuntimeException("Could Not Add Node to Tree");
    }

    boolean BreadthFirstSearch(BNode node) {
        if (head.isEmpty()) {
            return false;
        }

        Queue<BNode> queue = new PriorityQueue<>();
        queue.add((BNode) head);

        BNode workingNode;
        while(!queue.isEmpty()) {
            workingNode = queue.poll();
            if(workingNode.value == node.value) {
                return true;
            }

            if(!workingNode.leftNode.isEmpty()) {
                queue.add((BNode) workingNode.leftNode);
            }

            if(workingNode.rightNode.isEmpty()) {
                queue.add((BNode) workingNode.rightNode);
            }
        }

        return false;
    }

    boolean DepthFirstSearchPreorder(BNode node) {
        if(head.isEmpty()) {
            return false;
        }

        Stack<BNode> stack = new Stack<>();
        stack.add((BNode) head);

        BNode workingNode;
        while (!stack.isEmpty()) {
            workingNode = stack.pop();
            if(workingNode.value == node.value) {
                return true;
            }

            if(workingNode.rightNode.isEmpty()) {
                stack.push((BNode) workingNode.rightNode);
            }

            if(!workingNode.leftNode.isEmpty()) {
                stack.push((BNode) workingNode.leftNode);
            }
        }

        return false;
    }

    boolean DepthFirstSearchInOrder(BNode node) {
        if(head.isEmpty()) {
            return false;
        }

        Stack<BNode> stack = new Stack<>();
        BNode headNode = (BNode) head;
        if(!headNode.rightNode.isEmpty()) {
            stack.add((BNode) headNode.rightNode);
        } else {
            stack.add(headNode);
        }

        BNode workingNode;
        while (!stack.isEmpty()) {
            workingNode = stack.pop();
            if(workingNode.value == node.value) {
                return true;
            }

            if(workingNode.rightNode.isEmpty()) {
                stack.push((BNode) workingNode.rightNode);
            }

            if(!workingNode.leftNode.isEmpty()) {
                stack.push((BNode) workingNode.leftNode);
            }
        }

        return false;
    }

    void printInLevelOrder() {

    }
}
