package leetcode;

import java.util.Stack;

/**
 * 232. 用栈实现队列
 */
public class MyQueue {

    private Stack<Integer> stackA;
    private Stack<Integer> stackB;

    public MyQueue() {
        stackA = new Stack<>();
        stackB = new Stack<>();
    }

    public void push(int x) {
        stackB.push(x);
    }

    public int pop() {
        if (stackA.isEmpty()) {
            while (!stackB.isEmpty()) {
                stackA.push(stackB.pop());
            }
        }
        return stackA.pop();
    }

    public int peek() {
        if (stackA.isEmpty()) {
            while (!stackB.isEmpty()) {
                stackA.push(stackB.pop());
            }
        }
        return stackA.peek();
    }

    public boolean empty() {
        return stackA.isEmpty() && stackB.isEmpty();
    }
}
