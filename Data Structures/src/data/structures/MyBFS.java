/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;

public class MyBFS {

    public static void main(String[] args) {
        BFSGraph graph = new BFSGraph();

        graph.addVertex('A');   //0
        graph.addVertex('B');   //1
        graph.addVertex('C');   //2
        graph.addVertex('D');   //3
        graph.addVertex('E');   //4
        graph.addVertex('F');   //5
       

        graph.addEdge(0, 1);  //AB
        graph.addEdge(1, 2);   //BC
        graph.addEdge(0, 3);  //AD
        graph.addEdge(1, 5);  //BF
        graph.addEdge(3, 4);  //DE

        System.out.print("Nodes visited in order: ");
        graph.bfs();
        System.out.println();

    }
}

//we use a queue to implement BFS
class BFSQueue {

    private final int SIZE = 20;
    private int[] qArray;
    private int front;
    private int rear;

    public BFSQueue() {
        qArray = new int[SIZE];
        front = 0;
        rear = -1;
    }

    public void insert(int val) {
        if (rear == SIZE - 1) {
            rear = -1;
        }

        qArray[++rear] = val;
    }

    public int remove() {
        int temp = qArray[front++];

        if (front == SIZE) {
            front = 0;
        }

        return temp;
    }

    public boolean isEmpty() {
        return (rear + 1 == front);  // when rear = -1. front = 0 and when rear = 
    }
}

class BFSVertex {

    public char name;
    public boolean visited;

    public BFSVertex(char lab) {
        name = lab;
        visited = false;
    }
}

class BFSGraph {

    private final int MAX_VERTS = 20;
    private BFSVertex[] vertexList;  //list of vertices
    private int adjMat[][];   //adjacency matrix
    private int currVerts;    //current number of vertices

    private BFSQueue bfsq;

    public BFSGraph() {
        vertexList = new BFSVertex[MAX_VERTS];

        adjMat = new int[MAX_VERTS][MAX_VERTS];

        currVerts = 0;

        //set adjacency matrix to 0's
        for (int i = 0; i < MAX_VERTS; i++) {
            for (int j = 0; j < MAX_VERTS; j++) {
                adjMat[i][j] = 0;
            }
        }

        bfsq = new BFSQueue();

    }

    public void addVertex(char lab) {
        vertexList[currVerts++] = new BFSVertex(lab);
    }

    public void addEdge(int start, int end) {
        adjMat[start][end] = 1;
        adjMat[end][start] = 1;
    }

    public void displayVertex(int v) {
        System.out.print(vertexList[v].name);
    }

    //dfs needs single loop while bfs need an inner loop as well so as to first search neighbors of root and then go to each neighbor's
    //neighbor
    //in dfs we use stack to store vertices which are visited, add their neighbors to stack and remove them when they have no more neighbors
    //in bfs we use queue to store visited vertces, remove them and then add their neighbors to the queue
    public void bfs() {
        vertexList[0].visited = true;
        displayVertex(0);
        bfsq.insert(0);
        int v2;

        while (!bfsq.isEmpty()) {
            int v1 = bfsq.remove();

            //until it has no unvisited neighbors
            while ((v2 = getAdjUnvisitedVertex(v1)) != -1) {
                vertexList[v2].visited = true;
                displayVertex(v2);
                bfsq.insert(v2);
            }

        }

        //reset flags at the end
        for (int i = 0; i < currVerts; i++) {
            vertexList[i].visited = false;
        }

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
