/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;

import java.util.HashSet;

/**
 *
 * Write code to remove duplicates from an unsorted list. With and without
 * temporary buffer
 */
public class Problem1 {

    static Node1 first;
    public static void main(String[] args) {
       Problem1 p = new Problem1();
       p.insertLast(1);
       p.insertLast(3);
       p.insertLast(3);
       p.insertLast(4);
       p.insertLast(4);
       p.display();
       System.out.println("Deleting using hashset method");
       p.deleteDupsHashset(first);
       p.display();
       System.out.println("");
       first = null;
      
       p.insertLast(1);
       p.insertLast(3);
       p.insertLast(3);
       p.insertLast(4);
       p.insertLast(4);
       p.display();
       System.out.println("Deleting using runner method");
       p.deleteDupsNoBuffer(first);
       p.display();
       
       
    }

    //using hashset
    void deleteDupsHashset(Node1 n) {
        HashSet<Integer> set = new HashSet<Integer>();
        Node1 previous = null;
        while(n != null){
            if(set.contains(n.data)){
                previous.next = n.next;
            }else{
                set.add(n.data);
                previous = n;
            }
            n = n.next;
        }

    }
    
    //use two pointers, one gors thru the inner loop 
    void deleteDupsNoBuffer(Node1 first){
        Node1 curr = first;
        while(curr != null){
            //look for similar node and remove if duplicate
            Node1 runner = curr;
            while(runner.next != null){
                if(runner.next.data == curr.data){
                    runner.next = runner.next.next;
                }else{
                    runner = runner.next;
                }
                
            }
            curr = curr.next;
        }
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
class Node1{
     public int data;
     public Node1 next;
     
     public Node1(int item){
         data = item;
     }
        
}




