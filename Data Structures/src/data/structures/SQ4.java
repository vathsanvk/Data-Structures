/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;

import java.util.Stack;

/**
 *
 * Design a stack in addition to push and pop, it should also return min element, all in O(1) time.
 * This program uses an additional stack to keep track of the mins
 */
public class SQ4 {
     public static void main(String[] args){
        StackWithMin2 s = new StackWithMin2();
        s.push(2);
        s.push(5);
        s.push(1);
       
        
        System.out.println("Minimun element: " + s.min());
        s.pop();
        System.out.println("Minimun element: " + s.min());
    }
}

class StackWithMin2 extends Stack<Integer>{
    Stack<Integer> s2;
    public StackWithMin2(){
        s2 = new Stack<Integer>();
    }
    
    public void push(int val){
        if(val <= min()){
            s2.push(val);
        }
        super.push(val);
    }
    
    public Integer pop(){
        int val = super.pop();
        if( val == min()){
            s2.pop();
        }
        return val;
    }
    
    public int min(){
        if(s2.isEmpty()){
            return Integer.MAX_VALUE;
        }else{
            return s2.peek();
        }
    }
    
}


