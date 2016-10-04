import utils.ListNode;

import java.util.Random;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class LinkedListRandomNode {
    private ListNode head;
    private Random random;
    private int length = 0;

    /** @param head The linked list's head.
    Note that the head is guaranteed to be not null, so it contains at least one node. */
    public LinkedListRandomNode(ListNode head) {
        this.head = head;
        this.random = new Random();
        ListNode tmp = this.head;
        while(tmp.next != null){
            this.length++;
            tmp = tmp.next;
        }

    }

    /** Returns a random node's value. */
    public int getRandom() {
        ListNode res = head;
        int ranNum = random.nextInt(length + 1);
        int index = 0;
        while(index < ranNum){
            res = res.next;
            index++;
        }
        return res.val;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */