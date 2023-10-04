package org.example.HWCollection;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class TripletDeque<E> implements Deque<E> {
    private int maxSize = 1000;
    private int size = 0;
    private Container<E> first;
    private Container<E> last;
    TripletDeque(){
        Container<E> newContainer = new Container<>();
        first = newContainer;
        last = newContainer;
    }
    TripletDeque(int dequeCapacity){
        maxSize = dequeCapacity;
        Container<E> newContainer = new Container<>();
        first = newContainer;
        last = newContainer;
    }

    private void addContainerNext() {
        Container<E> newContainer = new Container<>();

        first.setNext(newContainer);
        newContainer.setPrevious(first);
        first = newContainer;

    }
    private void addContainerPrev() {
        Container<E> newContainer = new Container<>();

        last.setPrevious(newContainer);
        newContainer.setNext(last);
        last = newContainer;

    }


    @Override
    public void addFirst(E e) {
        if (e == null){
            throw new NullPointerException();
        }else if (last.isFull()){
            addContainerPrev();
            this.last.addLast(e);
            this.size++;
        }else if (last == first){
            this.last.addFirstCont(e);
            this.size++;
        } else if (size == maxSize) {
            throw new IllegalArgumentException("Превышен максимальный лимит по емкости");
        } else{
            this.last.addLast(e);
            this.size++;
        }

    }

    @Override
    public void addLast(E e) {
        if (e == null){
            throw new NullPointerException();
        }else if (first.isFull()){
            addContainerNext();
            this.first.addFirst(e);
            this.size++;
        } else if (size == maxSize) {
            throw new IllegalArgumentException("Превышен максимальный лимит по емкости");
        }else{
            this.first.addFirst(e);
            this.size++;
        }

    }

    @Override
    public boolean offerFirst(E e) {
        if (size != maxSize){
            addFirst(e);
            return true;
        }
        return false;
    }

    @Override
    public boolean offerLast(E e) {
        if (size != maxSize){
            addLast(e);
            return true;
        }
        return false;
    }

    @Override
    public E removeFirst() {
        if (size == 0){
            throw new NoSuchElementException();
        }
        E elem = last.removeLast();
        if (last.isEmpty()){
            last = last.getNext();
        }
        size--;
        return elem;
    }

    @Override
    public E removeLast() {
        if (size == 0){
            throw new NoSuchElementException();
        }
        E elem = first.removeFirst();
        if (first.isEmpty()){
            first = first.getPrevious();
        }
        size--;
        return elem;

    }

    @Override
    public E pollFirst() {
        if (size != 0){
            return removeFirst();
        }
        return null;
    }

    @Override
    public E pollLast() {
        if (size != 0){
            return removeLast();
        }
        return null;
    }

    @Override
    public E getFirst() {
        if (size == 0){
            throw new NoSuchElementException();
        }
        E elem = (E) last.getElements()[last.findLastToRemove()];

        return elem;
    }

    @Override
    public E getLast() {
        if (size == 0){
            throw new NoSuchElementException();
        }
        E elem = (E) first.getElements()[first.findFirstToRemove()];

        return elem;

    }

    @Override
    public E peekFirst() {
        if (size != 0){
            return getFirst();
        }
        return null;
    }

    @Override
    public E peekLast() {
        if (size != 0){
            return getLast();
        }
        return null;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        boolean flg = false;
        List<E> tempArray = new ArrayList<>();
        Iterator<E> iterator = iterator();
        while (iterator.hasNext()){
            E tempEl = iterator.next();
            if (tempEl == o && !flg){
                flg = true;
            }else {
                tempArray.add(tempEl);
            }
            removeFirst();
        }

        Container<E> newContainer = new Container<>();
        first = newContainer;
        last = newContainer;
        if (flg){
            for(E el : tempArray){
                addLast(el);
            }
        }

        return flg;
    }
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Container<E> currentContainer = last;
            private int currentIndex = currentContainer.findLastToRemove();

            @Override
            public boolean hasNext() {
                return currentContainer != null &&
                        (currentIndex <= currentContainer.findFirstToRemove() ||
                                currentContainer.getNext() != null) ;
            }

            @Override
            public E next() {


                if (currentIndex == currentContainer.getCapacity()) {
                    currentContainer = currentContainer.getNext();
                    currentIndex = 0;
                }
                if (currentContainer == null || currentIndex >= currentContainer.getCapacity()) {
                    throw new NoSuchElementException();
                }

                return (E) currentContainer.getElements()[currentIndex++];
            }
        };
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        boolean flg = false;
        List<E> tempArray = new ArrayList<>();
        Iterator<E> iterator = iterator();
        while (iterator.hasNext()){
            E tempEl = iterator.next();

            tempArray.add(tempEl);
            removeFirst();

        }
        Container<E> newContainer = new Container<>();
        first = newContainer;
        last = newContainer;
        if (tempArray.remove(tempArray.lastIndexOf(o)) == o){
            flg = true;
        }
        if (flg){
            for(E el : tempArray){
                addLast(el);
            }
        }

        return flg;
    }

    @Override
    public boolean add(E e) {
        //addlast
        if (size == maxSize){
            throw new IllegalStateException();
        }else if (e == null) {
            throw new NullPointerException();
        }
        addLast(e);
        return true;
    }

    @Override
    public boolean offer(E e) {
        if (size == maxSize){
            throw new IllegalStateException();
        }else if (e == null) {
            throw new NullPointerException();
        }
        offerLast(e);
        return true;
    }

    @Override
    public E remove() {
        return removeFirst();
    }

    @Override
    public E poll() {
        return pollFirst();
    }

    @Override
    public E element() {
        return getFirst();
    }

    @Override
    public E peek() {
        return peekFirst();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E elem : c){
            offer(elem);
        }
        return true;
    }


    @Override
    public void push(E e) {
        if (size == maxSize){
            throw new IllegalStateException();
        }else if (e == null) {
            throw new NullPointerException();
        }
        addFirst(e);
    }

    @Override
    public E pop() {
        return removeFirst();
    }

    @Override
    public boolean remove(Object o) {
        return removeFirstOccurrence(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        boolean flg = false;
        Iterator<E> iterator = iterator();
        while (iterator.hasNext()){
            if(iterator.next() == o){
                flg = true;
                break;
            }
        }
        return flg;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public Iterator<E> descendingIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }
    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }


    public void printDeque(){
        Container<E> temp = last;
        while (temp != null){
            System.out.print(Arrays.toString(temp.getElements()));
            temp = temp.getNext();
        }
    }

    static class Container<E> {

        private int head = 0;
        private int tail = 0;
        @Getter
        private int capacity;
        @Getter
        private int size;
        @Getter
        private Object[] elements;
        @Setter
        @Getter
        private Container<E> next;
        @Setter
        @Getter
        private Container<E> previous;

        Container(){
            int DEFAULT_SIZE = 5;
            capacity = DEFAULT_SIZE;
            this.elements = new Object[DEFAULT_SIZE];

        }
        Container(int capacity){
            this.capacity = capacity;
            this.elements = new Object[capacity];

        }
        public E removeFirst(){
            E elem = (E) elements[findFirstToRemove()];
            elements[findFirstToRemove()] = null;
            size--;
            return elem;
        }
        public E removeLast(){
            E elem = (E) elements[findLastToRemove()];
            elements[findLastToRemove()] = null;
            size--;
            return elem;
        }
        private int findLastToRemove() {
            int ind = 0;
            for (int i = 0; i < elements.length; i ++){
                if (elements[i] != null){
                    ind = i;
                    break;
                }
            }
            return ind;
        }

        public int findFirstToRemove() {
            int ind = 0;
            for (int i = elements.length - 1; i != 0 ; i--){
                if (elements[i] != null){
                    ind = i;
                    break;
                }
            }
            return ind;
        }

        public void addFirst(E e){
            if (isFull()){
            System.out.println("Net mesta");
        }else {

            this.elements[findFirstInd()] = e;
            this.size++;
            }
        }



        public void addLast(E e){
            if (isFull()){
                System.out.println("Net mesta");
            }else {
                this.elements[findLastInd()] = e;
                this.size++;
            }
        }

        private int findLastInd() {
            int ind = 0;
            for (int i = elements.length - 1; i != 0 ; i--){
                if (elements[i] == null){
                    ind = i;
                    break;
                }
            }
            return ind;
        }
        private int findFirstInd() {
            int ind = 0;
            for (int i = 0; i < elements.length; i ++){
                if (elements[i] == null){
                    ind = i;
                    break;
                }
            }
            return ind;
        }

        public boolean isFull() {
            return size == elements.length;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        private void moveElems(){
            Object[] temp = new Object[elements.length];
            for (int i = 0; i < elements.length; i++){
                if (elements[i] == null){
                    break;
                }
                temp[i + 1] = elements[i];
            }
            this.elements = temp;
        }

        public void addFirstCont(E e) {
            if (isFull()){
                System.out.println("Net mesta");
            }else {
                moveElems();
                this.elements[0] = e;
                this.size++;
            }
        }

        public boolean remove(E e){
            boolean result = false;
            for (int i = 0; i < this.size; i++){
                if (e == elements[i]){
                    elements[i] = null;
                    result = true;
                }
            }
            return result;
        }


    }

}
