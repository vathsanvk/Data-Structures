/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;


public class MyMST {
     public static void main(String[] args){
        MSTGraph graph = new MSTGraph();
        
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
        graph.addEdge(1, 3);  //BD
        
        System.out.print("Minimum Spanning Tree: ");
        graph.mst();
        System.out.println();
        
    }
}

//we use stack to implement DFS

class MSTStack{
    private final int SIZE = 20;
    private int[] st;
    private int top;
    
    
    public MSTStack(){
        st = new int[SIZE];
        top = -1;
    }
    
    public void push (int val){
        top++;
        st[top] = val;
    }
    
    public int pop(){
        return st[top--];
    }
    
    public int peek(){
        return st[top];
    }
    
    public boolean isEmpty(){
        return top == -1;
    }
}

//create a vertex class that holds vertex name and visit status

class MSTVertex{
    public char name;
    public boolean visited;
    
    public MSTVertex(char lab){
        name = lab;
        visited = false;
    }
}

// finally create the graph class on whicb the dfs is performed

class MSTGraph{
    private final int MAX_VERTS = 20;
    private Vertex[] vertexList;  //list of vertices
    private int adjMat[][];   //adjacency matrix
    private int currVerts;    //current number of vertices
    
    private MSTStack mstStack;
    
    
    public MSTGraph(){
        vertexList = new Vertex[MAX_VERTS];
        
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        
        currVerts = 0;
        
        //set adjacency matrix to 0's
        
        for(int i = 0; i< MAX_VERTS;i++){
            for(int j = 0; j< MAX_VERTS;j++){
                adjMat[i][j] = 0;
            }
        }
        
        mstStack = new MSTStack();
        
    }
    
    public void addVertex(char lab){
        vertexList[currVerts++] = new Vertex(lab);
    }
    
    public void addEdge(int start, int end){
        adjMat[start][end] = 1;
        adjMat[end][start] = 1;
    }
    
    public void displayVertex(int v){
        System.out.print(vertexList[v].name);
    }
    
    public void mst(){
        //begin at vertex 0 
        
        vertexList[0].visited = true;
     
        mstStack.push(0);
        
        while(!mstStack.isEmpty()){
            //get unvisited vertex adjacent to stack top
            int currentVertex = mstStack.peek();
            int v = getAdjUnvisitedVertex(currentVertex);
            
            if(v == -1) // no such vertex
                mstStack.pop();
            
            else{
                vertexList[v].visited = true;
                displayVertex(currentVertex);
                displayVertex(v);
                mstStack.push(v);
                System.out.print(" ");
            }
        }
        
        //reset all flags
        
        for(int i = 0; i< currVerts; i++){
            vertexList[i].visited = false;
        }
    }
    
    public int getAdjUnvisitedVertex(int v){
        
        for(int i =0 ; i < currVerts; i++){
            if(adjMat[v][i] == 1 && vertexList[i].visited == false ){
                return i;
            }
        }
        
        
        return -1;
    }
    
    
}


