package top.ht;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode代码
 * @author : huangtao3
 * @since : 2020/8/8 14:33
 */
public class LeetCode {
    public void twoPointorRange(int[] height) {
        areaMax(height);
    }

    public int areaMax(int[] a) {
        int i = 0, j = a.length - 1, max = 0;
        while (i < j) {
            int area = (j - i) * Math.min(a[i], a[j]);
            max = Math.max(max, area);
            if (a[i] < a[j]) {
                ++i;
            } else {
                --j;
            }
        }
        return max;
    }

    public String intToRoman(int num) {
        int[] nums = new int[]{1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
        String[] romans = new String[]{"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
        int pointer = 12;
        int count;
        StringBuilder roman = new StringBuilder();
        while (num > 0) {
            if (0 != (count = num / nums[pointer])) {
                num %= nums[pointer];
                for (int i = 0; i < count; i++) {
                    roman.append(romans[pointer]);
                }
            }
            pointer--;
        }
        return roman.toString();
    }

    public int romanToInt(String s) {
        int[] m = new int[90];
        m['I'] = 1;
        m['V'] = 5;
        m['X'] = 10;
        m['L'] = 50;
        m['C'] = 100;
        m['D'] = 500;
        m['M'] = 1000;
        int num = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i < chars.length - 1 && m[chars[i]] < m[chars[i + 1]]) {
                num -= m[chars[i]];
            } else {
                num += m[chars[i]];
            }
        }
        return num;
    }

    /**
     * 最长公共前缀
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        String str = strs[0];
        for (int i = 1; strs.length > i; i++) {
            int j = 0;
            for (; str.length() > j && strs[i].length() > j; j++) {
                if (str.charAt(j) != strs[i].charAt(j)) {
                    break;
                }
            }
            str = strs[i].substring(0, j);
            if (str.length() == 0) {
                return "";
            }
        }
        return str;
    }

    /**
     * 正则匹配
     */
    public boolean isMatch(String s, String p) {
        char[] charS = s.toCharArray();
        char[] charP = p.toCharArray();
        boolean[][] matchs = new boolean[1000][1000];
        matchs[0][0] = true;
        for (int j = 0; j <= charP.length; j++) {
            for (int i = 0; i <= charS.length; i++) {
                if (j > 0 && i > 0 && (charP[j - 1] == charS[i - 1] || charP[j - 1] == '.')) {
                    matchs[i][j] = matchs[i - 1][j - 1];
                } else if (j > 0 && charP[j - 1] == '*') {
                    if (i > 0 && (charP[j - 2] == charS[i - 1] || charP[j - 2] == '.')) {
                        matchs[i][j] = matchs[i - 1][j] || matchs[i][j - 1] || matchs[i][j - 2];
                    } else {
                        matchs[i][j] = matchs[i][j - 2];
                    }
                }
            }
        }
        return matchs[charS.length][charP.length];
    }

    /**
     * 三数之和
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        //保存重复数字集合
        for (int i = 0; i < n - 2; i++) {
            int third = n - 1;
            //第一位数重复的直接过滤
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < n - 1; j++) {
                //第二位数重复直接过滤
                if (j > i + 1 && nums[j - 1] == nums[j]) {
                    continue;
                }
                while (third > j && -nums[third] < nums[i] + nums[j]) {
                    third--;
                }
                if (third == j) {
                    break;
                }
                if (-nums[third] == nums[i] + nums[j]) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[third]);
                    result.add(list);
                }
            }
        }
        return result;
    }

    /**
     * 快速排序
     *
     * @param nums
     * @return
     */
    public int[] sortArray(int[] nums) {
        quikSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quikSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int flag = nums[right];
        int i = left;
        int j = right;
        while (i < j) {
            while (i < j && nums[i] <= flag) {
                i++;
            }
            nums[j] = nums[i];
            while (i < j && flag <= nums[j]) {
                j--;
            }
            nums[i] = nums[j];
        }
        nums[i] = flag;
        quikSort(nums, left, i - 1);
        quikSort(nums, i + 1, right);
    }

    /**
     * 最接近的三数之和   单指针解法
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        //差距最小值
        int min = 99999;
        //和
        int sum = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                //临时差距最小值
                int tempMin = 99999;
                int lastSum = 0;
                for (int k = j + 1; k < nums.length; k++) {
                    //差距最小值时和
                    int sumTemp = nums[i] + nums[j] + nums[k];
                    int temp = Math.abs(sumTemp - target);
                    //如果循环中数字开始增大或到了最后一个数字，则停止循环
                    if (temp > tempMin || temp == 0 || k == nums.length - 1) {
                        if (temp > tempMin) {
                            //最小的差距在上一次，则和应该是上一次的
                            sumTemp = lastSum;
                        }
                        tempMin = Math.min(temp, tempMin);
                        //推出前判断是否为目前为止的最小值，如是则替换之前和
                        if (min > tempMin) {
                            min = tempMin;
                            sum = sumTemp;
                        }
                        break;
                    }
                    lastSum = sumTemp;
                    tempMin = temp;
                }
            }
        }
        return sum;
    }

    /**
     * 双指针解法
     */
    public int threeSumClosestDoublePointer(int[] nums, int target) {
        int min = 99999;
        int sum=0;
        for (int i = 0; i < nums.length - 2; i++) {
            int j = i + 1, k = nums.length - 1;
            //target-i ~ j+k
            int targetI = target - nums[i];
            int tempMin=99999;
            while (j < k) {
                if (nums[j] + nums[k] > targetI) {
                    k--;
                } else {
                    j++;
                }
            }

        }
        return 1;
    }

    @Test
    public void test() {
        int[] nums = new int[]{0,0,0,0};
        List<List<Integer>> lists = threeSum(nums);
        System.out.println(lists);
    }

    static class Node{
        int v;
        Node next;
        Node(int v,Node next){
            this.v= v;
            this.next=next;
        }
    }
    public static void main(String[] args) {
        Node head = new Node(0,null);
        Node pre = head;
        for (int i = 1; i < 10; i++) {
            Node node = new Node(i, null);
            pre.next = node;
            pre = node;
        }
        Node pointer = head;
        while(pointer != null){
            System.out.println(pointer.v);
            pointer = pointer.next;
        }
    }
}
