package com.company;

public class Main {

    public static void main(String[] args) {
	    HashTable hashTable = new HashTable();
	    BinarySearchTree searchTree = new BinarySearchTree();
	    searchTree.insert(1);
        searchTree.insert(4);
        searchTree.insert(6);
        searchTree.insert(5);
        searchTree.insert(3);
        System.out.println(searchTree.find(5));
    }
}
