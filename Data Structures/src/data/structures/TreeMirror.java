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
// generate a mirror for a tree
//Mirror of a Tree: Mirror of a Binary Tree T is another Binary Tree M(T) with left and right children of all non-leaf nodes interchanged.
public class TreeMirror {

    public static void main(String[] args) {
        BinaryTree b = new BinaryTree();
        TreeMirrorNode root = new TreeMirrorNode(1);
        root.left = new TreeMirrorNode(2);
        root.left.left = new TreeMirrorNode(3);
        root.left.right = new TreeMirrorNode(4);
        root.right = new TreeMirrorNode(5);
        
        b.root = root;
        b.inOrder();
        b.mirror();
        System.out.println();
        b.inOrder();
    }

}

class TreeMirrorNode {

    int data;
    TreeMirrorNode left, right;

    public TreeMirrorNode(int item) {
        data = item;
        left = right = null;
    }
}

class BinaryTree {

    TreeMirrorNode root;

    void mirror() {
        mirror(root);
    }

    void mirror(TreeMirrorNode node) {
        if (node == null) {
            return;
        } else {
            TreeMirrorNode temp;

            mirror(node.left); // call left
            mirror(node.right); //call right

            //swap left and right
            temp = node.left;
            node.left = node.right;
            node.right = temp;

        }
    }

    void inOrder() {
        inOrder(root);
    }

    void inOrder(TreeMirrorNode node) {
        if (node == null) {
            return;
        }

        inOrder(node.left);
        System.out.print(node.data);
       
        inOrder(node.right);
    }

}
