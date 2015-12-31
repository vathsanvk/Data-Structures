/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;

/**
 *
 * find the next node in a BST , next node = next in-order successor Assume each
 * node has link to its parent
 */
public class TG6 {

    public static void main(String[] args) {
        CreateTree c = new CreateTree();
        int array[] = {1, 3, 5, 6, 7};
        Node4 root = c.createTree(array);
    }

    static Node4 inOrderSucc(Node4 n) {
        if (n == null) {
            return null;
        }

        //found right children -> return leftmost node of right subtree
        if (n.right != null) {
            return leftMostChild(n.right);
        } else {
            Node4 q = n;
            Node4 x = q.parent;

            //go until we are on the left instead of right
            while (x != null && x.left != q) {
                q = x;
                x = x.parent;
            }
            return x;
        }
    }

    static Node4 leftMostChild(Node4 n) {
        if (n == null) {
           return null;   
        }
        while(n.left != null){
            n = n.left;
        }
        return n;
    }
}
