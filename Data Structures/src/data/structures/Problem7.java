/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;

import static data.structures.Problem2.first;

/**
 *
 * add two numbers represented by a linked list, backward (1->2->3 means number
 * 321)and forward (vice versa as bef)
 */
public class Problem7 {

    static Node2 firstList;
    static Node2 secondList;

    public static void main(String[] args) {
        Problem7 p = new Problem7();
        p.insertLast1(7);
        p.insertLast1(1);
        p.insertLast1(6);

        p.insertLast2(5);
        p.insertLast2(9);
        p.insertLast2(2);

        System.out.println("Number represented backwards");
        p.display(firstList);
        System.out.println("");
        p.display(secondList);
        System.out.println("");
        Node2 n = p.addLists1(firstList, secondList, 0);
        Node2 curr = n;
        while (curr.next != null) { //passing third node to be deleted
            System.out.print(curr.data);
            curr = curr.next;

        }
        System.out.println(curr.data);
        System.out.println("Number represented forwards");

        firstList = null;
        secondList = null;
        p.insertLast1(6);
        p.insertLast1(1);
        p.insertLast1(7);

        p.insertLast2(2);
        p.insertLast2(9);
        p.insertLast2(5);
        p.display(firstList);
        System.out.println("");
        p.display(secondList);
        System.out.println("");
        Node2 n1 = p.addLists2(firstList, secondList);
        Node2 curr1 = n1;
        while (curr1.next != null) { //passing third node to be deleted
            System.out.print(curr1.data);
            curr1 = curr1.next;

        }
        System.out.println(curr1.data);
    }

    //if numbers are represented backward, just iterrate thru list and carry over to the end
    Node2 addLists1(Node2 l1, Node2 l2, int carry) {
        if (l1 == null && l2 == null && carry == 0) {
            return null;
        }
        Node2 result = new Node2();
        int val = carry;
        if (l1 != null) {
            val += l1.data;
        }

        if (l2 != null) {
            val += l2.data;
        }

        result.data = val % 10;

        //recurse
        if (l1 != null || l2 != null) {
            Node2 more = addLists1(l1 == null ? null : l1.next, l2 == null ? null : l2.next, val >= 10 ? 1 : 0);
            result.setNext(more);
        }

        return result;
    }

    /*when number is represented forward, code gets a little complicated when one of the list is short.
     In the previous case results are carried forward (added to tail). In this case results are added to the head (carried backward)
     Recursive call should return the result as well as the carry.
     */
    Node2 addLists2(Node2 l1, Node2 l2) {
        int len1 = length(l1);
        int len2 = length(l2);

        ///pad the shorter list with zeros
        if (len1 < len2) {
            l1 = padList(l1, len2 - len1);
        } else {
            l2 = padList(l2, len1 - len2);
        }

        //add lists
        PartialSum sum = addListHelper(l1, l2);

        //if there is a carryover left, add it at the front, else return the list
        if (sum.carry == 0) {
            return sum.sum;
        } else {
            Node2 result = insertBefore(sum.sum, sum.carry);
            return result;
        }

    }

    PartialSum addListHelper(Node2 l1, Node2 l2) {
        if (l1 == null && l2 == null) {
            PartialSum sum = new PartialSum();
            return sum;
        }

        //add smaller digits recursively
        PartialSum sum = addListHelper(l1.next, l2.next);

        //add carry to current data
        int val = sum.carry + l1.data + l2.data;

        //insert sum of current digits
        Node2 full_result = insertBefore(sum.sum, val % 10);

        //return sum so far and the carry val
        sum.sum = full_result;
        sum.carry = val / 10;
        return sum;
    }

    Node2 padList(Node2 n, int padding) {
        Node2 curr = n;
        for (int i = 0; i < padding; i++) {
            curr = insertBefore(curr, 0);
        }

        return curr;
    }

    Node2 insertBefore(Node2 list, int data) {
        Node2 node = new Node2(data);
        if (list != null) {
            node.next = list;
        }
        return node;
    }

    int length(Node2 list) {
        if (list == null) {
            return 0;
        }

        int size = 1;
        Node2 curr = list;
        while (curr.next != null) {
            size++;
            curr = curr.next;
        }

        return size;
    }

    void insertLast1(int data) {
        Node2 node = new Node2(data);

        if (firstList == null) {
            firstList = node;

        } else {
            Node2 curr = firstList;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = node;
        }

    }

    void insertLast2(int data) {
        Node2 node = new Node2(data);

        if (secondList == null) {
            secondList = node;

        } else {
            Node2 curr = secondList;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = node;
        }

    }

    public void display(Node2 head) {
        Node2 curr = head;
        while (curr.next != null) {
            System.out.print(curr.data);
            curr = curr.next;
        }
        System.out.print(curr.data);
    }

}

class Node2 {

    public int data;
    public Node2 next;

    public Node2() {
        next = null;
    }

    public Node2(int data) {
        this.data = data;
    }

    void setNext(Node2 n) {
        next = n;
    }

    Node2 getNext() {
        return next;
    }

}

class PartialSum {

    Node2 sum = null;
    int carry = 0;
}
