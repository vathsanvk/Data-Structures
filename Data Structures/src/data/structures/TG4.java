/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;

/**
 *
 * Check if a binary tree is balanced
 */
public class TG4 {

    public static void main(String[] args) {
        CreateTree c = new CreateTree();
        int array[] = {1,2,3,4,5};
        Node4 root = c.createTree(array);
        
        System.out.println(isBalanced(root));
    }
    
    static boolean isBalanced(Node4 root){
        System.out.println(checkHeight(root));
        return checkHeight(root) != Integer.MIN_VALUE;
    }
    
    static int checkHeight(Node4 root){
        if(root == null) return -1 ;  // height of a null tree
        
        int leftHeight = checkHeight(root.left);
        
        if( leftHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE; //pass error up
        
        int rightHeight = checkHeight(root.right);
      
        if(rightHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE; //pass error up
        
        int heightDiff = leftHeight - rightHeight;
        
        if(Math.abs(heightDiff) > 1){
            return Integer.MIN_VALUE;
        }else{
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }
}
