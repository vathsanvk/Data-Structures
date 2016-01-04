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
public class BubbleSort {

    public static void main(String[] args) {
        int arr[] = {5, 4, 3, 2, 1};

        sort(arr);

    }

    static void sort(int[] arr) {
        int in, out;

        for(out = arr.length - 1 ; out > 0 ; out-- ){  //going backwards
            for(in = 0 ; in < out; in++){                //going forward
                if(arr[in] > arr[in + 1]){
                    int temp = arr[in];
                    arr[in] = arr[in + 1];
                    arr[in + 1] = temp;
                    
                }
            }                                     
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
