package org.example.HW5;

import lombok.Data;
import lombok.Getter;

public class Tree<T extends Comparable<T>> {

    private Node root;
    @Getter
    private int size;

    public T add(T value){
        if (this.root == null){
            this.root = new Node(value);
            this.size++;
        }else {
            addNode(this.root, value);
        }

        return value;
    }


    public void remove(T value) {
        root = removeNode(root, value);
    }

    private Node removeNode(Node node, T value) {
        if (node == null) {
            return null;
        }
        int res = value.compareTo(node.getValue());
        if (res < 0) {
            node.setLeftLeaf(removeNode(node.getLeftLeaf(), value));
        } else if (res > 0) {
            node.setRightLeaf(removeNode(node.getRightLeaf(), value));
        } else {
            if (node.getLeftLeaf() == null) {
                return node.getRightLeaf();
            } else if (node.getRightLeaf() == null) {
                return node.getLeftLeaf();
            }

        }

        return node;
    }

    private void addNode(Node node, T value){

        int res = value.compareTo(node.getValue());
        if (res > 0){
            addRightNode(node, value);
        }else if (res < 0){
            addLeftNode(node, value);
        }

    }

    private void addRightNode(Node node, T value) {
        if (node.getRightLeaf() == null){
            node.setRightLeaf(new Node(value));
            this.size++;
        }else {
            addNode(node.getRightLeaf(), value);
        }
    }
    private void addLeftNode(Node node, T value) {
        if (node.getLeftLeaf() == null){
            node.setLeftLeaf(new Node(value));
            this.size++;
        }else {
            addNode(node.getLeftLeaf(), value);
        }
    }

    @Data
    private class Node{
        private T value;
        private Node leftLeaf;
        private Node rightLeaf;
        Node (T value){
            this.value = value;
        }

    }

}
