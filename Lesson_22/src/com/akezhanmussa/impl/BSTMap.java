package com.akezhanmussa.impl;
import com.akezhanmussa.adt.Map;


public class BSTMap <K extends Comparable, V> implements Map<K, V> {

    private TreeNode<KeyValuePair<K,V>> root;
    private int size;
    private V someRemovalValue = null;

    public BSTMap() {
        root = null;
        size = 0;
    }

    @Override
    public void define(K key, V value) {

        TreeNode<KeyValuePair<K, V>> pair = new TreeNode(new KeyValuePair(key, value));

        if (size == 0) {
            root = pair;
            size = 1;
        } else {
            addHelper(root, key, value);
        }
    }

    private void addHelper(TreeNode<KeyValuePair<K,V>> node, K key, V value){

        if (node.getValue().getKey().compareTo(key) < 0){
            if (node.getRight() == null){
                node.setRight(new TreeNode(new KeyValuePair<>(key, value)));
                size++;
            } else {
                addHelper(node.getRight(), key, value);
            }
        } else if (node.getValue().getKey().compareTo(key) > 0){
            if (node.getLeft() == null){
                node.setLeft(new TreeNode(new KeyValuePair<>(key, value)));
                size++;
            } else {
                addHelper(node.getLeft(), key, value);
            }
        } else {
            node.getValue().setValue(value);
        }
    }

    @Override
    public V getValue(K key) {

        if (size == 0){
            return null;
        }

        return containsHelper(root, key);
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

        if (removeHelper(root, root, false, key)){
            return someRemovalValue;
        }

        return null;
    }

    private boolean removeHelper(TreeNode<KeyValuePair<K,V>> node, TreeNode<KeyValuePair<K,V>> preNode, boolean isRight, K key){

        if (node == null){
            someRemovalValue = null;
            return false;
        }

        if (key.compareTo(node.getValue().getKey()) == 0){
            if (node.getLeft() == null && node.getRight() == null){
                if (size == 1){
                    root = null;
                } else {
                    if (isRight){
                        preNode.setRight(null);
                    } else {
                        preNode.setLeft(null);
                    }
                }
            } else if (node.getLeft() == null && node.getRight() != null){
                if (node == root){
                    root = node.getRight();
                } else {
                    if(isRight){
                        preNode.setRight(node.getRight());
                    } else {
                        preNode.setLeft(node.getRight());
                    }
                }

            } else if (node.getLeft() != null && node.getRight() == null){
                if (node == root){
                    root = node.getLeft();
                } else {
                    if(isRight){
                        preNode.setRight(node.getLeft());
                    } else {
                        preNode.setLeft(node.getLeft());
                    }
                }
            } else {
                TreeNode<KeyValuePair<K,V>> minimumLeaf = minimumLeafFinder(node.getRight(), node);

                if (node == root){
                    root.setValue(minimumLeaf.getValue());
                }else {
                    node.setValue(minimumLeaf.getValue());
                }
            }

            size --;

            someRemovalValue = node.getValue().getValue();
            return true;

        } else {

            return removeHelper(node.getRight(), node, true, key) || removeHelper(node.getLeft(), node, false, key);
        }
    }

    private TreeNode<KeyValuePair<K,V>> minimumLeafFinder(TreeNode<KeyValuePair<K,V>> node, TreeNode<KeyValuePair<K,V>> prevNode){

        if (node.getLeft() == null){

            if (node.getRight() != null){
                if (prevNode == root) {
                    prevNode.setValue(node.getValue());
                    prevNode.setRight(node.getRight());
                } else {
                    prevNode.setLeft(node.getRight());
                }
            } else {
                if (prevNode == root){
                    prevNode.setValue(node.getValue());
                    prevNode.setRight(null);
                } else {
                    prevNode.setLeft(null);
                }
            }

            return node;
        }

        return minimumLeafFinder(node.getLeft(), node);
    }

    @Override
    public KeyValuePair<K, V> removeAny() throws Exception {

        if (size == 0){
            throw new Exception("The set is Empty!");
        }

        KeyValuePair<K,V> rootValue = root.getValue();
        remove(rootValue.getKey());

        return rootValue;
    }

    @Override
    public int getSize() {

        return this.size;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public String toString() {

        return "[ " + toStringHelper(root) + " ]";
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


