/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;

/**
 *
 * @author Srivathsan
 */
public class MyQueue {
    public static void main(String[] args){
       QueueClass<Integer> q = new QueueClass<Integer>();
       q.add(5);
       q.add(2);
       q.add(9);
       q.add(4);
       
       q.display(q);
       
       System.out.println("First: " + q.peek());
       q.remove();
        System.out.println("After removing first element");
       q.display(q);
       System.out.println("First: " + q.peek());
       
   }
}

class QueueClass<T> {
   
    private class Node<T>{
        private T data;
        private Node<T> next;
        
        public Node(T data){
            this.data = data;
        }
    }
    
    private Node<T> first;
    private Node<T> last;
    
    public T remove(){
        if(first == null){
            System.out.println("Queue is empty");
            
        }else{
          T data = first.data;
          first = first.next;
          if(first == null){
              last = null;
          }
          return data;
        }
        
        return null;
    }
    
    public void add(T item){
        Node<T> t = new Node<T>(item);
        
        if(last != null){
            last.next = t;
        }
        last = t;
        if(first == null){
            first = last;
        }
        
    }
    
    public T peek(){
       if(first == null){
           System.out.println("Queue is empty");
       }else{
           return first.data;
       }
       return null;
    }
    
    public boolean isEmpty(){
        return first==null;
    }
    
    public void display(QueueClass s){
        
        Node temp = first;
        while(temp.next != null){
            System.out.print(temp.data);
            temp = temp.next;
        }
        System.out.println(temp.data);
    }
           
}

