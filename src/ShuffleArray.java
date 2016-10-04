import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by rogerzzzz on 2016/10/4.
 */
public class ShuffleArray {
    private int[] nums = null;
    private Random random = null;
    public ShuffleArray(int[] nums) {
        this.nums = nums;
        random = new Random(System.currentTimeMillis());
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return Arrays.copyOf(nums,nums.length); // just return a copy.
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int[] ans = Arrays.copyOf(nums,nums.length); // create a copy
        for(int i = 1 ; i < nums.length ; i++){
            int swapIndex = random.nextInt(i+1); // generate a random number within visited elements including current index.
            swap(ans,i,swapIndex); // swap the index
        }
        return ans;
    }
    private void swap(int[] ans, int from , int to){
        int temp = ans[from];
        ans[from] = ans[to];
        ans[to] = temp;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
