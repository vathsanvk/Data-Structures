/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;
import java.util.Stack;

/**
 *
 * Program to sort a stack such that smallest items on the top. Can use extra stack but not any other data structure
 */
public class SQ7 {
    public static void main(String[] args){
        Stack<Integer> s = new Stack<>();
        s.push(1);
        s.push(3);
        s.push(2);
        s.push(4);
        
        Stack<Integer> result = sort(s);
        
        System.out.println("Sorted stack: ");
        
        while(!result.isEmpty()){
            System.out.print(result.pop());
        }
        
        
    }
    
    static Stack<Integer> sort(Stack<Integer> s){
        Stack<Integer> r = new Stack<Integer>();
        while(!s.isEmpty()){
            int temp = s.pop();
            while(!r.isEmpty() && r.peek() > temp){
                s.push(r.pop());
            }
            r.push(temp);
        }
        return r;
    }
}

