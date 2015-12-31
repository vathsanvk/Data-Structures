/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;

/**
 *
 * count the number of oaths that sum to a given value
 */
public class TG7 {
    public static void main(String[] args){
         CreateTree c = new CreateTree();
         int array[] = {1,2,3,4,5,6};
         Node4 root = c.createTree(array);
         
         System.out.println(countPathWithSum(root, 8));
    }
    
    static int countPathWithSum(Node4 root, int targetSum){
        if(root == null) return 0;
        
        //count paths with sum starting from root
        int pathsFromRoot = countPathSumFromNode(root, targetSum, 0);
        
        
        //try for nodes left and right
        //int pathsOnLeft = countPathWithSum(root.left, targetSum);
        //int pathsOnRight = countPathWithSum(root.right, targetSum);
        
        
        //return pathsFromRoot + pathsOnLeft + pathsOnRight;
        return pathsFromRoot;
    }
    
    //returns the number of paths with this sum starting from this node
    
    static int countPathSumFromNode(Node4 node, int targetSum, int currentSum){
        if(node == null) return 0;
        currentSum += node.data;
        
        int totalPaths = 0;
        if(currentSum == targetSum){
            totalPaths++;
        }
        totalPaths += countPathSumFromNode(node.left, targetSum, currentSum);
        totalPaths += countPathSumFromNode(node.right, targetSum, currentSum);
        
        
        return totalPaths;
    }
    
}
