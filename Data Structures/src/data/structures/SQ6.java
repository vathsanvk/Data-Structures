/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;
import java.util.Stack;

/**
 *
 * implement a queue using two stacks
 * we use one stack where new elements are on the top and the other where old elements on the top
 * enqueue to new stack and dequeue from old stack. when old is empty fill contents from new stack
 */
public class SQ6 {
    public static void main(String[] args){
        MyNewQueue<Integer> m = new MyNewQueue<>();
        m.add(1);
        m.add(2);
        m.add(3);
        m.add(4);
        System.out.println("Removed element from queue: " + m.remove());
        m.add(5);
        System.out.println("Removed element from queue: " + m.remove());
        System.out.println("Removed element from queue: " + m.remove());
        System.out.println("Removed element from queue: " + m.remove());
        System.out.println("Removed element from queue: " + m.remove());
        
    }
}

class MyNewQueue<T>{
    Stack<T> newStack, oldStack;
     public MyNewQueue(){
        newStack = new Stack<T>();
        oldStack = new Stack<T>();
    }
     
     public int size(){
         return newStack.size() + oldStack.size();
     }
     
     public void add(T value){
         //push new values to new stack
         newStack.push(value);
     }
     
     //moving elements from new to old stack
     private void shiftStacks(){
         if(oldStack.isEmpty()){
             while(!newStack.isEmpty()){
                 oldStack.push(newStack.pop());
             }
         }
     }
     
     public T peek(){
         shiftStacks();
         return oldStack.peek();
     }
     
     public T remove(){
         shiftStacks();
         return oldStack.pop();
     }
   
}