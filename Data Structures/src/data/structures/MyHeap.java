/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;

import com.sun.corba.se.impl.encoding.BufferManagerReadGrow;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyHeap {

    public static void main(String[] args) throws IOException {
        int val, val2;

        Heap h = new Heap(31);
        boolean success;

        h.insert(70);
        h.insert(40);
        h.insert(50);
        h.insert(20);
        h.insert(60);
        h.insert(100);
        h.insert(80);
        h.insert(30);
        h.insert(10);
        h.insert(90);

        while (true) {
            System.out.print("Enter a letter ");
            System.out.println(" s : show, i : insert , r : remove, c : change, e:exit");

            String choice = getString();
            
            if(choice.equals("e"))
                break;

            switch (choice) {

                case "s":
                    h.displayHeap();
                    break;
                case "i":
                    System.out.println("Enter a value to insert: ");
                    val = getInt();
                    success = h.insert(val);
                    if (!success) {
                        System.out.println("Can't insert; heap full");
                    }

                    break;

                case "r":
                    if (!h.isEmpty()) {
                        h.remove();
                    } else {
                        System.out.println("Can't remove: heap empty");
                    }

                    break;

                case "c":
                    System.out.println("Enter current index of the item: ");
                    val = getInt();
                    System.out.println("Enter new key");
                    val2 = getInt();
                    success = h.change(val, val2);

                    if (!success) {
                        System.out.println("Invalid index");
                    }
                    break;
                    
               
                default:
                    System.out.println("Invalid entry");

            }

        }
    }

    public static String getString() throws IOException {
        InputStreamReader is = new InputStreamReader(System.in);

        BufferedReader br = new BufferedReader(is);

        String line = br.readLine();

        return line;
    }

    public static int getInt() throws IOException {
        String line = getString();
        return Integer.parseInt(line);
    }

}

class Node {

    private int data;

    public Node(int key) {
        data = key;
    }

    public int getKey() {
        return data;
    }

    public void setKey(int key) {
        data = key;
    }
}

class Heap {

    private Node[] heapArray;
    private int maxSize;
    private int currSize;

    public Heap(int size) {
        maxSize = size;
        currSize = 0;
        heapArray = new Node[maxSize];
    }

    public boolean isEmpty() {
        return currSize == 0;
    }

    public boolean insert(int key) {
        if (currSize == maxSize) {
            return false;
        }
        Node newNode = new Node(key);
        //insert newnode at the end
        heapArray[currSize] = newNode;
        //perform trickleup so as to restore heap property (parent node key > child nodes key)
        trickleUp(currSize);
        currSize++;
        return true;

    }

    public void trickleUp(int index) {
        //find parent of the inserted node

        int parent = (index - 1) / 2;

        Node bottom = heapArray[index]; // inserted node is saved in bottom variable

        //go up until it is one level below root and until inserted node's key is lesser than sone parent key
        while (index > 0 && heapArray[parent].getKey() < bottom.getKey()) {
            heapArray[index] = heapArray[parent]; //move the parent down

            index = parent;  //give parent's index to inserted node index

            parent = (parent - 1) / 2; //new parent is the parent of old parent

        }

        //once we hit a node where the loop exists insert new node at that position
        heapArray[index] = bottom;

    }

    public Node remove() {
        //remove item with max key which is at the top

        Node root = heapArray[0];

        //replace root with the last node from the array remove the last node from the array
        currSize--;
        heapArray[0] = heapArray[currSize];

        //perform tricledown from root to restore heap property
        trickleDown(0);

        return root;
    }

    public void trickleDown(int index) {
        //go down the tree atlease one level above the leaf node level, replace the index node with the larger child at each level

        int largerChild;
        Node top = heapArray[index]; //save root

        while (index < currSize / 2) {  //this makes sure the index node reaches atleast one level above the leaf nodes

            int leftChild = 2 * index + 1;
            int rightChild = leftChild + 1;

            //now find the larger child
            if (rightChild < currSize && heapArray[leftChild].getKey() < heapArray[rightChild].getKey()) {
                largerChild = rightChild;
            } else {
                largerChild = leftChild;
            }

            // we check if the original root (top) is greater than the larger node, if so exit loop
            if (top.getKey() >= heapArray[largerChild].getKey()) {
                break;
            }

            //replace the index with the larger node
            heapArray[index] = heapArray[largerChild];

            // move down
            index = largerChild;

        }

        //now move the top into the position in the index variable
        heapArray[index] = top;

    }

    // to change the value of a node
    public boolean change(int index, int newVal) {
        //check if the index is valid

        if (index < 0 || index >= currSize) {
            return false;
        }

        int oldVal = heapArray[index].getKey(); //get the old value

        heapArray[index].setKey(newVal); //change to new value

        //if new value is higher then trickle it up else trickle it down
        if (oldVal < newVal) {
            trickleUp(index);
        } else {
            trickleDown(index);
        }

        return true;
    }

    public void displayHeap() {
        System.out.print("Heap as an array: ");

        for (int i = 0; i < currSize; i++) {
            if (heapArray[i] != null) {
                System.out.print(heapArray[i].getKey() + " ");
            } else {
                System.out.print("-- ");
            }
        }
        
        System.out.println();

        //representing as a heap
        int spaces = 32;
        int itemsPerRow = 1;
        int column = 0;
        int j = 0;   //current item

        String dots = "...............................";

        System.out.println(dots + dots); // top dotted line

        while (currSize > 0) {
            if (column == 0) {
                for (int k = 0; k < spaces; k++) {
                    System.out.print(" ");
                }
            }
            
        

            System.out.print(heapArray[j].getKey());

            if (++j == currSize) {
                break;
            }

            if (++column == itemsPerRow) {
                spaces /= 2;
                itemsPerRow *= 2;
                column = 0;
                System.out.println();
            } else {
                for (int k = 0; k < spaces * 2 - 2; k++) {
                    System.out.print(" ");
                }
            }
            
    }

            System.out.println("\n" + dots + dots);
        

    }
}
