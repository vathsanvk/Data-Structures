/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Srivathsan
 */
public class Anagram {

    static int size;
    static int count;
    static char[] charArr = new char[100];

    public static void main(String[] args) throws IOException {
        System.out.println("Enter a word: ");

        String input = getString();
        size = input.length();
        count = 0;

        for (int i = 0; i < size; i++) {
            charArr[i] = input.charAt(i);
        }

        doAnagram(size);
    }

    public static void doAnagram(int newSize) {

        if (newSize == 1) {
            return;
        }
        for (int i = 0; i < newSize; i++) {  //for each pos, anagram remaining
            doAnagram(newSize - 1);
           // if (newSize == 2) {
               displayWord();             //if innermost, display data
            //}
            rotate(newSize);
        }
    }

    public static void rotate(int newSize) {
        int i;
        int pos = size - newSize;
        char temp = charArr[pos];  //save first letter 
        for (i = pos + 1; i < size; i++) //shifting others
        {
            charArr[i - 1] = charArr[i];
        }

        charArr[i - 1] = temp;  // put first on right
    }

    public static void displayWord() {
        if (count < 99) {
            System.out.print(" ");
        }
        if (count < 9) {
            System.out.print(" ");
        }
        System.out.print(++count + " ");
        for (int i = 0; i < size; i++) {
            System.out.print(charArr[i]);
        }
        System.out.print("  ");
        System.out.flush();
        if (count % 6 == 0) {
            System.out.println("");
        }
    }
    
    public static String getString() throws IOException{
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        String s=br.readLine();
        return s;
    }
}
