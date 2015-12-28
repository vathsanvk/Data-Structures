/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;

/**
 *
 * Partition a linkedlist around a value x, x can be anywhere in the bigger list, not necessary to be in the center
 */
public class Problem4 {
  static Node1 first;
    public static void main(String[] args) {
       Problem4 p = new Problem4();
       p.insertLast(1);
       p.insertLast(2);
       p.insertLast(3);
       p.insertLast(7);
       p.insertLast(5);
       p.insertLast(6);
       p.display();
      
       
       Node1 n = p.partition(first, 5);
       System.out.println("");
       
       Node1 curr = n;
       while(curr.next != null){
            System.out.print(curr.data);
            curr = curr.next;
        }
        System.out.print(curr.data);
      
       
       
    }

    //create a new list using existing list, with elements lesser added to head and greater than x, added to the tail
    Node1 partition(Node1 node, int x){
        Node1 head = node;
        Node1 tail = node;
        
    
        while(node != null){
            Node1 next = node.next;  //store next value since we are modifying it
            if(node.data < x){
                node.next = head;
                head = node;
            }else{
                tail.next = node;
                tail = node;
            }
            node = next;
        }
        tail.next = null;
        
        
        return head;
    }
   
    
    
    public void insertLast(int data){
        Node1 node = new Node1(data);
        if(first == null)
            first = node;
        else{
            Node1 curr = first;
            while(curr.next != null){
                curr = curr.next;
            }
            curr.next = node;
        }
        
        
    }
    
   
    public void display(){
        Node1 curr = first;
        while(curr.next != null){
            System.out.print(curr.data);
            curr = curr.next;
        }
        System.out.print(curr.data);
    }

}

        







