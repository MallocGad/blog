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
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int val) {
        stack.push(val);
        Integer peek = minStack.peek();
        // 取巧，如果当前元素不是最小的那么再push一次重复的，后续弹出也是弹出重复的
        minStack.push(Math.min(minStack.peek(),val));
    }

    public void pop() {
        minStack.pop();
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
