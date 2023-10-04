package org.example.HWCollection;

import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AssertionsKt;
import org.junit.jupiter.api.Test;


public class TripletDequeTest extends TestCase {
    @Test
    public void testAddFirst() {

        TripletDeque<Integer> tripletDeque = new TripletDeque<>();
        tripletDeque.addFirst(123441);
        tripletDeque.addFirst(1);

        Integer expected = 1;

        Assertions.assertEquals(expected, tripletDeque.getFirst());
    }

    @Test
    public void testAddLast() {
        TripletDeque<Integer> tripletDeque = new TripletDeque<>();
        tripletDeque.addLast(1);
        tripletDeque.addLast(2);
        Integer expected = 2;

        Assertions.assertEquals(expected, tripletDeque.getLast());
    }
    @Test
    public void testOfferFirst() {
        TripletDeque<Integer> tripletDeque = new TripletDeque<>();
        tripletDeque.addFirst(123441);
        tripletDeque.offerFirst(1);

        Integer expected = 1;

        Assertions.assertEquals(expected, tripletDeque.getFirst());
    }
    @Test
    public void testOfferLast() {
        TripletDeque<Integer> tripletDeque = new TripletDeque<>();
        tripletDeque.addLast(1);
        tripletDeque.offerLast(2);
        Integer expected = 2;

        Assertions.assertEquals(expected, tripletDeque.getLast());
    }
    @Test
    public void testRemoveFirst() {
        TripletDeque<Integer> tripletDeque = new TripletDeque<>();
        tripletDeque.addFirst(1);
        tripletDeque.addLast(2);
        tripletDeque.addLast(2);
        tripletDeque.removeFirst();
        Integer expected = 2;


        Assertions.assertEquals(expected, tripletDeque.getFirst());
        Assertions.assertEquals(expected, tripletDeque.size());

    }
    @Test
    public void testRemoveLast() {
        TripletDeque<Integer> tripletDeque = new TripletDeque<>();
        tripletDeque.addFirst(1);
        tripletDeque.addLast(2);
        tripletDeque.addLast(2);
        tripletDeque.removeLast();
        Integer expectedInteger = 1;
        Integer expectedSize = 2;


        Assertions.assertEquals(expectedInteger, tripletDeque.getFirst());
        Assertions.assertEquals(expectedSize, tripletDeque.size());
    }
    @Test
    public void testPollFirst() {
        TripletDeque<Integer> tripletDeque = new TripletDeque<>();
        tripletDeque.addFirst(1);
        tripletDeque.addLast(2);
        tripletDeque.addLast(2);
        tripletDeque.pollFirst();
        Integer expected = 2;

        Assertions.assertEquals(expected, tripletDeque.getFirst());
        Assertions.assertEquals(expected, tripletDeque.size());
    }
    @Test
    public void testPollFirstEmptyDeque() {
        TripletDeque<Integer> tripletDeque = new TripletDeque<>();
        Integer expected = null;
        Assertions.assertEquals(expected, tripletDeque.pollFirst());
    }
    @Test
    public void testPollLast() {
        TripletDeque<Integer> tripletDeque = new TripletDeque<>();
        tripletDeque.addFirst(1);
        tripletDeque.addLast(2);
        tripletDeque.addLast(2);
        tripletDeque.pollLast();
        Integer expectedInteger = 1;
        Integer expectedSize = 2;


        Assertions.assertEquals(expectedInteger, tripletDeque.getFirst());
        Assertions.assertEquals(expectedSize, tripletDeque.size());
    }
    @Test
    public void testPollLastEmptyDeque() {
        TripletDeque<Integer> tripletDeque = new TripletDeque<>();
        Integer expected = null;
        Assertions.assertEquals(expected, tripletDeque.pollLast());
    }
    @Test
    public void testGetFirst() {
        TripletDeque<Integer> tripletDeque = new TripletDeque<>();
        for (int i = 0; i < 10; i++){
            tripletDeque.addFirst(i);
        }

        Integer expected = 9;

        Assertions.assertEquals(expected, tripletDeque.getFirst());
    }
    @Test
    public void testGetLast() {
        TripletDeque<Integer> tripletDeque = new TripletDeque<>();
        for (int i = 0; i < 10; i++){
            tripletDeque.addLast(i);
        }

        Integer expected = 9;

        Assertions.assertEquals(expected, tripletDeque.getLast());
    }
    @Test
    public void testPeekFirst() {
        TripletDeque<Integer> tripletDeque = new TripletDeque<>();
        Assertions.assertNull(tripletDeque.peekFirst());
        tripletDeque.addFirst(14);
        Assertions.assertEquals(14, tripletDeque.getFirst());
        Assertions.assertEquals(1, tripletDeque.size());
    }
    @Test
    public void testPeekLast() {
        TripletDeque<Integer> tripletDeque = new TripletDeque<>();
        Assertions.assertNull(tripletDeque.peekLast());
        tripletDeque.addFirst(14);
        tripletDeque.addFirst(15);
        Assertions.assertEquals(14, tripletDeque.peekLast());
        Assertions.assertEquals(2, tripletDeque.size());
    }
    @Test
    public void testRemoveFirstOccurrence() {
        TripletDeque<Integer> tripletDeque = new TripletDeque<>();
        for (int i = 0; i < 10; i++){
            tripletDeque.addLast(i);
        }
        tripletDeque.printDeque();
        System.out.println();
        Assertions.assertEquals(10, tripletDeque.size());
        tripletDeque.removeFirstOccurrence(5);
        tripletDeque.printDeque();
        Assertions.assertEquals(9, tripletDeque.size());
        Assertions.assertEquals(0, tripletDeque.getFirst());
        Assertions.assertEquals(9, tripletDeque.getLast());

    }
    @Test
    public void testRemoveLastOccurrence() {
        TripletDeque<Integer> tripletDeque = new TripletDeque<>();
        for (int i = 0; i < 10; i++){
            tripletDeque.addLast(i);
        }
        Assertions.assertEquals(10, tripletDeque.size());
        tripletDeque.removeLastOccurrence(5);
        Assertions.assertEquals(9, tripletDeque.size());
        Assertions.assertEquals(0, tripletDeque.getFirst());
        Assertions.assertEquals(9, tripletDeque.getLast());
    }

}