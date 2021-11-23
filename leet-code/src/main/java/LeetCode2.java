import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * LeetCode
 *
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
        // 如果目前还小，则判断最大的值是否大于零
        if (target > tempTarget && nums[nums.length-1] < 0) {
            return;
        }else if(temp.size() > 0 && target < tempTarget && temp.lastElement() > 0){
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

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // 中间结果值保存变量 可以改为栈试试效率
        int[] tempResult = new int[151];
        for (int i = 0; i < candidates.length; i++) {
            int tempTarget = 0;
            for (int j = i, n = 0; j < candidates.length && n < 150 && n >= 0; ) {
                int temp = tempTarget + candidates[j];
                // 入栈
                tempResult[n] = candidates[j];
                // 小于继续使用当前值 入栈
                if (temp < target) {
                    tempTarget = temp;
                    n++;
                } else {
                    if (target == temp) {
                        // 保存结果，继续移除上一次的数字，并将下一个值放入 出栈
                        if (n != 0 || i == 0) { // 这里当单个满足值时会出现重复的所以做了过滤
                            result.add(Arrays.stream(tempResult).limit(n + 1).boxed().collect(Collectors.toList()));
                        }
                    }
                    if (n == 0) {
                        j++;
                        continue;
                    }
                    // tempTarget减去上次值，移除上一次的数字，并将下一个值放入 出栈
                    tempTarget -= tempResult[--n];
                    j++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
//        List<List<Integer>> sum = new LeetCode2().fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0);
//        System.out.println(sum);

        String utf8String = "\\xe7\\xbb\\x93\\xe7\\xae\\x97\\xe4\\xbb\\xb7";
        System.out.println(decodeUTF8Str(utf8String));

        int[] a = new int[]{2, 3, 5};
        List<List<Integer>> lists = new LeetCode2().combinationSum(a, 8);
        System.out.println(lists);
    }

    public static String decodeUTF8Str(String xStr) throws UnsupportedEncodingException {
        return URLDecoder.decode(xStr.replaceAll("\\\\x", "%"), "utf-8");
    }


}
