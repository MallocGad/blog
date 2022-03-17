package top.ht;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

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

    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        int left = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                // 之前重复字符以及之前的字符移除，开始新的一轮计算
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            // 保存字符坐标
            map.put(s.charAt(i), i);
            max = Math.max(max, i - left + 1);
        }
        return max;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        boolean flag1 = false, flag2 = false;
        ListNode listNode1 = headA, listNode2 = headB;
        while (listNode1 != null && listNode2 != null) {
            if (listNode1 == listNode2) {
                return listNode1;
            }
            listNode1 = listNode1.next;
            listNode2 = listNode2.next;
            if (listNode1 == null && !flag1) {
                flag1 = true;
                listNode1 = headB;
            }
            if (listNode2 == null && !flag2) {
                flag2 = true;
                listNode2 = headA;
            }
        }
        return null;
    }

    public int majorityElement(int[] nums) {
        int count = 1, pre = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                pre = nums[i];
                count = 1;
            } else if (pre == nums[i]) {
                count++;
            } else {
                count--;
            }
        }
        return pre;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode tail = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return tail;
    }

    /**
     * 234. 回文链表
     */
    public boolean isPalindrome(ListNode head) {
        ArrayList<Integer> list = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            list.add(node.val);
            node = node.next;
        }
        int end = list.size() - 1, star = 0;
        while (star < end) {
            if (!list.get(star).equals(list.get(end))) {
                return false;
            }
            star++;
            end--;
        }
        return true;
    }

    /**
     * 448. 找到所有数组中消失的数字
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int[] nbs = new int[nums.length];
        for (int num : nums) {
            nbs[num - 1] = 1;
        }
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nbs[i] == 0) {
                res.add(i + 1);
            }
        }
        return res;
    }

    public List<Integer> findDisappearedNumbers2(int[] nums) {
        ArrayList<Integer> list = new ArrayList<>();
        int n = nums.length;
        for (int num : nums) {
            int pos = (num - 1) % n;
            // nums[i]一定是小于等于n的，+n之后一定大于n
            nums[pos] += n;
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) {
                list.add(i);
            }
        }
        return list;
    }

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
}
