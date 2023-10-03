package org.example.HW5;

import lombok.Data;
import lombok.Getter;

public class Tree<T extends Comparable<T>> {
    private Node root;

    private int size;
    public int size(){
        return this.size;
    }
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

    private Node removeNode(Node currentNode, T value) {
        if (currentNode == null) {
            return null;
        }

        if (value.compareTo(currentNode.value) < 0) {
            currentNode.setLeftLeaf(removeNode(currentNode.getLeftLeaf(), value));
        } else if (value.compareTo(currentNode.value) > 0) {
            currentNode.setRightLeaf(removeNode(currentNode.getRightLeaf(), value));
        } else {
            if (currentNode.getLeftLeaf() == null) {
                return currentNode.getRightLeaf();
            } else if (currentNode.getRightLeaf() == null) {
                return currentNode.getLeftLeaf();
            }

            currentNode.value = findMinValue(currentNode.getRightLeaf());
            currentNode.setRightLeaf(removeNode(currentNode.getRightLeaf(), currentNode.value));
        }

        return currentNode;
    }

    private T findMinValue(Node currentNode) {
        T minValue = currentNode.value;
        while (currentNode.getLeftLeaf() != null) {
            minValue = currentNode.getLeftLeaf().value;
            currentNode = currentNode.getLeftLeaf();
        }
        return minValue;
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
