/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;

import java.util.LinkedList;

/**
 *
 * Given a BST. Print all possible arrays that could have led to this tree
 */
public class TG13 {

    public static void main(String[] args) {
        Node4 root = new Node4(2);
        root.left = new Node4(1);
        root.right = new Node4(3);
        root.right.right = new Node4(4);

        Sequences seq = new Sequences(root);

        System.out.println("Final Results");
          seq.displaySeqs();
    }
}

class Sequences {

    public LinkedList<LinkedList<Node4>> seqs; // all of possible sequences
    LinkedList<Node4> pre; //The nodes already visited in the current sequence
    LinkedList<Node4> poss; //All of the possible next steps in the current sequence

    public Sequences(Node4 root) {
        seqs = new LinkedList<>();

        pre = new LinkedList<>();
        poss = new LinkedList<>();

        buildSeqs(pre, poss, root);

    }

    void buildSeqs(LinkedList<Node4> pre, LinkedList<Node4> poss, Node4 root) {
        pre.add(root);

        if (root.left != null) {
            poss.add(root.left);
        }

        if (root.right != null) {
            poss.add(root.right);
        }

        if (poss.isEmpty()) {
            seqs.add(pre);
        }
        
        System.out.println("Pre");
        for(Node4 n : pre){
            
            System.out.print(n.data + " " );
        }
        System.out.println("");

        for (Node4 n : poss) {
            LinkedList<Node4> temp = (LinkedList<Node4>) pre.clone();
            LinkedList<Node4> ptemp = (LinkedList<Node4>) poss.clone();
            ptemp.remove(n);
            buildSeqs(temp, ptemp, n);
        }
    }

    void displaySeqs() {
        for (LinkedList<Node4> l : seqs) {
            for (Node4 n : l) {
                System.out.print(n.data + " ");
            }
            System.out.println();
        }
    }
}
