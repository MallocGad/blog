package top.ht.listNode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * @author: huangtao3
 * @since: 2022/1/25 15:06
 */
public class Solutions {
    /**
     * 环形链表
     */
    public boolean hasCycle(ListNode head) {
        ListNode fastPtr = head;
        ListNode slowPtr = head;
        while (fastPtr != null && fastPtr.next != null) {
            fastPtr = fastPtr.next.next;
            slowPtr = slowPtr.next;
            if (slowPtr == fastPtr && fastPtr != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * 环形链表 II
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slowPtr = head, fastPtr = head;
        while (fastPtr != null && fastPtr.next != null) {
            fastPtr = fastPtr.next.next;
            slowPtr = slowPtr.next;
            if (fastPtr == slowPtr) {
                // 进过公式推出，头结点到入环节点距离D与入环节点到相遇点距离S1，
                // 以及相遇点到入环节点距离S2的关系为：(n-1)(S1+S2)-S1=D
                // 即两个指针在相遇点和头节点以相同的速度往前走，最终会在入环节点相遇
                while (head != slowPtr) {
                    head = head.next;
                    slowPtr = slowPtr.next;
                }
                return slowPtr;
            }
        }
        return null;
    }

    /**
     * 相交链表
     */
    public class Solution {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            ListNode p1 = headA, p2 = headB;
            // A和B的变轨标识
            boolean changeA = false, changeB = false;
            while (p1 != null && p2 != null) {
                if (p1 == p2) {
                    return p1;
                }
                p1 = p1.next;
                p2 = p2.next;
                // 思路：如果两个链表后面相同，则补全前面为等长后按照相同步长去走，则一定是同时到达相同节点。
                // 补全前面的思路就是两个链表串起来，使用变轨的方式串起来，不改变原有链表。
                if (p1 == null && !changeA) {
                    p1 = headB;
                    changeA = true;
                }
                if (p2 == null && !changeB) {
                    p2 = headA;
                    changeB = true;
                }

            }
            return null;
        }
    }

    /**
     * 删除倒数第N个节点
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode deleteNode = new ListNode(-1);
        deleteNode.next = head;
        ListNode fastPtr = deleteNode, slowPtr = deleteNode;
        // 快指针先走n
        while (n-- > 0) {
            fastPtr = fastPtr.next;
        }
        // 当快指针到达最后一个节点时，满指针刚好指向倒数第n个节点的前一个节点
        while (fastPtr.next != null) {
            fastPtr = fastPtr.next;
            slowPtr = slowPtr.next;
        }
        slowPtr.next = slowPtr.next.next;
        return deleteNode.next;
    }

    /**
     * 977. 有序数组的平方
     */
    public int[] sortedSquares(int[] nums) {
        int splitFlag = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) {
                splitFlag = i;
                break;
            } else if (nums.length - 1 == i) {
                splitFlag = nums.length;
            }
        }
        int[] result = new int[nums.length];
        int i = splitFlag - 1, j = splitFlag, k = 0;
        while (i >= 0 && j < nums.length) {
            if (Math.abs(nums[i]) < Math.abs(nums[j])) {
                result[k++] = nums[i] * nums[i];
                i--;
            } else {
                result[k++] = nums[j] * nums[j];
                j++;
            }
        }
        while (i >= 0) {
            result[k++] = nums[i] * nums[i];
            i--;
        }
        while (j < nums.length) {
            result[k++] = nums[j] * nums[j];
            j++;
        }
        return result;
    }

    /**
     * 189. 轮转数组
     */
    public void rotate(int[] nums, int k) {
        for (int j = 0; j < k; j++) {
            int t = nums[nums.length - 1];
            for (int i = nums.length - 1; i > 0; i--) {
                nums[i] = nums[i - 1];
            }
            nums[0] = t;
        }
    }

    /**
     * 798. 得分最高的最小轮调
     */
    public int bestRotation(int[] nums) {
        int minK = 0, max = 0;
        for (int k = 0; k < nums.length; k++) {
            int count = 0;
            int move = nums.length - k;
            for (int i = 0; i < nums.length; i++) {
                int iMove = (i + move) % nums.length;
                if (nums[i] <= iMove) {
                    count++;
                }
            }
            if (max < count) {
                max = count;
                minK = k;
            }
        }
        return minK;
    }

    /**
     * 1109. 航班预订统计
     */
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] answer = new int[n];
        for (int[] booking : bookings) {

            int l = booking[0] - 1;
            int r = booking[1] - 1;
            int v = booking[2];
            answer[l] += v;
            if (r + 1 < n) {
                answer[r + 1] -= v;
            }
        }
        for (int i = 1; i < answer.length; i++) {
            answer[i] += answer[i-1];
        }
        return answer;
    }



    @Test
    public void test() {
        int[] nums = {2, 3, 1, 4, 0};
        int i = bestRotation(nums);
        System.out.println(i);
    }


    public static void main(String[] args) {
        int[] a = {-4, -1, 0, 3, 10};
        int[] ints = new Solutions().sortedSquares(a);
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
