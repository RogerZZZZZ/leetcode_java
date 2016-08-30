/**
 * Created by rogerzzzz on 16/8/30.
 */
public class Main {
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-100);
        minStack.push(1);
        minStack.push(-1);
        minStack.push(2);
        minStack.push(-2);// -2 2 -1 1
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
        System.out.print(minStack.top());

    }
}
