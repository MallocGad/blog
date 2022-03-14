package top.ht;

import org.springframework.util.StopWatch;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

/**
 * @author: huangtao3
 * @since: 2021/4/2 17:13
 */
public class LeetCode2 {


    /**
     * 归并排序
     *
     * @param arr
     */
    public static void mergeSort(int[] arr) {
        int[] temp = new int[arr.length];
        recursion(arr, 0, arr.length - 1, temp);
    }

    /**
     * 分治不一定要迭代到底，可以到一定程度之后采用其他的排序方法排序
     */
    private static void recursion(int[] arr, int left, int right, int[] temp) {
        if (left == right) {
            return;
        }
        int mid = (left + right) / 2;
        recursion(arr, left, mid, temp);
        recursion(arr, mid + 1, right, temp);
        merge(arr, left, mid, right, temp);
    }

    /**
     * 这里合并
     */
    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int pointL = left, pointR = mid + 1;
        for (int i = left; i <= right; ) {
            // 防止越界，必须在前面
            if (pointR > right) {
                temp[i++] = arr[pointL++];
                continue;
            }
            if (pointL > mid) {
                temp[i++] = arr[pointR++];
                continue;
            }
            // 合并的时候L<=R能保证稳定性
            if (arr[pointL] <= arr[pointR]) {
                temp[i++] = arr[pointL++];
            } else if (arr[pointL] > arr[pointR]) {
                temp[i++] = arr[pointR++];
            }
        }
        // 排序后数组插入源数组
        System.arraycopy(temp, left, arr, left, right - left + 1);
    }

    /**
     * leetCode 17. 电话号码的字母组合
     * <p>
     * 递归解法
     */
    public List<String> letterCombinations(String digits) {
        char[][] phone = {{'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'}, {'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'}, {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}};

        char[] array = digits.toCharArray();
        int[] index = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            index[i] = array[i] - '2';
        }
        List<String> result = new ArrayList<>();
        if ("".equals(digits)) {
            return result;
        }
        StringBuilder temp = new StringBuilder();
        letterRecursion(0, temp, index, result, phone);
        return result;
    }

    private void letterRecursion(int deep, StringBuilder temp, int[] index, List<String> result, char[][] phone) {
        if (index.length == deep) {
            result.add(temp.toString());
            return;
        }
        int num = index[deep];
        for (int i = 0; i < phone[num].length; i++) {
            temp.append(phone[num][i]);
            letterRecursion(deep + 1, temp, index, result, phone);
            temp.deleteCharAt(deep);
        }
    }

    /**
     * 18. 四数之和
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {

        Stack<Integer> temp = new Stack<>();
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 4) {
            return result;
        }
        // 排序主要是方便过滤重复组合，同时顺序判断
        Arrays.sort(nums);
        nSumRecursion(nums, target, 4, temp, result, 0, 0);
        return result;
    }

    public List<List<Integer>> fourSum1(int[] nums, int target) {

        List<List<Integer>> result = new ArrayList<>();
        if (null == nums || nums.length < 4) {
            return result;
        }
        // 排序主要是方便过滤重复组合，同时提前预判结束
        Arrays.sort(nums);
        // 防止溢出
        long t = 0;
        for (int i = 0; i < nums.length - 3; i++) {
            // 过滤重复的
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            // 如果第一个选取后，最小的组合就大于目标值，说明后续均不满足了，提前结束判断
            if (t + nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            // 当选了第一个数可以预判最大的组合能否大于目标值，如果不能，说明当前第一个数可以继续向大的数选取
            if (t + nums[i] + nums[nums.length - 1] + nums[nums.length - 2] + nums[nums.length - 3] < target) {
                continue;
            }
            for (int j = i + 1; j < nums.length - 2; j++) {
                // 过滤重复
                if (j > i + 1 && nums[j - 1] == nums[j]) {
                    continue;
                }
                // 同理-判断最小
                if (t + nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }
                // 同理-判断最大
                if (t + nums[i] + nums[j] + nums[nums.length - 1] + nums[nums.length - 2] < target) {
                    continue;
                }
                // 最后两个数，则可以使用双指针，加快执行
                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    // 过滤重复
                    if (left > j + 1 && nums[left - 1] == nums[left]) {
                        left++;
                        continue;
                    }
                    if (right < nums.length - 1 && nums[right + 1] == nums[right]) {
                        right--;
                        continue;
                    }
                    int tempTarget = nums[i] + nums[j] + nums[left] + nums[right];
                    if (tempTarget == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        right--;
                        left++;
                    } else if (tempTarget < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }

        }
        return result;
    }

    /**
     * n数之和
     *
     * @param nums       待选数组
     * @param target     目标值
     * @param n          需要选择几个数
     * @param temp       保存选取临时数组，出事为空栈
     * @param result     结果集
     * @param pointer    所选数的游标。如：选取四个数，如第一个数坐标从零开始，第二个数则总1开始，point则记录所选的第n个数的坐标值
     * @param tempTarget 临时变量
     */
    private void nSumRecursion(int[] nums, int target, int n, Stack<Integer> temp, List<List<Integer>> result, int pointer, int tempTarget) {
        // 选了n个数之后通过大小比较减少判断次数
        if (n == temp.size()) {
            if (tempTarget == target) {
                result.add(new ArrayList<>(temp));
                return;
            }
            return;
        }
        // TODO 可以增加判断
        if (tempTarget > 0 && target < 0) {
            return;
        }
        for (int i = pointer; i < nums.length; i++) {
            // 过滤重复的值
            if (i > pointer && nums[i - 1] == nums[i]) {
                continue;
            }
            temp.push(nums[i]);
            nSumRecursion(nums, target, n, temp, result, i + 1, tempTarget + nums[i]);
            temp.pop();
        }
    }

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 19. 删除链表的倒数第 N 个结点
     */
    int cur = 0;

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (Objects.isNull(head)) {
            return null;
        }
        head.next = removeNthFromEnd(head.next, n);
        // cur是在到达尾部后开始增加的
        cur++;
        if (cur == n) {
            head = head.next;
        }
        return head;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode pre, next;
        ListNode node = new ListNode();
        node.next = head;
        next = node;
        while (n > 0) {
            next = next.next;
            n--;
        }
        pre = node;
        while (next.next != null) {
            pre = pre.next;
            next = next.next;
        }
        pre.next = pre.next.next;
        return node.next;
    }

    /**
     * 39. 组合总和
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        Stack<Integer> tempResult = new Stack<>();
        List<List<Integer>> result = new ArrayList<>();
        int tempTarget = 0;
        for (int i = 0; i < candidates.length; i++) {
            tempResult.push(candidates[i]);
            tempTarget += candidates[i];

        }
        dps(result, tempResult, candidates, 0, target);
        return result;
    }

    public class TreeNode {
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

    /**
     * 94.二叉树中序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (Objects.nonNull(root) || !stack.empty()) {
            while (Objects.nonNull(root)) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            result.add(root.val);
            root = root.right;
        }
        return result;
    }


    private void dps(List<List<Integer>> result, Stack<Integer> tempResult, int[] candidates, int point, int target) {
        if (target == 0) {
            result.add(new ArrayList<>(tempResult));
            return;
        }
        if (target < 0) {
            return;
        }
        for (int i = point; i < candidates.length; i++) {
            tempResult.push(candidates[i]);
            dps(result, tempResult, candidates, i, target -= candidates[i]);
            tempResult.pop();
        }
    }

    /**
     * 206 反转链表
     */
