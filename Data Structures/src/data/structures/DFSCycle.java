/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;

public class DFSCycle {

    public static void main(String[] args) {
        DFSCycleGraph d = new DFSCycleGraph();
        d.addVertex('A'); //0
        d.addVertex('B'); //1
        d.addVertex('C'); //2
        d.addVertex('D'); //3
        d.addVertex('E'); //4

        d.addEdge(0, 1); //AB
        d.addEdge(3, 0); //DA
        d.addEdge(0, 4); //AE
        d.addEdge(1, 2); //BC
        d.addEdge(2, 3); //CD
       

        d.dfs();
    }
}

//we use stack to implement DFS
class DFSCycleStack {

    private final int SIZE = 20;
    private int[] st;
    private int top;

    public DFSCycleStack() {
        st = new int[SIZE];
        top = -1;
    }

    public void push(int val) {
        top++;
        st[top] = val;
    }

    public int pop() {
        return st[top--];
    }

    public int peek() {
        return st[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean checkCycles(int v) {

        for (int i = 0; i <= top; i++) {

            if (st[i] == v) {
                return true;
            }
        }

        return false;
    }
}

//create a vertex class that holds vertex name and visit status
class DFSCycleVertex {

    public char name;
    public boolean visited;

    public DFSCycleVertex(char lab) {
        name = lab;
        visited = false;
    }
}

// finally create the graph class on whicb the dfs is performed
class DFSCycleGraph {

    private final int MAX_VERTS = 20;
    private DFSCycleVertex[] vertexList;  //list of vertices
    private int adjMat[][];   //adjacency matrix
    private int currVerts;    //current number of vertices

    private DFSCycleStack dFSCycleStack;

    public DFSCycleGraph() {
        vertexList = new DFSCycleVertex[MAX_VERTS];

        adjMat = new int[MAX_VERTS][MAX_VERTS];

        currVerts = 0;

        //set adjacency matrix to 0's
        for (int i = 0; i < MAX_VERTS; i++) {
            for (int j = 0; j < MAX_VERTS; j++) {
                adjMat[i][j] = 0;
            }
        }

        dFSCycleStack = new DFSCycleStack();

    }

    public void addVertex(char lab) {
        vertexList[currVerts++] = new DFSCycleVertex(lab);
    }

    public void addEdge(int start, int end) {
        adjMat[start][end] = 1;

    }

    public void displayVertex(int v) {
        System.out.print(vertexList[v].name);
    }

    public void dfs() {
        //begin at vertex 0 

        vertexList[0].visited = true;
        
        dFSCycleStack.push(0);

        while (!dFSCycleStack.isEmpty()) {
            //get unvisited vertex adjacent to stack top
            int v = getAdjUnvisitedVertex(dFSCycleStack.peek());

            if (v == -1) // no such vertex
            {
                dFSCycleStack.pop();
            } else if (v == -2) {
                System.out.println("Graph has Cycles");
                return;
            } else {

                vertexList[v].visited = true;
               
                dFSCycleStack.push(v);
                
            }
        }
        
        System.out.println("Graph has no Cycles");

        //reset all flags
        for (int i = 0;
                i < currVerts;
                i++) {
            vertexList[i].visited = false;
        }
    }

    public int getAdjUnvisitedVertex(int v) {

        for (int i = 0; i < currVerts; i++) {

            if (adjMat[v][i] == 1 && vertexList[i].visited == true) {

                if (dFSCycleStack.checkCycles(i)) {
                    return -2;
                }
            }

            if (adjMat[v][i] == 1 && vertexList[i].visited == false) {

                return i;
            }

        }

        return -1;
    }

}
