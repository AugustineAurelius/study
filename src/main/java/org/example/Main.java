package org.example;

import org.example.HW3.Exchanger;
import org.example.HW3.Type;

public class Main {

    public static void main (String[] args){

        Exchanger ex = new Exchanger();

        System.out.println(ex.convert(15.372790161414297, Type.YUAN, Type.RUBLES ));
    }
}