//    class Solution {
//        public ListNode reverseList(ListNode head) {
//            if (null == head || head.next ==null){
//                return head;
//            }
//            ListNode next = reverseList(head.next);
//            // 到了最后一个节点
//            head.next.next = head;
//            head.next = null;
//            return next;
//        }
//    }
    public ListNode reverseList(ListNode head) {

        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 704 二分查找
     */
    public int search(int[] nums, int target) {
        int top = nums.length - 1, flower = 0, mid;
        while (top >= flower) {
            mid = (top - flower) / 2 + flower;

            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > target) {
                top = mid - 1;
            } else {
                flower = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 278. 第一个错误的版本
     */
    public int firstBadVersion(int n) {
        int mid, top = n, low = 1;
        // 记录最后一个出现坏的和不坏的，方便查找临界值
        while (top > low) {
            mid = (top - low) / 2 + low;
            if (isBadVersion(mid)) {
                top = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return isBadVersion(top) ? top : top + 1;
    }


    public void listRevered(ListNode head) {
        next(head, head.next);
        head.next = null;
    }

    private void next(ListNode pre, ListNode node) {
        if (node == null) {
            return;
        }
        next(node, node.next);
        node.next = pre;
    }


    /**
     * 35. 搜索插入位置
     */
    public int searchInsert(int[] nums, int target) {
        int top = nums.length - 1, low = 0, mid;
        // 最后一个小于target的位置
        int lastMin = 0;
        while (top >= low) {
            mid = low + (top - low) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (target > nums[mid]) {
                low = mid + 1;
                lastMin = mid;
            } else {
                top = mid - 1;
            }
        }
        // 处理特殊情况：当target要被插入首位时
        if (nums[lastMin] < target) {
            return lastMin + 1;
        }
        return lastMin;
    }

    /**
     * 101. 对称二叉树
     */
    public boolean isSymmetric(TreeNode root) {
        return compare(root.left, root.right);
    }

    private boolean compare(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return compare(left.left, right.right) && compare(left.right, right.left);
    }

    public boolean isSymmetric1(TreeNode root) {
        LinkedList<TreeNode> queue1 = new LinkedList<>();
        LinkedList<TreeNode> queue2 = new LinkedList<>();
        // 题目描述了root不为空
        TreeNode left = root.left;
        TreeNode right = root.right;
        while (left != null || right != null) {
            if (left == null || right == null) {
                return false;
            }
            if (left.val != right.val) {
                return false;
            }
            if (left.left == null ^ right.right == null) {
                return false;
            }
            if (left.right == null ^ right.left == null) {
                return false;
            }
            // 这里只需要判断一个就行，因为前面已经保证左右一定不为null
            if (left.left != null) {
                queue1.offer(left.left);
                queue2.offer(right.right);
            }
            if (left.right != null) {
                queue1.offer(right.left);
                queue2.offer(left.right);
            }
            left = queue1.poll();
            right = queue2.poll();
        }
        return true;
    }
    /**
     * 136. 只出现一次的数字
     */
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res = res ^ nums[i];
        }
        return res;
    }

    public int singleNumber2(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])){
                map.put(nums[i],2);
            }else{
                map.put(nums[i],1);
            }
        }
        for (Integer key : map.keySet()){
            if (map.get(key) == 1){
                return key;
            }
        }
        return -1;
    }

    boolean isBadVersion(int version) {
        return true;
    }

    /**
     * 98. 验证二叉搜索树
     */
    int pre = Integer.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!isValidBST(root.left)) {
            return false;
        }
        if (pre > root.val) {
            return false;
        }
        pre = root.val;
        return isValidBST(root.right);
    }

    /**
     * 99. 恢复二叉搜索树
     */
    TreeNode firstNode = null, secondNode = null, preNode = null;

    public void recoverTree(TreeNode root) {
        recoverTreeDef(root);
        int temp = firstNode.val;
        firstNode.val = secondNode.val;
        secondNode.val = temp;
    }

    void recoverTreeDef(TreeNode root) {
        if (null == root) {
            return;
        }
        recoverTreeDef(root.left);
        if (preNode != null && preNode.val > root.val) {
            secondNode = root;
            if (firstNode == null) {
                firstNode = preNode;
            } else {
                return;
            }
        }
        preNode = root;
        recoverTreeDef(root.right);
    }

    /**
     * @param flag true 左子树，false右子数
     */
    boolean dfs(TreeNode root, List<Integer> fathers, boolean flag) {
        if (root == null) {
            return true;
        }
        for (Integer father : fathers) {
            if (flag && root.val >= father) {
                return false;
            } else if (!flag && root.val <= father) {
                return false;
            }
        }
        fathers.add(root.val);
        boolean b = dfs(root.left, fathers, true) &&
                dfs(root.right, fathers, false);
        fathers.remove(fathers.size() - 1);
        return b;
    }

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (null == matrix || matrix.length <= 0 || matrix[0].length <= 0) {
            return false;
        }
        int row = matrix.length - 1;
        int maxCol, minCol;
        // 查找matrix[0]中最后一个小于target目标的列
        maxCol = findInterval(matrix[0], target);
        // 此时low处于 > target 或 < target的临界点，需要具体判断一下
        if (matrix[0][maxCol] == target) {
            return true;
        }
        maxCol = matrix[0][maxCol] < target ? maxCol : maxCol - 1;
        // maxtrix[row]中第一个大于target的目标列;
        minCol = findInterval(matrix[row], target);
        if (matrix[row][minCol] == target) {
            return true;
        }
        minCol = matrix[row][minCol] > target ? minCol : minCol + 1;

        // 此时target只能在[minCol,maxCol]之间
        for (int i = minCol; i <= maxCol; i++) {
            int low = 0, top = row, mid = 0;
            while (low <= top) {
                mid = low + (top - low) / 2;
                if (matrix[mid][i] > target) {
                    top = mid - 1;
                } else if (matrix[mid][i] < target) {
                    low = mid + 1;
                } else if (matrix[mid][i] == target) {
                    return true;
                }
            }
        }
        return false;
    }


    int findInterval(int[] num, int target) {
        int low = 0, top = num.length - 1, mid;
        while (low < top) {
            mid = low + (top - low) / 2;
            if (num[mid] > target) {
                top = mid - 1;
            } else if (num[mid] < target) {
                low = mid + 1;
            } else if (num[mid] == target) {
                return mid;
            }
        }
        return low;
    }

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];
            if (map.get(temp) != null) {
                return new int[]{i, map.get(temp)};
            } else {
                map.put(nums[i], i);
            }
        }
        return null;
    }

    /**
     * 20. 有效的括号
     */
    public boolean isValid(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');
        for (char aChar : chars) {
            if (aChar == '(' || aChar == '[' || aChar == '{') {
                stack.push(aChar);
            } else if (stack.empty() || !map.get(aChar).equals(stack.pop())) {
                return false;
            }
        }
        return stack.empty();
    }

    /**
     * 21. 合并两个有序链表
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null) {
            return null;
        }
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode head, pre;

        if (list1.val > list2.val) {
            pre = head = list2;
            list2 = list2.next;
        } else {
            pre = head = list1;
            list1 = list1.next;
        }
        while (list1 != null && list2 != null) {
            if (list1.val > list2.val) {
                pre.next = list2;
                list2 = list2.next;
            } else {
                pre.next = list1;
                list1 = list1.next;
            }
            pre = pre.next;
        }
        if (list1 != null) {
            pre.next = list1;
        } else {
            pre.next = list2;
        }
        return head;
    }

    /**
     * 53. 最大子数组和
     */
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
        }
        int max = dp[0];
        for (int i = 1; i < dp.length; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        int[][] num = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
        new LeetCode2().findNumberIn2DArray(num, 5);
    }

    public static String decodeUTF8Str(String xStr) throws UnsupportedEncodingException {
        return URLDecoder.decode(xStr.replaceAll("\\\\x", "%"), "utf-8");
    }


}
