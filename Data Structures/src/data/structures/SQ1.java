/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;

/**
 *
 * Implement 3 stacks using single array. Fixed allocation. Array divided into 3 parts
 */
public class SQ1 {
    public static void main(String[] args){
        FixedMultiStack f = new FixedMultiStack(3);
        f.push(0, 1);
        f.push(0, 2);
        f.push(1, 2);
        f.push(2, 4);
        f.push(2, 5);
        f.push(2, 6);
        System.out.println(f.pop(2));
    }
}

class FixedMultiStack{
    private int numOfStacks = 3;
    private int stackCapacity;
    private int[] values;
    private int[] sizes;
    
    public FixedMultiStack(int stackSize){
        stackCapacity = stackSize;
        values = new int[stackSize  * numOfStacks];
        sizes = new int[numOfStacks];
    }
    
    
    public void push(int stackNum, int val){
        if(isFull(stackNum)){
            System.out.println("Stack is full");
        }
        
        //increment stack ptr and update top value
        sizes[stackNum]++;
        values[indexOfTop(stackNum)] = val;
    }
    
    public int pop(int stackNum){
        if(isEmpty(stackNum)){
            System.out.println("Stack is empty");
        }
        int topIndex = indexOfTop(stackNum);
        int val = values[topIndex]; // get top val
        values[topIndex] = 0; //clear
        sizes[stackNum]--; //shrink
        return val;
    }
    
    public int peek(int stackNum){
        if(isEmpty(stackNum)){
            System.out.println("Stack is empty");
        }
        return values[indexOfTop(stackNum)];
    }
    
    public boolean isEmpty(int stackNum){
        return sizes[stackNum] == 0;
    }
    
    public boolean isFull(int stackNum){
        return sizes[stackNum] == stackCapacity;
    }
    
    private int indexOfTop(int stackNum){
        int offset = stackNum * stackCapacity; //gives the starting pos of a particular stack
        int size = sizes[stackNum]; //size of a stack till the top pos
        return offset + size - 1; // returns the top index of the stack
        
    }
}
