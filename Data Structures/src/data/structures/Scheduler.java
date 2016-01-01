/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Srivathsan
 */
public class Scheduler {

    static int windowTime = 3;
    static ArrayList<Process> processes = new ArrayList<Process>();
    static Queue<Process> processQueue = new LinkedList<Process>();

    public static void main(String[] args) {

        processes.add(new Process("p1", 5,0));
        processes.add(new Process("p2", 3,1));
        processes.add(new Process("p3", 8,2));
        processes.add(new Process("p4", 6,3));

        for (Process p : processes) {
            processQueue.add(p);
        }
        
        manageProcess();
        
        for(Process p : processes){
            System.out.println("Wait time for " + p.name + " : " + (p.waitTime - p.arrivalTime));
        }
        

    }

    static void manageProcess() {
        while (!processQueue.isEmpty()) {
            Process curr = processQueue.remove();
            runProcess(curr);
            if(curr.execTime > 0){
                processQueue.add(curr);
            }

        }
    }

    static void runProcess(Process p) {
        int i;
        for (i = 0; i < windowTime; i++) {
            if (p.execTime > 0) {
                p.execTime--;
            } else {
                break;
            }
        }
        
        for(Process process : processes ){
           if(!process.name.equals(p.name) && process.execTime > 0){
               process.waitTime = process.waitTime + i ;
           }
        }
        
        
    }

}

class Process {

    String name;
    int execTime;
    int waitTime;
    int arrivalTime;

    public Process(String name, int time, int arrivalTime) {
        this.name = name;
        execTime = time;
        waitTime = 0;
        this.arrivalTime = arrivalTime;
    }
}
