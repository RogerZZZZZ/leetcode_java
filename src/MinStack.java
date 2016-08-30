import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//public class MinStack {
//    private ArrayList<Integer> resArray;
//
//    /** initialize your data structure here. */
//    public MinStack() {
//        resArray = new ArrayList<Integer>();
//    }
//
//    public void push(int x) {
//        resArray.add(0, x);
//    }
//
//    public void pop() {
//        resArray.remove(0);
//    }
//
//    public int top() {
//        return resArray.get(0).intValue();
//    }
//
//    public int getMin() {
//        Iterator iterator = resArray.iterator();
//        int minRes = (Integer)iterator.next();
//        while(iterator.hasNext()){
//            int nextInt = (Integer)iterator.next();
//            minRes = minRes < nextInt ? minRes : nextInt;
//        }
//        return minRes;
//    }
// run out of time
//}

class MinStack {
    private Node head;

    public void push(int x) {
        if(head == null)
            head = new Node(x, x);
        else
            head = new Node(x, Math.min(x, head.min), head);
    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.min;
    }

    private class Node {
        int val;
        int min;
        Node next;

        private Node(int val, int min) {
            this(val, min, null);
        }

        private Node(int val, int min, Node next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }
}


/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */