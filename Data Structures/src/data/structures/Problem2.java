/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;

import static data.structures.Problem1.first;
import java.util.HashSet;

/**
 *
 * Implement algo to delete a node in the middle(i.e. any node but first and last, not exactly middle) of a singly linked list, given
 * only access to that node
 *ip: abcdef  , op: abdef
 */
public class Problem2 {
    
    static Node1 first;
    public static void main(String[] args) {
       Problem2 p = new Problem2();
       p.insertLast(1);
       p.insertLast(2);
       p.insertLast(3);
       p.insertLast(4);
       p.insertLast(5);
       p.display();
       Node1 curr = first;
       for(int i = 0 ; i < 2; i++){ //passing third node to be deleted
           curr = curr.next;
           
       }
       p.deleteNode(curr);
       System.out.println("");
       p.display();
      
       
       
    }

    //copy data from next node to the current node and delete the next node
    void deleteNode(Node1 n) {
       
       if(n == null || n.next == null){
           return;
       }
       n.data = n.next.data;
       n.next = n.next.next;
       

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

        






