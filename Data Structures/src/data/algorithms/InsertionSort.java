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
public class InsertionSort {
    public static void main(String[] args){
        int arr[] = {5,4,3,2,1};
        
        sort(arr);
        
        
    }
    
    static void sort(int[] arr){
        int in, out;
        
        int temp;
        for(out = 1; out < arr.length ; out++){
            temp = arr[out];
            in = out;
            while( in > 0 && arr[in - 1] >= temp){
                
                arr[in] = arr[in - 1];  //shift item right
                
                --in;
            }
            
            arr[in] = temp;  // insert marked item
        }
        
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }
}
