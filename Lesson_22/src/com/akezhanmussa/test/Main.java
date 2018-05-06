package com.akezhanmussa.test;

import com.akezhanmussa.adt.HashTableMap;
import com.akezhanmussa.adt.Map;
import com.akezhanmussa.impl.BSTHashTableMap;
import com.akezhanmussa.impl.BSTMap;
import com.akezhanmussa.impl.KeyValuePair;
import com.akezhanmussa.impl.LLQHashTableMap;

public class Main {

    public static void main(String[] args) {


        Map<Integer, String> testMap = new BSTMap<>();

        try{
            testMap.removeAny();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        System.out.println(testMap);

        String value = "";

        testMap.define(3, "Adam3");
        testMap.define(2, "Adam2");
        testMap.define(1, "Adam1");
        testMap.define(4, "Adam4");
        testMap.define(0, "Adam0");

        System.out.println(testMap);
        System.out.println(testMap.getSize());

        testMap.remove(1);
        System.out.println(testMap);
        System.out.println(testMap.getSize());

        testMap.remove(6);
        System.out.println(testMap);
        System.out.println(testMap.getSize());

        for(int i = 0; i<2;i++){
            testMap.define(i, "Adam6");
        }

        System.out.println(testMap);
        System.out.println(testMap.getSize());

        testMap.define(5,"Adam7");
        testMap.define(7,"Adam7");
        testMap.define(6,"Adam7");

        System.out.println(testMap);
        System.out.println(testMap.getSize());

        try{
            for(int i = 0; i<2; i++){
                KeyValuePair<Integer, String> pair = testMap.removeAny();
                System.out.println(pair);
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        System.out.println(testMap);
        System.out.println(testMap.getSize());

        testMap.clear();
        System.out.println(testMap);
        System.out.println(testMap.getSize());

        testMap.define(1, "Adam1");
        testMap.define(2, "Adam2");

        System.out.println(testMap);
        System.out.println(testMap.getSize());

        //HashTable implementation

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        Map<Integer, String> anotherMap = new LLQHashTableMap(6);

        try{
            anotherMap.removeAny();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        System.out.println(anotherMap);

        anotherMap.define(3, "Adam3");
        anotherMap.define(2, "Adam2");
        anotherMap.define(1, "Adam1");
        anotherMap.define(4, "Adam4");
        anotherMap.define(0, "Adam0");

        System.out.println(anotherMap);
        System.out.println(anotherMap.getSize());

        anotherMap.remove(1);
        System.out.println(anotherMap);
        System.out.println(anotherMap.getSize());

        anotherMap.remove(6);
        System.out.println(anotherMap);
        System.out.println(anotherMap.getSize());

        for(int i = 0; i<2;i++){
            anotherMap.define(i, "Adam6");
        }

        System.out.println(anotherMap);
        System.out.println(anotherMap.getSize());

        anotherMap.define(5,"Adam7");
        anotherMap.define(7,"Adam7");
        anotherMap.define(6,"Adam7");

        System.out.println(anotherMap);
        System.out.println(anotherMap.getSize());

        try{
            for(int i = 0; i<2; i++){
                KeyValuePair<Integer, String> pair = anotherMap.removeAny();
                System.out.println(pair);
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        System.out.println(anotherMap);
        System.out.println(anotherMap.getSize());

        anotherMap.clear();
        System.out.println(anotherMap);
        System.out.println(anotherMap.getSize());

        anotherMap.define(1, "Adam1");
        anotherMap.define(2, "Adam2");

        System.out.println(anotherMap);
        System.out.println(anotherMap.getSize());


        //BSTHashTableMap<K, V> implementation

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        Map<Integer, String> thirdMap = new BSTHashTableMap<>(6);

        try{
            thirdMap.removeAny();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        System.out.println(thirdMap);

        thirdMap.define(3, "Adam3");
        thirdMap.define(2, "Adam2");
        thirdMap.define(1, "Adam1");
        thirdMap.define(4, "Adam4");
        thirdMap.define(0, "Adam0");

        System.out.println(thirdMap);
        System.out.println(thirdMap.getSize());

        thirdMap.remove(1);
        System.out.println(thirdMap);
        System.out.println(thirdMap.getSize());

        thirdMap.remove(6);
        System.out.println(thirdMap);
        System.out.println(thirdMap.getSize());

        for(int i = 0; i<2;i++){
            thirdMap.define(i, "Adam6");
        }

        System.out.println(thirdMap);
        System.out.println(thirdMap.getSize());

        thirdMap.define(5,"Adam7");
        thirdMap.define(7,"Adam7");
        thirdMap.define(6,"Adam7");

        System.out.println(thirdMap);
        System.out.println(thirdMap.getSize());

        try{
            for(int i = 0; i<2; i++){
                KeyValuePair<Integer, String> pair = thirdMap.removeAny();
                System.out.println(pair);
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        System.out.println(thirdMap);
        System.out.println(thirdMap.getSize());

        thirdMap.clear();
        System.out.println(thirdMap);
        System.out.println(thirdMap.getSize());

        thirdMap.define(1, "Adam1");
        thirdMap.define(2, "Adam2");

        System.out.println(thirdMap);
        System.out.println(thirdMap.getSize());

    }
}
