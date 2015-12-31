/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;

/**
 *
 * check if a tree t2 is a subtree of another tree t1.
 * Use preorder traversal . Also name null nodes as some dummy nodes
 */
public class TG8 {
    public static void main(String[] args){
        CreateTree c = new CreateTree();
        int array1[] = {1,3,5,6,8};
        int array2[] = {5,8};
        Node4 root1 = c.createTree(array1);
        Node4 root2 = c.createTree(array2);
        System.out.println(containsTree(root1, root2));
    }
    
    static boolean containsTree(Node4 t1, Node4 t2){
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        
        getOrderString(t1, sb1);
        getOrderString(t2, sb2);
        
        System.out.println(sb1);
        System.out.println(sb2);
        
        return sb1.toString().contains(sb2.toString());
        //return sb1.indexOf(sb2.toString()) != -1;
        
    }
    
    static void getOrderString(Node4 node, StringBuilder sb){
        if(node == null){
            sb.append("X");
            return;
        }
        
        //do preorder traversal
        sb.append(node.data + " ");
        getOrderString(node.left, sb);
        getOrderString(node.right, sb);
    }
}
