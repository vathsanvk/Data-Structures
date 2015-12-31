/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;

/**
 *
 * implement a function to check i a binary tree is binary search tree
 * do an in-order traversal and simultaneously validate data
 */
public class TG5 {
    public static void main(String[] args){
        CreateTree c = new CreateTree();
        int array[] = {1,2,4,3};
        Node4 root = c.createTree(array);
        System.out.println(checkBST(root));
    }
    static Integer last_printed = null;
    static boolean checkBST(Node4 n){
        if(n == null) return true;
        
        
        //recurse left
        if(!checkBST(n.left)) return false;
        
        
        //check current data
        if(last_printed != null && n.data <= last_printed){
            return false;
        }
        last_printed = n.data;
        
        //recurse right
        
        if(!checkBST(n.right)) return false;
        
        
        
        return true; // all good
    }
}
