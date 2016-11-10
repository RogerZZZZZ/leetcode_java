import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by rogerzzzz on 2016/10/27.
 */
public class WirelessRouters {
    private static int[] isCovered;
    private static List<LinkedList<Integer>> connection = new LinkedList<LinkedList<Integer>>();
    private static int[] satisifyArray;
    private static int roomNum;


    public static void main(String[] args) throws Exception{
        Scanner s = new Scanner(System.in);
        /**
         * index
         * 0: input N(2<=N<=1000) & M(1<=M<=N, M<=100)
         * 1: input array S (1 <= S[i] <= 10)
         * 2 - end: contain two integers x, y, which represents a door is between room x and y.
         */
        int index = 0;
//        int roomNum = 5;
        int routerNum = 1;

        System.out.println("input some parameter:");
        while(true){
            String line = s.nextLine();
            if(index == 0){
                String[] tmp = line.split(" ");
                roomNum = Integer.parseInt(tmp[0]);
                routerNum = Integer.parseInt(tmp[1]);
                if(roomNum > 1000 || roomNum < 2){
                    throw new Exception("N is out of range");
                }
                if(routerNum < 1 || routerNum > roomNum || routerNum > 100){
                    throw new Exception("M is out of range");
                }
                for(int i = 0; i < roomNum; i++){
                    connection.add(new LinkedList<Integer>());
                }
                isCovered = new int[roomNum];
                for(int i = 0; i < roomNum; i++){
                    isCovered[i] = 0;
                }
            }else if(index == 1){
                satisifyArray = stringParser(line.split(" "));
                if(satisifyArray.length > roomNum) throw new Exception("the length of S is illegal");
            }else if(index >= 2){
                int[] rooms = stringParser(line.split(" "));
                int room1 = rooms[0] - 1;
                int room2 = rooms[1] - 1;
                if(connection.get(room1).size() >= 3 || connection.get(room2).size() >= 3) throw new Exception("one room has more than three doors");
                connection.get(room1).add(room2);
                connection.get(room2).add(room1);
            }
            index++;
            if(index > roomNum) break;
        }
        int ifCoverPrevious = 0;
        int ifNotCoverPrevious = 0;
        int res = findMax(0, routerNum, ifCoverPrevious, ifNotCoverPrevious, isCovered);
        System.out.println(res);
    }

    private static int findMax(int roomIndex, int routerLeft, int ifCoverPrevious, int ifNotCoverPrevious, int[] coverStatus){
        if(routerLeft == 0 || roomIndex > roomNum - 1){
            return Math.max(ifCoverPrevious, ifNotCoverPrevious);
        }
        int[] hasCover = new int[coverStatus.length];
        int[] notCover = new int[coverStatus.length];
        int ciIfcoverPrevious = ifCoverPrevious;
        System.arraycopy(coverStatus, 0, hasCover, 0, isCovered.length);
        System.arraycopy(coverStatus, 0, notCover, 0, isCovered.length);
        if(hasCover[roomIndex] == 0){
            hasCover[roomIndex] = 1;
            ifCoverPrevious += satisifyArray[roomIndex];
        }
        for(int j = 0; j < connection.get(roomIndex).size(); j++){
            int tmp = connection.get(roomIndex).get(j);
            if(hasCover[tmp] == 0){
                ifCoverPrevious += satisifyArray[tmp];
                hasCover[tmp] = 1;
            }
        }
        ifCoverPrevious = findMax(roomIndex+1, routerLeft-1,ifCoverPrevious, ifNotCoverPrevious, hasCover);
        ifNotCoverPrevious = findMax(roomIndex+1, routerLeft, ciIfcoverPrevious, ifNotCoverPrevious, notCover);
        return Math.max(ifCoverPrevious, ifNotCoverPrevious);
    }

    private static int[] stringParser(String[] input){
        int[] res = new int[input.length];
        int index = 0;
        for(String tmp : input){
            res[index++] = Integer.parseInt(tmp);
        }
        return res;
    }
}
