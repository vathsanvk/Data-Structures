/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;

import java.util.LinkedList;

/**
 *
 * Given a directed graph, design an algo to find whethere there is a route
 * between them use bfs or dfs
 */
public class TG1 {
  public static void main(String[] args){
      Graph1 graph1 = new Graph1();

        graph1.addVertex('A');   //0
        graph1.addVertex('B');   //1
        graph1.addVertex('C');   //2
        graph1.addVertex('D');   //3
        graph1.addVertex('E');   //4
        graph1.addVertex('F');   //5
       

        graph1.addEdge(0, 1);  //AB
        graph1.addEdge(1, 2);   //BC
        graph1.addEdge(0, 3);  //AD
        graph1.addEdge(1, 5);  //BF
        graph1.addEdge(3, 4);  //DE

        System.out.println(new CheckRoute().search(graph1, 1, 5));
        System.out.println();
  }
}

class Vertex1 {

    public char name;
    public boolean visited;

    public Vertex1(char lab) {
        name = lab;
        visited = false;
    }
}

class CheckRoute {

    

    boolean search(Graph1 g, int start, int end) {
        if (start == end) {
            return true;
        }

        //works a queue
        LinkedList<Integer> q = new LinkedList<Integer>();

       
        q.add(start);
       
        int v2;
        while (!q.isEmpty()) {
            int v1 = q.removeFirst();

            //until it has no unvisited neighbors
            while ((v2 = g.getAdjUnvisitedVertex(v1)) != -1) {
                g.vertexList[v2].visited = true;
               
                q.add(v2);
                
                if(v2 == end) return true;
            }

        }
        
        return false;

}
}

class Graph1 {

    private final int MAX_VERTS = 20;
    public Vertex1[] vertexList;  //list of vertices
    private int adjMat[][];   //adjacency matrix
    private int currVerts;    //current number of vertices

   

    public Graph1() {
        vertexList = new Vertex1[MAX_VERTS];

        adjMat = new int[MAX_VERTS][MAX_VERTS];

        currVerts = 0;

        //set adjacency matrix to 0's
        for (int i = 0; i < MAX_VERTS; i++) {
            for (int j = 0; j < MAX_VERTS; j++) {
                adjMat[i][j] = 0;
            }
        }

        

    }

    public void addVertex(char lab) {
        vertexList[currVerts++] = new Vertex1(lab);
    }

    public void addEdge(int start, int end) {
        adjMat[start][end] = 1;
        
    }

    public void displayVertex(int v) {
        System.out.print(vertexList[v].name);
    }
    
    public Vertex1[] getVertices(){
        return vertexList;
    }
    
     
     
    
    public int getAdjUnvisitedVertex(int v) {
        for (int i = 0; i < currVerts; i++) {
            if (adjMat[v][i] == 1 && vertexList[i].visited == false) {
                return i;
            }
        }

        return -1;
    }

}
