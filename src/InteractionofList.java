import java.util.ArrayList;
import java.util.List;

/**
 * Created by rogerzzzz on 2016/10/22.
 */
public class InteractionofList {
    private ArrayList<Integer> list1 = new ArrayList<Integer>();
    private ArrayList<Integer> list2 = new ArrayList<Integer>();

    public InteractionofList(ArrayList<Integer> list1, ArrayList<Integer> list2){
        this.list1 = list1;
        this.list2 = list2;
    }

    public ArrayList<Integer> getInteraction(){
        ArrayList<Integer> resList = new ArrayList<Integer>();
        int len1 = list1.size();
        int len2 = list2.size();
        int index1 = 0, index2 = 0;
        while(index1 < len1 && index2 < len2){
            if(list1.get(index1).equals(list2.get(index2))){
                resList.add(list1.get(index1));
                index1++;
                index2++;
            }else if(list1.get(index1) < list2.get(index2)){
                index1++;
            }else {
                index2++;
            }
        }
//
//        for(int i = index1; i < len1; i++){
//            resList.add(list1.get(i));
//        }
//
//        for(int i = index2; i < len2; i++){
//            resList.add(list2.get(i));
//        }
        System.out.println(resList);
        return resList;
    }
}
