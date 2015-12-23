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
public class MyLinkedList {
     public static void main(String[] args){
       LinkedListClass<Integer> s = new LinkedListClass<Integer>();
       s.insertFirst(3);
       s.insertFirst(2);
       s.insertFirst(9);
       s.insertFirst(4);
       
       s.display(s);
       
       System.out.println("Element 2 is present at position " + s.find(2));
       System.out.println("Deleting the first node");
       s.deleteFirst();
       s.display(s);
       System.out.println("Deleting the node with value 2");
       s.delete(2);
       s.display(s);
       
       
       
   }
}

class LinkedListClass<T> {
   
    private class Node<T>{
        private T data;
        private Node<T> next;
        
        public Node(T data){
            this.data = data;
        }
    }
    
    private Node<T> first;
    private int size;
   
    public void insertFirst(T data){
        Node<T> node = new Node<T>(data);
        node.next = first;
        first = node;
        size++;
    }
    
    public Node<T> deleteFirst(){
        Node<T> temp = first;
        first = first.next;
        size--;
        return temp;
    }
    
    public int find(T key){
        Node<T> current = first;
        int pos = 1;
        while(!current.data.equals(key)){
            if(current.next == null){
                return 0;
            }else{
                current = current.next;
                pos++;
            }
        }
        
        return pos;
    }
    
    public Node<T> delete(T key){
        Node<T> current = first;
        Node<T> previous = first;
        
        while(current.data != key){
            if(current.next == null){
                return null;
            }else{
                previous = current;
                current = current.next;
            }
        } 
        if(current == first){
            first = first.next;
        }else{
            previous.next = current.next;
        }
       
        return current;
    }
    
    
    
    public void display(LinkedListClass s){
        
        Node temp = first;
        while(temp.next != null){
            System.out.print(temp.data);
            temp = temp.next;
        }
        System.out.println(temp.data);
    }
           
}

