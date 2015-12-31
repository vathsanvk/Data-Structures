/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * Implement a queueu to enqueue and dequeue animals in order (Cracking coding
 * interview, 6th edition, page 238)
 */
public class SQ8 {

    public static void main(String[] args) {

    }
}

abstract class Animal {

    private int order;
    protected String name;

    public Animal(String n) {
        name = n;
    }

    public void setOrder(int ord) {
        order = ord;
    }

    public int getOrder() {
        return order;
    }

    public boolean isOlderThan(Animal a) {
        return this.order < a.getOrder();
    }
}

class AnimalQueue {
    LinkedList<Dog> dogs = new LinkedList<Dog>();
    LinkedList<Cat> cats = new LinkedList<>();
    private int order = 0;
    
    public void enqueue(Animal a){
        a.setOrder(order);
        order++;
        
        if(a instanceof Dog) dogs.addLast((Dog) a);
        else if(a instanceof Cat) cats.addLast((Cat) a);
        
    }
    
    public Animal dequeueAny(){
        if(dogs.size() == 0){
            return dequeueCats();
        }else if(cats.size() == 0){
            return dequeueDogs();
        }
        
        Dog dog = dogs.peek();
        Cat cat = cats.peek();
        
        if(dog.isOlderThan(cat)){
            return dequeueDogs();
        }else{
            return dequeueCats();
        }
    }
    
    public Dog dequeueDogs(){
        return dogs.poll();
    }
    
    public Cat dequeueCats(){
        return cats.poll();
    }
}

class Dog extends Animal {

    public Dog(String n) {
        super(n);
    }
}

class Cat extends Animal {

    public Cat(String n) {
        super(n);
    }
}
