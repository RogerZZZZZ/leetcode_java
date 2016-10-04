/**
 * Created by rogerzzzz on 16/9/9.
 * leetcode No.374 Guess number higher or lower
 */
public class GuessNumber {
    private int target = 6;
    public int guessNumber(int n) {
        return bsearch(1,n);
    }
    private int bsearch(int start,int end){
        if(start>end) return -1;//R u kidding me?
        if(guess(start)==0) return start;
        if(guess(end)==0) return end;
        int mid = start+(end-start)/2;
        if(guess(mid)==0) return mid;
        else if(guess(mid)==-1) return bsearch(start+1,mid-1);
        else return bsearch(mid+1,end-1);
    }


    public int guess(int guess){
        if(guess < target){
            return -1;
        }else if(guess > target){
            return 1;
        }else{
            return 0;
        }
    }
}
