/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;

public class MyDFS {
    public static void main(String[] args){
        Graph graph = new Graph();
        
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
        graph.dfs();
        System.out.println();
        
    }
}

//we use stack to implement DFS

class MyStackArray{
    private final int SIZE = 20;
    private int[] st;
    private int top;
    
    
    public MyStackArray(){
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

class Vertex{
    public char name;
    public boolean visited;
    
    public Vertex(char lab){
        name = lab;
        visited = false;
    }
}

// finally create the graph class on whicb the dfs is performed

class Graph{
    private final int MAX_VERTS = 20;
    private Vertex[] vertexList;  //list of vertices
    private int adjMat[][];   //adjacency matrix
    private int currVerts;    //current number of vertices
    
    private MyStackArray myStackArray;
    
    
    public Graph(){
        vertexList = new Vertex[MAX_VERTS];
        
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        
        currVerts = 0;
        
        //set adjacency matrix to 0's
        
        for(int i = 0; i< MAX_VERTS;i++){
            for(int j = 0; j< MAX_VERTS;j++){
                adjMat[i][j] = 0;
            }
        }
        
        myStackArray = new MyStackArray();
        
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
    
    public void dfs(){
        //begin at vertex 0 
        
        vertexList[0].visited = true;
        displayVertex(0);
        myStackArray.push(0);
        
        while(!myStackArray.isEmpty()){
            //get unvisited vertex adjacent to stack top
            int v = getAdjUnvisitedVertex(myStackArray.peek());
            
            if(v == -1) // no such vertex
                myStackArray.pop();
            
            else{
                vertexList[v].visited = true;
                displayVertex(v);
                myStackArray.push(v);
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
