package org.example.HW5;

public class TreeMain {


    public static void main(String[] args) {
        Tree<Integer> mytree = new Tree<>();
        mytree.add(1);
        mytree.add(2);
        mytree.add(0);
        mytree.add(-10);
        mytree.add(-5);
        System.out.println(mytree.getSize());
        mytree.remove(-5);
        System.out.println(mytree.getSize());
    }

}
