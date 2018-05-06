package com.akezhanmussa.impl;

import com.akezhanmussa.adt.HashTableMap;
import com.akezhanmussa.adt.Map;


public class LLQHashTableMap<K extends Comparable, V> implements HashTableMap<K, V> {

    private LinkedListQueue<KeyValuePair<K,V>>[] buckets;
    private int size;
    private int bucketSize;

    public LLQHashTableMap(int bucketSize){
        size = 0;
        this.bucketSize = bucketSize;
        buckets = new LinkedListQueue[bucketSize];
    }

    @Override
    public void define(K key, V value) {

        int index = hashFunction(key);

        if (buckets[index] == null){
            buckets[index] = new LinkedListQueue<>();
            buckets[index].enqueue(new KeyValuePair(key, value));
            size++;
        } else {

            int size = buckets[index].getSize();
            boolean contains = false;

            try{
                for(int i = 0; i<size; i++){

                    KeyValuePair<K,V> object = buckets[index].dequeue();

                    if (key.equals(object.getKey())){
                        buckets[index].enqueue(new KeyValuePair(key, value));
                        contains = true;
                        break;
                    }

                    buckets[index].enqueue(object);
                }

            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }

            if (!contains){
                buckets[index].enqueue(new KeyValuePair(key, value));
                this.size ++;
            }

        }
    }



    private int hashFunction(K key){

        int value = key.hashCode();

        value = Math.abs(value) % bucketSize;

        return value;
    }


    @Override
    public V getValue(K key) {

        int index = hashFunction(key);
        int size = buckets[index].getSize();

        try{
            for(int i = 0; i<size; i++){
                KeyValuePair<K,V> object = buckets[index].dequeue();
                buckets[index].enqueue(object);

                if (key.equals(object.getKey())){
                    return object.getValue();
                }

            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        return null;
    }

    @Override
    public V remove(K key) {

        int index = hashFunction(key);

        int size = buckets[index].getSize();

        try{
            for(int i = 0; i<size; i++){
                KeyValuePair<K,V> object = buckets[index].dequeue();

                if (key.equals(object.getKey())){
                    this.size --;
                    return object.getValue();
                }
                buckets[index].enqueue(object);
            }

        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        return null;
    }

    @Override
    public KeyValuePair<K, V> removeAny() throws Exception {
        if (size == 0){
            throw new Exception("The set is empty");
        }

        KeyValuePair<K,V> object = null;

        for(int i = 0; i<bucketSize; i++){
            if (buckets[i] != null){
                if (buckets[i].getSize() != 0){
                    object = buckets[i].dequeue();
                    size--;
                    break;
                }
            }
        }

        return object;

    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public void clear() {
        buckets = new LinkedListQueue[bucketSize];
        size = 0;
    }

    @Override
    public String toString() {
        String result = "[ --";
        for(int i = 0; i<bucketSize;i++){
            if (buckets[i] != null){
                if (buckets[i].getSize() != 0){
                    result += buckets[i].toString() + " -- ";
                }
            } else {
            }

        }
        return result + " ]";
    }

    @Override
    public int getNumberOfBuckets() {
        return this.bucketSize;
    }

    @Override
    public int getBucketSize(int index) throws Exception {
        if (index < 0 || index >= bucketSize){
            throw new Exception("The buckets with these indexes do not exist!!!");
        }
        return buckets[index].getSize();    }

    @Override
    public double getLoadFactor() {
        return this.size/this.bucketSize;
    }

    @Override
    public double getBucketSizeStandardDev() {

        double[] bucketArray = new double[bucketSize];
        double mean = size/bucketSize;
        double stdv = 0;

        for(int i = 0; i<bucketSize; i++){
            if (buckets[i] == null){
                bucketArray[i] = 0;
            } else {
                bucketArray[i] = buckets[i].getSize();
            }
        }

        for(int i = 0; i<bucketSize; i++){
            stdv += Math.pow(bucketArray[i] - mean, 2);
        }

        stdv /= bucketSize;
        stdv = Math.pow(stdv, 0.5);

        return stdv;    }

    @Override
    public String bucketsToString() {
        String result = "[\n";
        for(int i = 0; i<bucketSize;i++){
            if (buckets[i] != null){
                if (buckets[i].getSize() != 0){
                    result += " The bucket of index " + i + ": ";
                    result += buckets[i].toString();
                }
            } else {
                result += " The bucket of index " + i + ": is empty ";
            }

            result += '\n';

        }
        return result + "]";
    }

}

