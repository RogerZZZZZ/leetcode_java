/**
 * Created by rogerzzzz on 2016/10/31.
 */
public class Solution {
    private int solutionNum = 0;
    public int solution(int N){
        String arr = N + "";
        StringBuffer buffer = new StringBuffer(arr);
        findDiff(buffer, 0, arr.length()-1);
        return solutionNum;
    }

    private void findDiff(StringBuffer arr, int index1, int index2){
        if(index1 == index2){
            solutionNum++;
            System.out.println(arr);
            return;
        }else {
            for(int i=index1; i <= index2; i++){
                if(isValid(arr, index1, i)){
                    arr = swapElement(arr, index1, i);
                    findDiff(arr, index1 + 1, index2);
                    arr = swapElement(arr, index1, i);
                }
            }
        }
    }

    /**
     * Function swapElement
     *
     * @param buffer
     * @param index1
     * @param index2
     * @return return a StringBuffer that has been swapped.
     */
    private StringBuffer swapElement(StringBuffer buffer, int index1, int index2){
        StringBuffer res = buffer;
        char tmp = res.charAt(index1);
        res.setCharAt(index1, res.charAt(index2));
        res.setCharAt(index2, tmp);
        return res;
    }

    private boolean isValid(StringBuffer arr, int indexA, int indexB){
        if(indexB > indexA){
            for(int i = indexA; i < indexB; i++){
                if(arr.charAt(i) == arr.charAt(indexB)){
                    return false;
                }
            }
        }
        return true;
    }
}
