package top.ht.design;

/**
 * @Author: ht
 * @Date: Create in 17:40 2022/3/6
 * @Describe:
 * @Last_change:
 */
public class MyAvlTree {
    static class Node {
        // 不是树高度，高度应该-1；
        int height;
        int data;
        Node letf;
        Node right;

        public Node(int data) {
            this.data = data;
            this.height = 1;
        }
    }

    public Node insert(Node n, int data) {
        if (n == null) {
            return new Node(data);
        }
        if (n.data > data) {
            n.letf = insert(n.letf, data);
        } else if (n.data < data) {
            n.right = insert(n.right, data);
        } else {
            return n;
        }
        int diff = getDiff(n);
        if (diff > 1 && n.letf.data > data) {
            return rightRotate(n);
        }
        return null;
    }

    private Node rightRotate(Node n) {
        Node center = n.letf;
        center.right = n;
        n.height = Math.max(getHeight(n.letf), getHeight(n.right)) + 1;
        center.height = Math.max(getHeight(center.right), getHeight(center.letf)) + 1;
        return center;
    }

    private int getDiff(Node n) {
        if (null == n) {
            return 0;
        }
        return getHeight(n.letf) - getHeight(n.right);
    }

    public int getHeight(Node n) {
        if (null != n) {
            return n.height;
        }
        return 0;
    }

    public Node leftRotate(Node n) {
       return null;
    }
}
