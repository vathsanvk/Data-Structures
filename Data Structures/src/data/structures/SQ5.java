/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;

import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 *
 * STack of Plates. If the stack gets too high,create a new stack push and pop
 * operations should behave identically to a single stack
 *
 * Also implement popAt function which performs pop operation on specific
 * substack
 */
public class SQ5 {
   public static void main(String[] args){
       SetOfStacks s = new SetOfStacks(2);
       s.push(1);
       s.push(7);
       s.push(3);
       s.push(4);
       
       System.out.println("Top element at first stack: " + s.popAt(0));
        System.out.println("Top element at first stack: " + s.popAt(0));
       
   }
}

class SetOfStacks {

    ArrayList<Stack> stacks = new ArrayList<Stack>();
    int capacity;

    public SetOfStacks(int capacity) {
        this.capacity = capacity;
    }

    public Stack getLastStack() {
        if (stacks.size() == 0) {
            return null;
        }
        return stacks.get(stacks.size() - 1);
    }

    public void push(int v) {
        Stack last = getLastStack();
        if (last != null && !(last.isFull())) {
            last.push(v);
        } else {
            Stack stack = new Stack(capacity);
            stack.push(v);
            stacks.add(stack);
        }
    }

    public int pop() {
        Stack last = getLastStack();
        if (last == null) {
            throw new EmptyStackException();
        }
        int v = last.pop();
        if (last.size == 0) {
            stacks.remove(stacks.size() - 1);
        }
        return v;

    }
    
    public boolean isEmpty(){
        Stack last = getLastStack();
        return last == null || last.isEmpty();
    }
    
    public int popAt(int index){
        return leftShift(index, true);
    }
    
    public int leftShift(int index, boolean removeTop){
        Stack stack = stacks.get(index);
        int removed_item;
        if(removeTop) removed_item = stack.pop();
        else removed_item = stack.removeBottom();
        if(stack.isEmpty()){
            stacks.remove(index);
        }else if(stacks.size() > index + 1){
            int v = leftShift(index + 1, false);
            stack.push(v);
        }
        return removed_item;
    }
}

class Stack {

    private int capacity;
    public Node3 top, bottom;
    public int size = 0;

    public Stack(int capacity) {
        this.capacity = capacity;
    }

    public boolean isFull() {
        return capacity == size;
    }

    public void join(Node3 above, Node3 below) {
        if (below != null) {
            below.above = above;

        }

        if (above != null) {
            above.below = below;
        }
    }

    public boolean push(int v) {
        if (size >= capacity) {
            return false;
        }
        size++;
        Node3 n = new Node3(v);
        if (size == 1) {
            bottom = n;
        }
        join(n, top);
        top = n;
        return true;
    }

    public int pop() {
        Node3 t = top;
        top = top.below;
        size--;
        return t.value;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int removeBottom() {
        Node3 b = bottom;
        bottom = bottom.above;

        if (bottom != null) {
            bottom.below = null;
        }
        size--;
        return b.value;
    }
}

class Node3{
    Node3 above, below;
    int value;
    public Node3(int v){
        value = v;
    }
}
