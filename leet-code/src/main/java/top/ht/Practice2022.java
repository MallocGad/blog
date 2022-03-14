package top.ht;

/**
 * @Author: ht
 * @Date: Create in 20:20 2022/2/27
 * @Describe:
 * @Last_change:
 */
public class Practice2022 {
    /**
     * 896单调数列
     */
    public boolean isMonotonic(int[] nums) {
        if (nums.length <= 1) {
            return true;
        }
        int flag = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] < nums[i + 1]) {
                if (flag == 2) {
                    return false;
                }
                flag = 1;
            } else if (nums[i] > nums[i + 1]) {
                if (flag == 1) {
                    return false;
                }
                flag = 2;
            }
        }
        return true;
    }

    /**
     * 28. 实现strStr()
     */
    public int strStr(String haystack, String needle) {
        if (needle.length() <= 0) {
            return 0;
        }
        if (needle.length() > haystack.length()) {
            return -1;
        }
        char[] ch1 = haystack.toCharArray();
        char[] ch2 = needle.toCharArray();
        for (int i = 0; i <= ch1.length - ch2.length; i++) {
            if (ch1[i] == ch2[0]) {
                int j = 0;
                for (; j < ch2.length && ch1[i + j] == ch2[j]; ) {
                    j++;
                }
                if (j == ch2.length) {
                    return i;
                }
            }
        }
        return -1;
    }
}
