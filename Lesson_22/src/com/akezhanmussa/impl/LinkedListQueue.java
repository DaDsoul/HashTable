package com.akezhanmussa.impl;
import com.akezhanmussa.adt.Queue;


/**
 * Created by talgat on 18.02.18.
 */
public class LinkedListQueue<T> implements Queue<T>{

    Node<T> back;
    Node<T> front;
    int size;

    public LinkedListQueue(){
        back = null;
        front = null;
        size = 0;
    }

    @Override
    public void enqueue(T value) {
        if (size == 0){
            front = new Node(value);
            front.setLink(null);
            back = front;
        } else if (size == 1){
            Node<T> newItem = new Node(value);
            back = newItem;
            front.setLink(back);
            back.setLink(null);
        } else {
            Node<T> newItem = new Node(value);
            back.setLink(newItem);
            back = newItem;
            back.setLink(null);
        }

        size ++;
    }

    @Override
    public T dequeue() throws Exception {
        if (size == 0){
            throw new Exception("Queue is empty!!!");
        }

        Node<T> oldFront = front;
        Node<T> newFront = front.getLink();
        front = newFront;

        size --;

        return  oldFront.getValue();
    }

    @Override
    public int getSize() {

        return size;
    }

    @Override
    public void clear() {
        front = null;
        back = null;
        size = 0;
    }

    public String toString(){
        String result = "front[";

        if (size != 0){

            Node<T> actualFront = front;

            for(int i = 0; i<size; i++){
                result += actualFront.getValue() + " ";
                actualFront = actualFront.getLink();
            }
        }

        result += "]back";

        return result;
    }
}
