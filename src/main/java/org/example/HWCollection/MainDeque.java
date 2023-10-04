package org.example.HWCollection;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainDeque {



    public static void main(String[] args) {


//        Container<Integer> container = new Container<>();
        TripletDeque<Integer> container = new TripletDeque<>();
        for (int i = 0; i < 7; i++){
            container.addLast(i);
        }

        container.printDeque();
        System.out.println();
        Iterator<Integer> iterator = container.iterator();

        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }



    }


}
