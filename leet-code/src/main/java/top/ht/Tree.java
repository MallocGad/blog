package top.ht;

import java.util.*;

/**
 * @Author: ht
 * @Date: Create in 19:01 2022/3/7
 * @Describe:
 * @Last_change:
 */
public class Tree {
    /**
     * 94.二叉树中序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            result.add(root.val);
            root = root.right;
        }
        return result;
    }

    List<Integer> result = new ArrayList<>();

    public List<Integer> inorderTraversal1(TreeNode root) {
        if (root == null) {
            return result;
        }
        inorderTraversal1(root.left);
        result.add(root.val);
        inorderTraversal1(root.right);
        return result;
    }

    /**
     * 100 相同的数
     *
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        Stack<TreeNode> stackP = new Stack<>();
        Stack<TreeNode> stackQ = new Stack<>();
        while (p != null && !stackP.isEmpty()) {
            while (p != null) {
                if (q == null) {
                    return false;
                }
                stackP.push(p);
                stackQ.push(q);
                p = p.left;
                q = q.left;
            }


        }
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        }
        return isSameTree(p.left, q.left);
    }

    boolean isSameTreeresult;

    public boolean isSameTree2(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        } else if (p.val != q.val) {
            return false;
        }
        isSameTreeresult = isSameTree2(p.left, q.left);
        if (!isSameTreeresult) {
            return false;
        }
        return isSameTree2(p.right, q.right);
    }

    public boolean isSameTree3(TreeNode p, TreeNode q) {
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        while (p != null || q != null) {
            if (p == null || q == null) {
                return false;
            }
            if (p.val != q.val) {
                return false;
            }
            if (p.left != null && q.left != null) {
                queue1.offer(p.left);
                queue2.offer(q.left);
            } else if (p.left == null ^ q.left == null) {
                return false;
            }
            if (p.right != null && q.right != null) {
                queue1.offer(p.right);
                queue2.offer(q.right);
            } else if (p.right == null ^ q.right == null) {
                return false;
            }
            p = queue1.poll();
            q = queue2.poll();
        }
        return true;
    }

    public int maxDepth(TreeNode root) {
        if (root == null){
            return 0;
        }
        return Math.max(maxDepth(root.right),maxDepth(root.left)) + 1;
    }


    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
