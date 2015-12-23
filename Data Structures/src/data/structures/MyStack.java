/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;


public class MyStack{
   public static void main(String[] args){
       StackClass<Integer> s = new StackClass<Integer>();
       s.push(3);
       s.push(2);
       s.push(9);
       s.push(4);
       
       s.display(s);
       
       System.out.println("Top: " + s.peek());
       s.pop();
       System.out.println("Top: " + s.peek());
       
   }
}


class StackClass<T> {
   
    private class Node<T>{
        private T data;
        private Node<T> next;
        
        public Node(T data){
            this.data = data;
        }
    }
    
    private Node<T> top;
   
    
    public T pop(){
        if(top == null){
            System.out.println("Stack is empty");
            
        }else{
            T item = top.data;
            top = top.next;
           
            return item;
        }
        
        return null;
    }
    
    public void push(T item){
        Node<T> t = new Node<T>(item);
        t.next = top;
        top = t;
       
        
    }
    
    public T peek(){
        if(top == null){
            System.out.println("Stack is empty");
        }else{
            return top.data;
        }
        return null;
    }
    
    public boolean isEmpty(){
        return top==null;
    }
    
    public void display(StackClass s){
        
        Node temp = top;
        while(temp.next != null){
            System.out.print(temp.data);
            temp = temp.next;
        }
        System.out.println(temp.data);
    }
           
}
