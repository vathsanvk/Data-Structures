/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * Create a linkedlist of nodes at each level of a tree
 * we use pre-order traversal to record the count 
 */
public class TG3 {
    
    public static void main(String[] args){
        CreateTree c = new CreateTree();
        int array[] = {1,3,5,6,7};
        Node4 root = c.createTree(array);
        
        ArrayList<LinkedList<Node4>> ans = createLevelLinkedList(root);    
        int i = 0;
        for(LinkedList<Node4> l : ans){
            System.out.print("Nodes at level " + i + ": ");
            i++;
            for(Node4 n : l){
                System.out.print(n.data);
            }
            System.out.println("");
        }
        
    }
    
    static void createLevelLinkedList(Node4 root, ArrayList<LinkedList<Node4>> lists, int level){
        if(root == null) return;
        LinkedList<Node4> list = null;
        if(lists.size() == level){ //level not present in the list
            list = new LinkedList<Node4>();
            lists.add(list);
        }else{
            list = lists.get(level);
        }
        list.add(root);
        createLevelLinkedList(root.left, lists, level + 1);
        createLevelLinkedList(root.right, lists, level + 1);
        
    }
    
    static ArrayList<LinkedList<Node4>> createLevelLinkedList(Node4 root){
        ArrayList<LinkedList<Node4>> lists = new ArrayList<LinkedList<Node4>>();
        createLevelLinkedList(root, lists, 0);
        return lists;
    }
    
  
}
