/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;

/**
 *
 * @author Srivathsan
 */
public class DFSUndirectedCycle {

    public static void main(String[] args) {
        DFSUndirectedCycleGraph d = new DFSUndirectedCycleGraph();
        d.addVertex('A'); //0
        d.addVertex('B'); //1
        d.addVertex('C'); //2
        d.addVertex('D'); //3
        d.addVertex('E'); //4

        /*d.addEdge(0, 1); //AB
        d.addEdge(0, 2); //DA
        d.addEdge(0, 4); //AE
        d.addEdge(1, 2); //BC
        d.addEdge(2, 3); //CD*/
        
        d.addEdge(0, 1);
        d.addEdge(1, 2);
        d.addEdge(2, 0);
       

        d.dfs();
    }
}

class DFSUndirectedCycleVertex {

    public char name;
    public char parent;
    public boolean visited;

    public DFSUndirectedCycleVertex(char lab) {
        name = lab;
        visited = false;

    }
}

// finally create the graph class on whicb the dfs is performed
class DFSUndirectedCycleGraph {

    private final int MAX_VERTS = 20;
    private DFSUndirectedCycleVertex[] vertexList;  //list of vertices
    private int adjMat[][];   //adjacency matrix
    private int currVerts;    //current number of vertices
    int parent;

    private DFSCycleStack dFSCycleStack;

    public DFSUndirectedCycleGraph() {
        vertexList = new DFSUndirectedCycleVertex[MAX_VERTS];

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
        vertexList[currVerts++] = new DFSUndirectedCycleVertex(lab);
    }

    public void addEdge(int start, int end) {
        adjMat[start][end] = 1;
        adjMat[end][start] = 1;

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
            parent = dFSCycleStack.peek();
            int v = getAdjUnvisitedVertex(dFSCycleStack.peek());

            if (v == -1) // no such vertex
            {
                System.out.println(dFSCycleStack.pop());
            } else if (v == -2) {
                System.out.println("Graph has Cycles");
                return;
            } else {

                vertexList[v].visited = true;
                vertexList[v].parent = vertexList[parent].name;
              //System.out.println(vertexList[v].name + " " + vertexList[v].parent );
              
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

                if (vertexList[v].parent != vertexList[i].name && dFSCycleStack.checkCycles(i)) {
                    System.out.println(vertexList[v].name);
                    System.out.println(vertexList[v].parent + " " + vertexList[i].name);
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
