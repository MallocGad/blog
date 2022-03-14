package top.ht.design;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @Author: ht
 * @Date: Create in 22:58 2022/3/14
 * @Describe:
 * @Last_change:
 */
class MinStack {

    LinkedList<Integer> stack = new LinkedList<>();
    LinkedList<Integer> minStack = new LinkedList<>();
    public MinStack() {
        minStack.push(Integer.MIN_VALUE);
    }

    public void push(int val) {
        stack.push(val);
        Integer peek = minStack.peek();
        minStack.push(Math.min(minStack.peek(),val));
    }

    public void pop() {

    }

    public int top() {

    }

    public int getMin() {

    }
}
