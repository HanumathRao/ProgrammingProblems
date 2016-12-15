package com.company;

import java.util.Stack;
import java.util.Random;
/**
 * Created by hmaduri on 6/4/16.
 */
public class StackMinInConstSpace {
    public static void main(String[] args) {
        Stack stk = new Stack();
        int Iterations = 10;
        int SEED = 135;
        Random randInd = new Random();
        for (int index=0; index<Iterations;index++) {
            int element = randInd.nextInt(50);
            System.out.println("pushed element:" + element);
            stk.push(element);
            System.out.println("Minimum element:"+stk.getMin());
        }
        System.out.println("popping the elements");
        for (int index=0; index<Iterations;index++) {
            System.out.println("Minimum element:"+ stk.getMin());
            int element = stk.pop();
            System.out.println("Popped element:"+ element);
        }
    }

    public static class Stack{
        private Integer minEle = null;
        private java.util.Stack<Integer> stk = new java.util.Stack<>();

        public Stack() {}

        public void push(int ele) {
            if (stk.isEmpty() || ele>=minEle) {
                if (stk.isEmpty())
                    minEle = ele;
                stk.push(ele);
            } else {
                stk.push(2*ele - minEle);
                minEle = ele;
            }
        }

        public int pop() {
            assert(!stk.isEmpty());
            if (stk.peek() < minEle) {
                minEle = 2*minEle - stk.pop();
                return minEle;
            }
            return stk.pop();
        }

        public int getMin(){
            return minEle;
        }
    }
}
