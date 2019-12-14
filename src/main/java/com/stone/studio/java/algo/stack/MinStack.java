package com.stone.studio.java.algo.stack;

import java.util.Stack;

/**
 * 最小栈支持常量时间内获取栈最小值（注意不是要求pop出来的是最小值）
 */
public class MinStack<T extends Comparable<T>> {
    private Stack<T> dataStack = new Stack<T>();
    private Stack<Integer> indexStack = new Stack<Integer>(); // 最小数在data栈中的位置

    public static void main(String[] args) {
        MinStack<Integer> minStack = new MinStack<Integer>();
        minStack.push(3);
        //min:3
        System.out.println("min:" + minStack.getMin());

        minStack.push(5);

        //min:3
        System.out.println("min:" + minStack.getMin());

        minStack.push(2);
        //min:2
        System.out.println("min:" + minStack.getMin());

        minStack.pop();
        //min:3
        System.out.println("min:" + minStack.getMin());

        minStack.push(8);
        // min:3
        System.out.println("min:" + minStack.getMin());
    }

    public void push(T data) {
        dataStack.push(data);

        if(dataStack.size() == 1) { // 第一次push
            indexStack.push(0);
        } else { // 多于1一个元素时需要跟最小值做比较
            T min = getMin();
            if(data.compareTo(min) < 0 ) {
                int index = dataStack.indexOf(data);
                indexStack.push(index);
            }
        }
    }

    public T pop() {
        T data = dataStack.peek();
        int index = dataStack.indexOf(data);
        dataStack.pop();
        if(indexStack.peek() == index) {
            indexStack.pop();
        }

        return data;
    }

    public T getMin() {
        int index = indexStack.peek();
        return dataStack.elementAt(index);
    }
}
