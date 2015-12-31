/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;

/**
 *
 * Given a sorted array with unique elements, wtire algo to create a binary search tree with minimal height
 * 
 * 1. Insert into root the middle element from the array
 * 2. Insert into the left subtree, the left subarray
 * 3. Insert into the right subtree, the right subarray
 * 4. recurse
 */
public class TG2 {
    public static void main(String[] args){
        CreateTree c = new CreateTree();
        int array[] = {1,3,5,6,7};
        Node4 root = c.createTree(array);
        c.inOrder(root);
    }
}

class CreateTree{
    Node4 createTree(int array[]){
        return createTree(array, 0, array.length - 1);
    }
    
    Node4 createTree(int arr[], int start, int end){
        if(end < start) return null;
        
        int mid = (start + end) / 2;
        Node4 n = new Node4(arr[mid]);
        n.left = createTree(arr, start, mid -1 );
        n.right = createTree(arr, mid + 1,end );
        return n;
    }
    
    public void inOrder(Node4 root){
        if(root == null) return;
        inOrder(root.left);
        System.out.print(root.data);
        
        inOrder(root.right);
    }
}


class Node4{
    Node4 left,right,parent;
    int data;
    
    public Node4(int item){
        data = item;
    }
}