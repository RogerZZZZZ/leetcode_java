import utils.ListNode;

/**
 * Created by rogerzzzz on 16/8/30.
 */
public class Main {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        LinkedListRandomNode node = new LinkedListRandomNode(head);
        node.getRandom();
        node.getRandom();
        node.getRandom();

    }
}
