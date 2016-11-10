/**
 * Created by rogerzzzz on 2016/10/26.
 */
public class HeapSort {
    private int[] arr;
    public HeapSort(int[] arr){
        this.arr = arr;
//        System.out.println(arr);
        for(int t : arr){
            System.out.print(t + " ");
        }
        for(int i = arr.length/2; i >= 0; i--){
            precDown(i);
            System.out.print("round result:" + i + " ");
            for(int t : arr){
                System.out.print(t + " ");
            }
            System.out.println();
        }
        System.out.println("result:");
        for(int t : arr){
            System.out.print(t + " ");

        }
    }

    private void precDown(int i){
        int child;
        int tmp;
        for(tmp = arr[i]; leftChild(i) < arr.length; i = child){
            child = leftChild(i);
            System.out.println("child: " + child + " i: " + i);
            if(child != arr.length - 1 && arr[child] - arr[child+1] < 0){
                child++;
            }
            if(tmp - arr[child] < 0){
                arr[i] = arr[child];
            }else{
                break;
            }
        }
        arr[i] = tmp;
    }

    private int leftChild(int i){
        return 2*i + 1;
    }
}
