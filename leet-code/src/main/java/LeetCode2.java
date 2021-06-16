import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        int[] temp = new int[4];
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 4) {
            return result;
        }
        Arrays.sort(nums);
        nSumRecursion(nums, target, 0, 4, temp, result, 0);
        return result;
    }

    /**
     * n数之和
     */
    private void nSumRecursion(int[] nums, int target, int deep, int n, int[] temp, List<List<Integer>> result, int pointer) {
        // 选了n-1个数之后通过大小比较减少判断次数
        if (n - 1 == deep) {
            int sum = 0;
            for (int m = 0; m < n - 1; m++) {
                sum += nums[temp[m]];
            }
            for (int i = pointer; i < nums.length; i++) {
                if (sum + nums[i] > target) {
                    return;
                } else if (sum + nums[i] == target) {
                    temp[deep] = i;
                    ArrayList<Integer> integers = new ArrayList<>();
                    for (int idx : temp) {
                        integers.add(nums[idx]);
                    }
                    result.add(integers);
                }
            }
            return;
        }
        for (int i = pointer; i < nums.length - n + deep; i++) {
            temp[deep] = i;
            nSumRecursion(nums, target, deep + 1, n, temp, result, i + 1);
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
//        List<List<Integer>> sum = new LeetCode2().fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0);
//        System.out.println(sum);

        String utf8String = "\\xe7\\xbb\\x93\\xe7\\xae\\x97\\xe4\\xbb\\xb7";
        System.out.println(decodeUTF8Str(utf8String));


    }

    public static String decodeUTF8Str(String xStr) throws UnsupportedEncodingException {
        return URLDecoder.decode(xStr.replaceAll("\\\\x", "%"), "utf-8");
    }


}
