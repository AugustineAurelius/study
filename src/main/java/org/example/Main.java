package org.example;

import org.example.HW3.Exchanger;
import org.example.HW3.Type;

public class Main {

    public static void main (String[] args){

        Exchanger ex = new Exchanger();

        System.out.println(ex.convert(1.8967334035827186, Type.DOLLARS, Type.YUAN ));
    }
}
