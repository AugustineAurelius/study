package org.example;

import org.example.utils.HW1;
import org.example.utils.HW2;
import org.example.utils.HW3.Exchanger;
import org.example.utils.HW3.Type;

public class Main {

    public static void main (String[] args){

        Exchanger ex = new Exchanger();

        System.out.println(ex.convert(1.8967334035827186, Type.DOLLARS, Type.YUAN ));
    }
}
