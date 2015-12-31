/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Build order. Given a list of projects and dependencies, find a build order
 * give a,b,c,d,e,f dependencies (proj, dependent on) :
 * (d,a),(b,f),(d,b),(a,f),(c,d) output: f,e,a,b,d,c
 *
 * Hint: use topo sort
 */
public class TG10 {

    public static void main(String[] args) {
        Graph2 g = new Graph2();
        g.addVertex("a");
        g.addVertex("b");
        g.addVertex("c");
        g.addVertex("d");
        g.addVertex("e");
        g.addVertex("f");
        

        g.addEdge("a", "d");
        g.addEdge("f", "b");
        g.addEdge("b", "d");
        g.addEdge("f", "a");
        g.addEdge("d", "c");
        g.topo();

    }
}

class Node6 {

    public String label;

    public Node6(String lab) {
        label = lab;
    }
}

class Graph2 {

    private final int MAX_VERTS = 20;
    private Node6[] nodeList;
    private HashMap<String, ArrayList<String>> adjList;
    private int currNumNodes;
    private String[] sortedArray; // final sorted array of vertices

    public Graph2() {
        nodeList = new Node6[MAX_VERTS];

        currNumNodes = 0;
        
        adjList = new HashMap<String, ArrayList<String>>();

        sortedArray = new String[MAX_VERTS];
    }

    public void addVertex(String lab) {
        nodeList[currNumNodes++] = new Node6(lab);
        ArrayList<String> a = new ArrayList<String>();
        adjList.put(lab, a);
    }

    public void addEdge(String start, String end) {
        
      
        ArrayList<String> getList = adjList.get(start);
        getList.add(end);

    }

    public void displayNode(int v) {
        System.out.println(nodeList[v].label);
    }

    public void topo() {
        int origNodes = currNumNodes;

        while (currNumNodes > 0) {
            String currentNode = noSuccessors();
            if (currentNode == null) {
                System.out.println("Error: Graph has cycles");
                return;
            }

            sortedArray[currNumNodes - 1] = currentNode;
            deleteNode(currentNode);
        }

        //all nodes deleted, display sorted array
        System.out.print("Topologically Sorted Order: ");
        for (int i = 0; i < origNodes; i++) {
            System.out.print(sortedArray[i]);
        }
        System.out.println("");
    }

    public String noSuccessors() {
        boolean isEdge;

        for (Map.Entry<String, ArrayList<String>> map : adjList.entrySet()) {

            if (map.getValue().size() == 0) {
                isEdge = false;
            } else {
                isEdge = true;
            }

            if (!isEdge) {
                return map.getKey();
            }
        }

        return null;
    }

    public void deleteNode(String delNode) {

        int indexOfDelNode = Arrays.asList(nodeList).indexOf(delNode);

        if (indexOfDelNode != currNumNodes - 1) {  //checking if last vertex
            for (int i = 0; i < nodeList.length - 1; i++) {
                nodeList[i] = nodeList[i + 1];
            }
        }

        adjList.remove(delNode); //removing from hashmap

        for (Map.Entry<String, ArrayList<String>> map : adjList.entrySet()) {
            ArrayList<String> list = map.getValue();
            list.remove(delNode);
        }

        currNumNodes--;
    }

}
