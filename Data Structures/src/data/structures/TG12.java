/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;

/**
 *
 * find common ancestor of two nodes in a binary tree. Without link to parents
 */
public class TG12 {
    public static void main(String[] args){
        CreateTree c = new CreateTree();
        int array[] = {1,3,5,6,7};
        Node4 root = c.createTree(array);
        
        Node4 p = root.left;
        Node4 q = root.right;
        
        
        
        System.out.println(commonAncestor(root, p, q).data);
    }
    
    static Node4 commonAncestor(Node4 root, Node4 p, Node4 q){
        
        //check if one node is not in the tree
        if(!covers(root,p) || (!covers(root,q))){
            return null;
        }
        return ancestorHelper(root, p,q);
    }
    
    static Node4 ancestorHelper(Node4 root, Node4 p, Node4 q){
        if( root == null || root == p || root == q){
            return root;
        }
        
        boolean pIsOnLeft = covers(root.left, p);
        boolean qIsOnLeft = covers(root.left, q);
        
        if( pIsOnLeft != qIsOnLeft){ //nodes are on diff side
            return root;
        }
        
        Node4 childSide = pIsOnLeft ? root.left : root.right;
        
        return ancestorHelper(childSide, p, q);
    }
    
    static boolean covers(Node4 root, Node4 p){
        if(root == null) return false;
        if(root == p) return true;
        
        return covers(root.left, p) || covers(root.right, p);
                
    }
    
    
}
