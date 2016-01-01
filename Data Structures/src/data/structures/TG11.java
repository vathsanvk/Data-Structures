/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;

/**
 *
 * find common ancestor of two nodes in a binary tree
 */
public class TG11 {
    public static void main(String[] args){
        
    }
    
    //with link to its parent. Trace paths of both nodes up until they meet. same as linkedlist intersection problem
    static Node4 commonAncestor(Node4 n1, Node4 n2){
        int delta = depth(n1) - depth(n2);
        
        Node4 first = delta > 0 ? n2 : n1 ; //get shallower node
        Node4 second = delta > 0 ? n1 : n2 ; // get deeper node
        
        second = goUpBy(second, Math.abs(delta)); //move deeper node up
        
        //find where paths intersect
        
        while(first != second && first != null && second != null){
            first = first.parent;
            second = second.parent;
        }
        
        return first == null || second == null ? null : first;
    }
    
    static Node4 goUpBy(Node4 node, int delta){
        while(delta > 0 && node != null){
            node = node.parent;
            delta--;
        }
        
        return node;
    }
    
    static int depth(Node4 node){
        int depth = 0;
        while(node != null){
            node = node.parent;
            depth++;
        }
        
        return depth;
    }
    
}
