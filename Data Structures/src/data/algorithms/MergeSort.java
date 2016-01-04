/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.algorithms;

/**
 *
 * @author Srivathsan
 */
public class MergeSort {
    
   
    public static void main(String[] args){
        int arr[] = {5,4,3,2,1};
      
      
        sort(arr);
        
        for(int i=0;i < arr.length;i++){
           System.out.print(arr[i]);
         }
        
       
       
                
    }
    
   
    
    static void sort(int arr[]){
        int arrLength = arr.length;
        int mid;
       
        if(arrLength < 2){
            return;
        }
        mid = arrLength / 2;
        
        int[] left = new int[mid];
        int[] right = new int[arrLength - mid];
        
        for(int i = 0; i < mid ; i++){
            left[i] = arr[i];
        }
        
        for(int i = mid; i < arrLength ; i++){
            right[i - mid] = arr[i];
        }
        
        sort(left);
        sort(right);
        merge(left, right,arr);
       
        for(int i=0;i < arrLength;i++){
           
           System.out.print(arr[i]);
         }
        System.out.println("");
        
    }
    
    static void merge(int[] left, int[] right, int[] arr){
        int i = 0, j = 0, k = 0;
        
        while( i < left.length && j < right.length){
            if(left[i] <= right[j]){
                arr[k] = left[i];
                i++;
            }else{
                arr[k] = right[j];
                j++;
            }
            k++;
        }
        
        while( i < left.length){
            arr[k] = left[i];
            i++;
            k++;
            
        }
        
        while( j < right.length){
            arr[k] = right[j];
            j++;
            k++;
            
        }
        
    }
}
