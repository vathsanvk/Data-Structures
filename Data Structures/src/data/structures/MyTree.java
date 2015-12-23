/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;

public class MyTree {

    public static void main(String[] args) {
        TreeClass t = new TreeClass();
        t.insert(5);
        t.insert(1);
        t.insert(3);
        t.insert(4);
        t.insert(2);
        
        t.traverse("preOrder");
        t.traverse("inOrder");
        t.traverse("postOrder");
        
        t.delete(2);
        t.traverse("inOrder");
    }
}

class TreeClass {

    private class Node {

        private int data;

        private Node leftChild;
        private Node rightChild;
    }

    private Node root;

    public Node find(int key) {
        Node current = root;
        while (current.data != key) {

            if (key < current.data) {
                current = current.leftChild;
            } else {
                current = current.rightChild;
            }

            if (current == null) {
                return null;
            }

        }

        return current;
    }

    public void insert(int val) {
        Node newNode = new Node();
        newNode.data = val;
        if (root == null) {
            root = newNode;
        } else {
            Node current = root;
            Node parent;

            while (true) {
                parent = current;
                if (val < current.data) {
                    current = current.leftChild;
                    if (current == null) {
                        parent.leftChild = newNode;
                        return;

                    }
                } else {
                    current = current.rightChild;
                    if (current == null) {
                        parent.rightChild = newNode;
                        return;
                    }

                }
            }
        }
    }

    public boolean delete(int key) {
        Node current = root;
        Node parent = root;
        boolean isLeftChild = true;

        while (current.data != key) {
            parent = current;
            if (key < current.data) {
                isLeftChild = true;
                current = current.leftChild;
            } else {
                isLeftChild = false;
                current = current.rightChild;
            }

            if (current == null) {
                return false;
            }

        }

        //if no children , simply delete the current node
        if (current.leftChild == null && current.rightChild == null) {
            if (current == root) {
                root = null;
            } else if (isLeftChild) {
                parent.leftChild = null;
            } else {
                parent.rightChild = null;
            }

        } //if no right child, replace with left subtree
        else if (current.rightChild == null) {
            if (current == root) {
                root = current.leftChild;
            } else if (isLeftChild) {
                parent.leftChild = current.leftChild;
            } else {
                parent.rightChild = current.leftChild;
            }
        } //if no left child, replace with right subtree
        else if (current.leftChild == null) {
            if (current == root) {
                root = current.rightChild;
            } else if (isLeftChild) {
                parent.leftChild = current.rightChild;
            } else {
                parent.rightChild = current.rightChild;
            }
        } else {  //two children , replace node to be deleted with inorder successor
            Node successor = getSuccessor(current);

            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.leftChild = successor;
            } else {
                parent.rightChild = successor;
            }
            successor.leftChild = current.leftChild;
        }

        return true;
    }

    private Node getSuccessor(Node delNode) {
        Node successorParent = delNode;
        Node successor = delNode;
        Node current = delNode.rightChild;

        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.leftChild;
        }

        if (successor != delNode.rightChild) {
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = delNode.rightChild;
        }

        return successor;
    }

    public void traverse(String type) {
        switch (type) {
            case "preOrder":
                System.out.println("Preorder: ");
                preOrder(root);
                break;
            case "inOrder":
                System.out.println("Inorder: ");
                inOrder(root);
                break;
            case "postOrder":
                System.out.println("Postorder: ");
                postOrder(root);
                break;
        }
    }
    
    private void preOrder(Node lroot){
        if(lroot != null){
            System.out.println(lroot.data + " ");
            preOrder(lroot.leftChild);
            preOrder(lroot.rightChild);
        }
    }
    
    private void inOrder(Node lroot){
        if(lroot != null){
           
            inOrder(lroot.leftChild);
            System.out.println(lroot.data + " ");
            inOrder(lroot.rightChild);
        }
    }
    
    private void postOrder(Node lroot){
        if(lroot != null){
            
            postOrder(lroot.leftChild);
            postOrder(lroot.rightChild);
            System.out.println(lroot.data + " ");
        }
    }

}
