package interview.coding;

import java.util.Stack;

/*
    Given a string of parens '(' and non parens, determine if the parens are balanced;
    This could be easily extended to other open close characters like '{', '}' and '[', ']'
*/

public class BalancedParens {

    public boolean isBalanced(String string) {
        Stack<Character> stack = new Stack<>();

        for(int i=0;i<string.length();i++) {
            char c = string.charAt(i);
            if (c == '(') {
                stack.push(')');
            } else if( c == ')' && (stack.isEmpty() || stack.pop() != c)) {
                return false;
            }
        }

        return stack.isEmpty();
    }
}
