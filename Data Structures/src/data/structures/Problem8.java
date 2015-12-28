/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;

/**
 *
 * Given a list with loop, find the node at the beginning of the loop
 * e.g., 1->2->3->4->3 (earlier 3) gives 3 as answer
 */
public class Problem8 {
    static Node1 first,third;
    int size=0;
    public static void main(String[] args) {
       Problem8 p = new Problem8();
       p.insertLast(1);
       p.insertLast(2);
       p.insertLast(3);
       p.insertLast(4);
       p.insertLast(5);
       p.insertLast(6);
       p.loop();
       
       p.display();
      
       System.out.println("");
        System.out.println("Loop starts at: " + p.findBeginning(first).data);
      
      
       
       
    }

    /*
      1. Create two pointers. fast and slow
      2. Move fast at a rate of 2 steps and slow at a rate of 1
      3. When they collide, move slow to head. Keep fast where it is
      4. Move slow and fast at a rate of one step.Return the new collision point
    */
    Node1 findBeginning(Node1 head) {
       
       Node1 slow = head;
       Node1 fast = head;
       
       // find meeting point
       while(fast != null && fast.next != null){ //if fast.next == null, no loop
           slow= slow.next;
           fast = fast.next.next;
           if(slow == fast){  //collision
               break;
           }
           
       }
       
       //error check, if no meeting point, no loop
       if(fast == null || fast.next == null){
           return null;
       }
       
       slow= head;
       while(slow != fast){
           slow = slow.next;
           fast = fast.next;
       }
       //both now point to start of the loop
       return fast;

    }
    
    
    public void insertLast(int data){
        Node1 node = new Node1(data);
        Node1 curr;
        if(first == null)
            first = node;
        else{
            curr = first;
            while(curr.next != null){
                curr = curr.next;
            }
            curr.next = node;
        }
        size++;
       
        if(size == 3){
            third = node ;
        }
        
        
        
    }
    
    void loop(){
        Node1 curr = first;
        while(curr.next != null){
            curr = curr.next;
        }
        curr.next = third;
    }
    
   
    public void display(){
        Node1 curr = first;
        while(size > 0){
            System.out.print(curr.data);
            curr = curr.next;
            size--;
        }
        System.out.print(curr.data);
    }

}

        







