package com.company;

public class BinarySearchTree {
    private Node tree;

    public Node find(int data) {
        Node tim = tree;
        while (tim != null) {
            if (data >= tim.data) {
                if (data > tim.data) {
                    tim = tim.right;
                } else {
                    return tim;
                }
            } else {
                tim = tim.left;
            }
        }

        return null;
    }

    public void insert(int data) {
        if (tree == null) {
            tree = new Node(data);
        }

        Node tim = tree;
        while (tim != null) {
            if (data <= tim.data) { // data < tim.data
                if (tim.left == null) {
                    tim.left = new Node(data);
                }
                tim = tim.left;
            } else {
                if (tim.right == null) {
                    tim.right = new Node(data);
                }
                tim = tim.right;
            }
        }
    }

    public void delete(int data) {
        Node tim = tree;
        Node TT = null;
        while (tim != null && tim.data != data) {
            TT = tim;
            if (data <= tim.data) {
                tim = tim.left;
            } else {
                tim = tim.right;
            }
        }
        if (tim == null) { }

        if (tim.left == null || tim.right == null) {
        } else {
            Node mintim = tim.right;
            Node minTT = tim;
            while (mintim.left != null) {
                minTT = mintim;
                mintim = mintim.left;
            }
            tim.data = mintim.data;
            tim = mintim;
            TT = mintim;
        }

        Node child;
        if (tim.left == null) {
            if (tim.right != null) {
                child = tim.right;
            }
            else {
                child = null;
            }
        } else {
            child = tim.left;
        }

        if (TT != null) {
            if (TT.left == tim) {
                TT.left = child;
            }
            else {
                TT.right = child;
            }
        } else {
            tree = child;
        }
    }

    public Node findMin() {
        if (tree == null) {
            return null;
        }
        Node tim = tree;
        while (tim.left != null) {
            tim = tim.left;
        }
        return tim;
    }

    public Node findMax() {
        if (tree == null) {
            return null;
        }
        Node tim = tree;
        while (tim.right != null) {
            tim = tim.right;
        }
        return tim;
    }

    public static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }
    }
}
