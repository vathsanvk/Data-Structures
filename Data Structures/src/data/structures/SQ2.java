/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;

/**
 *
 * Implement 3 stacks using single array. Flexible allocation. Array divided
 * into 3 parts
 */
public class SQ2 {

    public static void main(String[] args) {
        MultiStack f = new MultiStack(3,3);
        f.push(0, 1);
        f.push(0, 2);
        f.push(0, 3);
        f.push(1, 2);
        f.push(1, 2);
        f.push(1, 2);
        f.push(2, 4);
        f.push(2, 5);
        f.push(2, 6);
       
        System.out.println(f.pop(2));
    }
}

class MultiStack {

    private class StackInfo {  //holds the info of each stack, does not hold the actual items

        int start, size, capacity;

        public StackInfo(int start, int capacity) {
            this.start = start;
            this.capacity = capacity;
        }

        //check if index of full array is within stack boundaries. Stack can wrap around to the start of the array
        public boolean isWithinStackCapacity(int index) {
            if (index < 0 || index >= values.length) {
                return false;
            }

            //if index wraps around, adjust it
            int contiguousIndex = index < start ? index + values.length : index;
            int end = start + capacity;
            return start <= contiguousIndex && contiguousIndex < end;
        }

        public int lastCapacityIndex() {
            return adjustIndex(start + capacity - 1);
        }

        public int lastElementIndex() {
            return adjustIndex(start + size - 1);
        }

        public boolean isFull() {
            return size == capacity;
        }

        public boolean isEmpty() {
            return size == 0;
        }

    }

    private StackInfo[] info;
    private int[] values;

    public MultiStack(int numOfStacks, int defaultSize) {

        //create metadata for all the stacks
        info = new StackInfo[numOfStacks];
        for (int i = 0; i < numOfStacks; i++) {
            info[i] = new StackInfo(defaultSize * i, defaultSize);
        }

        values = new int[numOfStacks * defaultSize];
    }

    //push value onto stack num, shift/expand if necessary, shows error when all stacks are full
    public void push(int stackNum, int value) {
        if (allStacksAreFull()) {
            System.out.println("All stacks are full");
            throw new StackOverflowError();
        }

        //if this stack is full, expand it
        StackInfo stack = info[stackNum];
        if (stack.isFull()) {
            expand(stackNum);
        }

        //find index of the top element in array + 1, and increment the stack ptr
        stack.size++;
        values[stack.lastElementIndex()] = value;
    }

    //remove value from stack
    public int pop(int stackNum) {
        StackInfo stack = info[stackNum];
        if (stack.isEmpty()) {
            System.out.println("Stack is empty");
        }

        //remove last element
        int value = values[stack.lastElementIndex()];
        values[stack.lastElementIndex()] = 0; // clear item
        stack.size--; //shrink
        return value;
    }

    //get top element from a stack
    public int peek(int stackNum) {
        StackInfo stack = info[stackNum];
        return values[stack.lastElementIndex()];
    }

    /*
     shift items in stock over by one element. If we have available capacity, then we will end up shrinking the stack by one
     element. If we dont have available space we need to shift the next stack over too
     */
    private void shift(int stackNum) {
        System.out.println(" Shifting stack: " + stackNum);
        StackInfo stack = info[stackNum];
        //if this stack is full, then we need to move next stack by one element. This stack can now claim the freed index
        if (stack.size >= stack.capacity) {
            int nextStack = (stackNum + 1) % info.length;
            shift(nextStack);
            stack.capacity++; //claim index that next stack lost
        }

        //shift all elements in the stack one by one
        int index = stack.lastCapacityIndex();
        while (stack.isWithinStackCapacity(index)) {
            values[index] = values[previousIndex(index)];
            index = previousIndex(index);
        }

        //adjust stack data
        values[stack.start] = 0; // clear item
        stack.start = nextIndex(stack.start); //move start
        stack.capacity--; // shrink capacity

    }

    //expand stack by shifting over other stacks
    private void expand(int stackNum) {
        shift((stackNum + 1) % info.length);
        info[stackNum].capacity++;
    }

    //returns number if items in all the stacks
    public int numberOfElements() {
        int size = 0;
        for (StackInfo sd : info) {
            size += sd.size;
        }

        return size;
    }

    //returns true if all stacks are full
    public boolean allStacksAreFull() {
        return numberOfElements() == values.length;
    }

    //adjust index to be within range of 0 to length - 1
    private int adjustIndex(int index) {
        int max = values.length;
        return ((index % max) + max) % max;
    }

    //get index after this index, adjusted for wrap around
    private int nextIndex(int index) {
        return adjustIndex(index + 1);
    }

    //get index before this index, adjusted for wrap around
    private int previousIndex(int index) {
        return adjustIndex(index - 1);
    }

}
