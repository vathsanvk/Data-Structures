/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * @author Srivathsan
 */
public class CheckPriorityQueue {

    public static void main(String[] args) {
        PriorityQueue<Integer> p = new PriorityQueue<>();
        p.add(5);
        p.add(2);
        p.add(1);

        System.out.println(p.remove());

        PriorityQueue<Person> p1 = new PriorityQueue<>(new Comparator<Person>() {
            public int compare(Person o1, Person o2) {
                if (o1.getId() < o2.getId()) {
                    return 1;
                } else if (o1.getId() > o2.getId()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        p1.add(new Person("a", 2));
        p1.add(new Person("a", 1));
        p1.add(new Person("a", 3));

        System.out.println(p1.remove().getId());
    }
}

class Person {

    String name;
    int id;

    public Person(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
