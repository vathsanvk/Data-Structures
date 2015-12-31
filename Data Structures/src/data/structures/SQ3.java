/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;

import java.util.Stack;

/**
 *
 * Design a stack in addition to push and pop, it should also return min element, all in O(1) time
 * This program uses a stack of objects , where in each object keeps track of the min values until that point
 */
public class SQ3 {
    public static void main(String[] args){
        StackWithMin s = new StackWithMin();
        s.push(2);
        s.push(5);
        s.push(1);
       
        
        System.out.println("Minimun element: " + s.min());
        s.pop();
        System.out.println("Minimun element: " + s.min());
    }
}

class StackWithMin extends Stack<NodeWithMin>{
    public void push(int val){
        int newMin = Math.min(val, min());
        super.push(new NodeWithMin(val, newMin));
    }
    
    public int min(){
        if(this.isEmpty()){
            return Integer.MAX_VALUE;
        }else{
            NodeWithMin obj = peek();
            return obj.min;
        }
    }
}

class NodeWithMin{
    int value;
    int min;
    public NodeWithMin(int v, int min){
        value = v;
        this.min = min;
    }
}
