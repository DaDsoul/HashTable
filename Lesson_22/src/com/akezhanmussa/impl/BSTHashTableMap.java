package com.akezhanmussa.impl;


import com.akezhanmussa.adt.HashTableMap;

public class BSTHashTableMap<K extends Comparable, V> implements HashTableMap<K, V> {

    private TreeNode<KeyValuePair<K,V>>[] roots;
    private int treeSizes[];
    private int size;
    private int bucketSize;
    private V someRemovalValue = null;

    public BSTHashTableMap(int bucketSize){
        this.bucketSize = bucketSize;
        this.size = 0;
        roots = new TreeNode[bucketSize];
        treeSizes = new int[bucketSize];
    }

    @Override
    public void define(K key, V value) {
        int index = hashFunction(key);

        TreeNode<KeyValuePair<K, V>> pair = new TreeNode(new KeyValuePair(key, value));

        if (roots[index] == null){
            roots[index] = pair;
            treeSizes[index] = 1;
            size ++;
        } else {
            addHelper(roots[index], key, value);
        }

    }

    private void addHelper(TreeNode<KeyValuePair<K,V>> node, K key, V value){

        int index = hashFunction(key);

        if (node.getValue().getKey().compareTo(key) < 0){
            if (node.getRight() == null){
                node.setRight(new TreeNode(new KeyValuePair<>(key, value)));
                treeSizes[index]++;
                size ++;
            } else {
                addHelper(node.getRight(), key, value);
            }
        } else if (node.getValue().getKey().compareTo(key) > 0){
            if (node.getLeft() == null){
                node.setLeft(new TreeNode(new KeyValuePair<>(key, value)));
                treeSizes[index]++;
                size ++;
            } else {
                addHelper(node.getLeft(), key, value);
            }
        } else {
            node.getValue().setValue(value);
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

        if (treeSizes[index] == 0){
            return null;
        }

        return containsHelper(roots[index], key);
    }

    private V containsHelper(TreeNode<KeyValuePair<K,V>> node, K key){
        if (node == null){
            return null;
        }

        if (key.compareTo(node.getValue().getKey()) > 0){
            return containsHelper(node.getRight(),key);
        } else if (key.compareTo(node.getValue().getKey()) < 0){
            return containsHelper(node.getLeft(), key);
        } else {
            return node.getValue().getValue();
        }
    }

    @Override
    public V remove(K key) {
        int index = hashFunction(key);

        if (removeHelper(roots[index], roots[index], false, key)){
            return someRemovalValue;
        }

        return null;
    }

    private boolean removeHelper(TreeNode<KeyValuePair<K,V>> node, TreeNode<KeyValuePair<K,V>> preNode, boolean isRight, K key){

        int index = hashFunction(key);

        if (node == null){
            someRemovalValue = null;
            return false;
        }

        if (key.compareTo(node.getValue().getKey()) == 0){
            if (node.getLeft() == null && node.getRight() == null){
                if (treeSizes[index] == 1){
                    roots[index] = null;
                } else {
                    if (isRight){
                        preNode.setRight(null);
                    } else {
                        preNode.setLeft(null);
                    }
                }
            } else if (node.getLeft() == null && node.getRight() != null){
                if (node == roots[index]){
                    roots[index] = node.getRight();
                } else {
                    if(isRight){
                        preNode.setRight(node.getRight());
                    } else {
                        preNode.setLeft(node.getRight());
                    }
                }

            } else if (node.getLeft() != null && node.getRight() == null){
                if (node == roots[index]){
                    roots[index] = node.getLeft();
                } else {
                    if(isRight){
                        preNode.setRight(node.getLeft());
                    } else {
                        preNode.setLeft(node.getLeft());
                    }
                }
            } else {
                TreeNode<KeyValuePair<K,V>> minimumLeaf = minimumLeafFinder(node.getRight(), node, key);

                if (node == roots[index]){
                    roots[index].setValue(minimumLeaf.getValue());
                }else {
                    node.setValue(minimumLeaf.getValue());
                }
            }

            treeSizes[index] --;
            size --;
            someRemovalValue = node.getValue().getValue();

            return true;
        } else {
            return removeHelper(node.getRight(), node, true, key) || removeHelper(node.getLeft(), node, false, key);
        }
    }

    private TreeNode<KeyValuePair<K,V>> minimumLeafFinder(TreeNode<KeyValuePair<K,V>> node, TreeNode<KeyValuePair<K,V>> prevNode, K key){

        int index = hashFunction(key);

        if (node.getLeft() == null){
            if (node.getRight() != null){
                if (prevNode == roots[index]) {
                    prevNode.setValue(node.getValue());
                    prevNode.setRight(node.getRight());
                } else {
                    prevNode.setLeft(node.getRight());
                }
            } else {
                if (prevNode == roots[index]){
                    prevNode.setValue(node.getValue());
                    prevNode.setRight(null);
                } else {
                    prevNode.setLeft(null);
                }
            }
            return node;
        }

        return minimumLeafFinder(node.getLeft(), node, key);
    }

    @Override
    public KeyValuePair<K, V> removeAny() throws Exception {

        if (size == 0){
            throw new Exception("The Map is empty");
        }

        KeyValuePair<K,V> object = null;

        for(int i = 0; i<bucketSize; i++){
            if (roots[i] != null){
                if (treeSizes[i] != 0){
                    object = roots[i].getValue();
                    remove(object.getKey());
                    break;
                }
            }
        }

        return object;
    }


    @Override
    public String toString() {
        String result = "[ --";
        for(int i = 0; i<bucketSize;i++){
            if (roots[i] != null){
                result += toStringHelper(roots[i]) + " -- ";
            }
        }
        return result + " ]";
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public void clear() {
        this.bucketSize = bucketSize;
        this.size = 0;
        roots = new TreeNode[bucketSize];

        treeSizes = new int[bucketSize];

        for(int i = 0; i<bucketSize; i++){
            treeSizes[i] = 0;
        }
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
        return treeSizes[index];

    }

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
            if (roots[i] == null){
                bucketArray[i] = 0;
            } else {
                bucketArray[i] = treeSizes[i];
            }
        }

        for(int i = 0; i<bucketSize; i++){
            stdv += Math.pow(bucketArray[i] - mean, 2);
        }

        stdv /= bucketSize;
        stdv = Math.pow(stdv, 0.5);

        return stdv;
    }

    @Override
    public String bucketsToString() {
        String result = "[\n";
        for(int i = 0; i<bucketSize;i++){
            if (roots[i] != null){
                if (treeSizes[i] != 0){
                    result += " The bucket of index " + i + ": ";
                    result += toStringHelper(roots[i]);
                }
            } else {
                result += " The bucket of index " + i + ": is empty ";
            }

            result += '\n';

        }
        return result + "]";
    }

    private String toStringHelper(TreeNode<KeyValuePair<K,V>> node){
        if (node == null){
            return "";
        }

        return toStringHelper(node.getLeft()) + " -- " +
                node.getValue() + " -- " +
                toStringHelper(node.getRight());
    }
}
