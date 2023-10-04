package org.example.HWCollection;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainDeque {



    public static void main(String[] args) {


//        Container<Integer> container = new Container<>();
        TripletDeque<Integer> container = new TripletDeque<>();
        for (int i = 1; i <= 8; i++){
            container.addFirst(i);
        }
        container.add(2);
        container.add(2);
        container.add(2);
        container.add(3);
        container.printDeque();

        System.out.println(container.contains(3));
        container.removeFirstOccurrence(3);

        System.out.println(container.contains(3));
        container.printDeque();



    }


}
