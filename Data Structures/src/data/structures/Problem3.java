/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;

/**
 *
 * Implement an algorithm to find the kth to last element in a singly linkedlist
 * for e.g., k = 1, return last element, k = 2 , return second to last element
 */
public class Problem3 {
   
    static Node1 first;
    public static void main(String[] args) {
       Problem3 p = new Problem3();
       p.insertLast(1);
       p.insertLast(2);
       p.insertLast(3);
       p.insertLast(4);
       p.insertLast(5);
       p.display();
      
       
       Node1 node = p.nthToLast(first, 1);
        System.out.println("");
       System.out.println("Returned Node data");
       System.out.println(node.data);
       
      
       
       
    }

    //use runner method. place pointers k nodes apart initially, and then move each at same pace, when one hits the end , other will be
    //at k nodes from the end or length - k nodes into the list
    Node1 nthToLast(Node1 first, int k){
        Node1 p1 = first;
        Node1 p2 = first;
        
        //Move p1 k nodes into the list
        for(int i= 0; i < k ; i++){
            if(p1 == null) return null; //out of bounds
            p1 = p1.next;
        }
        
        //Move them at same pace. When p1 hits the end, p2 wull be at k - which is the reqd answer
        while(p1!= null){
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
        
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

        







