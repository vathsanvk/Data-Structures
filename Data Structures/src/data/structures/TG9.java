/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;

import java.util.Random;

/**
 *
 * Implement a BST in which getRandomNode() return a random node, also implement find and insert
 */
public class TG9 {
    public static void main(String[] args){
        Node5 n = new Node5(5);
        n.insertInOrder(1);
        n.insertInOrder(3);
        n.insertInOrder(4);
        n.insertInOrder(6);
        n.insertInOrder(7);
        n.insertInOrder(8);
        
        System.out.println(n.getRandomNode().data());
    }
}

class Node5{
    private int data;
    public Node5 left,right;
    private int size = 0;
    
    public Node5(int d){
        data = d;
        size = 1;
    }
    
    Node5 getRandomNode(){
        int leftSize = left == null ? 0 : left.size();
        
        Random random = new Random();
        
        int index = random.nextInt(size);
        
        if(index < leftSize){
            return left.getRandomNode();
        }else if(index == leftSize){
            return this;
        }else{
            return right.getRandomNode();
        }
    }
    
    void insertInOrder(int d){
        if(d <= data){
            if(left == null){
                left = new Node5(d);
            }else{
                left.insertInOrder(d);
            }
        }else{
            if(right == null){
                right = new Node5(d);
            }else{
                right.insertInOrder(d);
            }
        }
        size++;
    }
    
    int size(){
        return size;
    }
    int data(){
        return data;
    }
    
    
    Node5 find(int d){
        if(d == data){
            return this;
        }else if(d <= data){
            return left != null ? left.find(d) : null;
        }else if(d > data){
            return right != null ? right.find(d) : null;
        }
        
        return null;
    }
}