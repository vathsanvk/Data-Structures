/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;

//*************
//in the array, rear is at 0 and front is at items - 1

class PriorityQueueClass {
    
    private int maxSize;
    private long[] qArr;
    private int items;
    
    public PriorityQueueClass(int s){
        maxSize = s;
        qArr = new long[maxSize];
        items = 0;
        
    }
    
    //insertion happens at 0 and removal happens at items
    public void insert(long item){
        
        if(items == 0){
            qArr[items] = item;
            items++;
        }else{
            for(int i = items - 1 ; i >=0 ; i-- ){
                if(item < qArr[i]){
                    qArr[i + 1] = qArr[i];
                    qArr[i] = item;
                }else{
                    qArr[i + 1] = item;
                    break;
                }
            }
            
            
            items++;
        }
    }
    
    public long remove(){
        items--;
        return qArr[items];
    }
    
    public long peekMax(){
        
        return qArr[items - 1];
    }
    
    public boolean isEmpty(){
        return items == 0;
    }
    
    public boolean isFull(){
        return items == maxSize;
    }
    
 }

public class PriorityQueue{
   public static void main(String[] args){
       PriorityQueueClass p = new PriorityQueueClass(5);
       p.insert(30);
       p.insert(20);
       p.insert(10);
       p.insert(50);
       p.insert(40);
       
       while(!p.isEmpty()){
           long item = p.remove();
           System.out.println(item + " ");
       }
   }
}
