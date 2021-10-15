package top.ht;

import org.springframework.util.StopWatch;

import javax.jnlp.ClipboardService;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (Objects.isNull(head)) {
            return null;
        }
        // 删除指针
        ListNode deletePointer = head;
        // 中间指针
        ListNode tempPointer = head;
        // 探路指针
        ListNode tailPointer = head;
        // 行走步数
        int t = 0;
        while (Objects.nonNull(tailPointer.next)) {
            tailPointer = tailPointer.next;
            t++;
            if (t == n) {
                // 删除指针在前防止跨过目标
                deletePointer = tempPointer;
                tempPointer = tailPointer;
                t = 0;
            }
        }
        // 在探路指针到达尾节点时，删除指针目前停留在倒数t+n个节点，所以删除指针需要在移动t-1步到达删除指针前一个节点
        for (int i = 0; i < t; i++) {
            deletePointer = deletePointer.next;
        }
        if (0 == t){

        }
        // 删除节点
        if (Objects.isNull(deletePointer.next)) {
            // 只有一个节点时
            return null;
        } else {
            deletePointer.next = deletePointer.next.next;
        }

        return head;
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


    public static void main(String[] args) throws UnsupportedEncodingException {
        //        List<List<Integer>> sum = new LeetCode2().fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0);
        //        System.out.println(sum);
//        List<List<Integer>> result = new LeetCode2().combinationSum(new int[]{5, 2, 3}, 8);
//        System.out.println(result);
//        BigDecimal multiply = new BigDecimal(10).multiply(new BigDecimal(2));
        StopWatch watch = new StopWatch();
        watch.start();
//        List<List<Integer>> res = new LeetCode2().fourSum(new int[]{-479, -472, -469, -461, -456, -420, -412, -403, -391, -377, -362, -361, -340, -336, -336, -323, -315, -301, -288, -272, -271, -246, -244, -227, -226, -225, -210, -194, -190, -187, -183, -176, -167, -143, -140, -123, -120, -74, -60, -40, -39, -37, -34, -33, -29, -26, -23, -18, -17, -11, -9, 6, 8, 20, 29, 35, 45, 48, 58, 65, 122, 124, 127, 129, 145, 164, 182, 198, 199, 206, 207, 217, 218, 226, 267, 274, 278, 278, 309, 322, 323, 327, 350, 361, 372, 376, 387, 391, 434, 449, 457, 465, 488}, 1979);
        List<List<Integer>> res = new LeetCode2().fourSum1(new int[]{0, 0, 0, 1000000000, 1000000000, 1000000000, 1000000000}, 1000000000);
        watch.stop();
        System.out.println(res);
        System.out.println(watch.getLastTaskTimeMillis());
//        list.iterator()
    }

    public static String decodeUTF8Str(String xStr) throws UnsupportedEncodingException {
        return URLDecoder.decode(xStr.replaceAll("\\\\x", "%"), "utf-8");
    }


}
