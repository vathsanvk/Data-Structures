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
public class Problem6 {

    static Node1 first;

    public static void main(String[] args) {
        Problem6 p = new Problem6();
        p.insertLast(1);
        p.insertLast(2);
        p.insertLast(3);
        p.insertLast(4);
        p.insertLast(5);
        p.display();
       
    }

    /*
     1. run thru the lists to get the lengths and the tails
     2. compare the tails. If they are different (by references, not values), return immediately. There is no intersection
     3. Set the two pointers to the start of the lists
     4. On the longer list, advance its pointer by the difference in lengths
     5. Now traverse, each list until the pointers are the same
    */
    
    Node1 findIntersection(Node1 l1, Node1 l2){
        if(l1 == null || l2 == null  ) return null;
        
        //get tail and sizes
        
        Result r1 = getTailAndSize(l1);
        Result r2 = getTailAndSize(l2);
        
        
        //if diff tail nodes, no intersection
        if(r1.tail != r2.tail){
            return null;
        }
        Node1 shorter,longer;
        //set pointers to start of each list
        if(r1.size < r2.size){
            shorter = l1;
            longer = l2;
        }else{
            shorter = l2;
            longer = l1;
        }
        
        //advance pointer for longer list by diff in lengths
        longer = getKthNode(longer, Math.abs(r1.size - r2.size));
        
        //move both pointers until collision
        
        while(shorter != longer){
            shorter = shorter.next;
            longer = longer.next;
        }
        
        //return either one
        
        return longer;
    }
    
    Result getTailAndSize(Node1 list){
        if(list == null) return null;
        
        int size = 1;
        Node1 curr = list;
        while(curr.next != null){
            size++;
            curr = curr.next;
        }
        
        return new Result(curr, size);
    }
    
    Node1 getKthNode(Node1 head, int k){
        Node1 curr = head;
        while(k > 0 && curr != null){
            curr = curr.next;
            k--;
        }
        return curr;
    }

    public void insertLast(int data) {
        Node1 node = new Node1(data);
        if (first == null) {
            first = node;
        } else {
            Node1 curr = first;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = node;
        }

    }

    public void display() {
        Node1 curr = first;
        while (curr.next != null) {
            System.out.print(curr.data);
            curr = curr.next;
        }
        System.out.print(curr.data);
    }

}

class Result{
    public Node1 tail;
    public int size;
    
    public Result(Node1 tail, int size){
        this.tail = tail;
        this.size = size;
    }
}
