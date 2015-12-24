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

//topological sort works on directed acyclic graphs
public class MyTopoSort {
    public static void main(String[] args){
        TopoGraph mstGraph = new TopoGraph();
        mstGraph.addVertex('A'); //0
        mstGraph.addVertex('B');  //1
        mstGraph.addVertex('C');  //2
        mstGraph.addVertex('D'); //3
        mstGraph.addVertex('E'); //4
        mstGraph.addVertex('F');  //5
        mstGraph.addVertex('G'); //6
        mstGraph.addVertex('H'); //7
        
        mstGraph.addEdge(0, 3);  //AD
        mstGraph.addEdge(0, 4); //AE
        mstGraph.addEdge(1, 4);  //BE
        mstGraph.addEdge(2, 5); //CF
        mstGraph.addEdge(3, 6); //DG
        mstGraph.addEdge(4, 6); //EG
        mstGraph.addEdge(5, 7);  //FH
        mstGraph.addEdge(6, 7);  //GH
        
        mstGraph.topo();
        
        
        

    }
}

class TopoVertex{
    public char label;
    
    public TopoVertex(char lab){
        label = lab;
    }
}

class TopoGraph{
    private final int MAX_VERTS = 20;
    private TopoVertex[] vertexList;
    private int adjMat[][];
    private int currVerts;
    private char[] sortedArray; // final sorted array of vertices
    
    public TopoGraph(){
        vertexList = new TopoVertex[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        currVerts = 0;
        
        for(int i = 0 ; i< MAX_VERTS; i++){
            for (int j = 0; j < MAX_VERTS ; j++){
                adjMat[i][j] = 0;
            }
        }
        
        sortedArray = new char[MAX_VERTS];
    }
    
    public void addVertex(char lab){
        vertexList[currVerts++] = new TopoVertex(lab);
    }
    
    public void addEdge(int start , int end){
        adjMat[start][end] = 1;  //since directed graph
    }
    
    public void displayVertex(int v){
        System.out.println(vertexList[v].label);
    }
    public void topo(){
        int origVerts = currVerts;
        
        
        while(currVerts > 0){
            int currentVetex = noSuccessors();
            if(currentVetex == -1){
                System.out.println("Error: Graph has cycles");
                return;
            }
            
            sortedArray[currVerts - 1] = vertexList[currentVetex].label;
            deleteVertex(currentVetex);
        }
        
        //all vertices deleted, display sorted array
        
        System.out.print("Topologically Sorted Order: ");
        for(int i = 0; i < origVerts;i++){
            System.out.print(sortedArray[i]);
        }
        System.out.println("");
    }
    
    public int noSuccessors(){
        boolean isEdge;
        
        for(int row = 0; row < currVerts; row++){
            isEdge = false;
            
            for(int col = 0; col < currVerts; col++){
                if(adjMat[row][col] > 0){
                    isEdge = true;
                    break;           //this vertex has a succ, try another
                }
            }
            if(!isEdge){
                return row;
            }
        }
        
        return -1;
    }
    
    public void deleteVertex(int delVert){
        if(delVert != currVerts - 1){  //check if not last vertex, if last vertex need not move anything
            for(int i = delVert ; i < currVerts - 1; i++){   // delete from vertex list
                vertexList[i] = vertexList[i + 1];
            }
            
            //delete row from adjmat
            for(int row = delVert; row < currVerts - 1; row++){
                moveRowUp(row, currVerts);
            }
            
            //delete col from adjmat
            
            for(int col = delVert; col < currVerts - 1; col++){
                moveColLeft(col, currVerts - 1);  //currVerts - 1, since row is already moved up by deleting it
            }
        }
        
        
        
        currVerts--;
    }
    
    private void moveRowUp(int row, int len){
        for(int col = 0; col < len ; col++){
            adjMat[row][col] = adjMat[row + 1][col];
        }
    }
    
     private void moveColLeft(int col, int len){
        for(int row = 0; row < len ; row++){
            adjMat[row][col] = adjMat[row][col + 1];
        }
    }
    
}
