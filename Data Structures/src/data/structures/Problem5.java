/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;

import java.util.Stack;

/**
 *
 * @author Srivathsan
 */
public class Problem5 {

    static Node1 first;

    public static void main(String[] args) {
        Problem5 p = new Problem5();
        p.insertLast(1);
        p.insertLast(2);
        p.insertLast(3);
        p.insertLast(2);
        p.insertLast(1);
        p.display();
      
        System.out.println("");
        System.out.println("The list is a palindrome: "  + p.isPalindrome(first));
        

    }

    //traverse first half while putting them inside a stack, when u traverse second half compare the elements
    //to know that we are in the middle , we use the runner method
    boolean isPalindrome(Node1 head) {
        Node1 fast = head;
        Node1 slow = head;

        Stack<Integer> stack = new Stack<Integer>();

        while (fast != null && fast.next != null) {
            stack.push(slow.data);
            slow = slow.next;
            fast = fast.next.next;
        }

        //if it has odd number of elements, skip the middle element
        if (fast != null) {
            slow = slow.next;
        }

        while (slow != null) {
            int top = stack.pop().intValue();

            if (top != slow.data) {
                return false;
            }

            slow = slow.next;
        }
        return true;

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


