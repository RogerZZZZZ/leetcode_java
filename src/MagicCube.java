import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by rogerzzzz on 2016/10/28.
 */
public class MagicCube {
    private static int M = 0;
    private static int P = 0;
    private static int N = 0;
    private static int[] bigCube;
    private static int[][] smallCube;
    private static int[] smallCubeWidth;
    private static int[] smallCubeIndex;
    private static int[][] pos = null;
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int index = 0;
        while(true){
            String line = scanner.nextLine();
            String[] tmp = line.split(" ");
            if(index == 0){
                M = Integer.parseInt(tmp[0]);
                N = Integer.parseInt(tmp[1]);
                P = Integer.parseInt(tmp[2]);
                smallCube = new int[N][];
                smallCubeWidth = new int[N];
                smallCubeIndex = new int[N];
                pos = new int[N][3];
                if(M < 2 || M > 7) throw new Exception("M is out of range");
                if(N < 1 || N > 12) throw new Exception("N is out of range");
                if(P < 3 || P > 5) throw new Exception("P is out of range");
            }else if(index == 1){
                bigCube = new int[tmp.length];
                bigCube = stringParser(tmp);
            }else if(index >= 2){
                int width = Integer.parseInt(tmp[0]);
                smallCube[index-2] = new int[(int) Math.pow(width, 3)];
                for(int i = 1; i <= Math.pow(width, 3); i++){
                    int t = Integer.parseInt(tmp[i]);
                    if(t < 0 || t >= P) throw new Exception("The Input of the small cube is invalid,");
                    smallCube[index-2][i-1] = t;
                }
            }
            index++;
            if(index > N + 1) break;
        }
        for(int i = 0; i < N; i++){
            smallCubeIndex[i] = 0;
            smallCubeWidth[i] = (int) Math.pow(smallCube[i].length, 1.0/3);
        }
        MagicCube magicCube = new MagicCube();
        magicCube.insertCube(smallCubeIndex, 0);
        for(int[] i: pos){
            for(int t: i){
                System.out.print(t + " ");
            }
            System.out.println();
        }
    }

    /**
     * Function stringParser
     * Description: make a string array to int array.
     * @param input [String[]]
     * @return [int[]]
     */
    private static int[] stringParser(String[] input){
        int[] res = new int[input.length];
        int index = 0;
        for(String tmp : input){
            res[index++] = Integer.parseInt(tmp);
        }
        return res;
    }

    /**
     * Function cubeIsValid
     * check if the cube is all empty
     * @param cubeArr [int[]]
     * @return boolean
     */
    private boolean cubeIsValid(int[] cubeArr){
        for(int tmp: cubeArr){
            if(tmp != 0) return false;
        }
        return true;
    }

    /**
     * Function insertCube
     * @param smallCubeIndex [int[]]
     * @param floor [int]
     * @return boolean
     */
    private boolean insertCube(int[] smallCubeIndex, int floor){
        int[] tmp = new int[bigCube.length];
        System.arraycopy(bigCube,0,tmp,0,bigCube.length);
        boolean flag = false;
        if(floor < N - 1 && smallCubeIndex[floor] < Math.pow((M-smallCubeWidth[floor]+1), 3)){
            flag = insertCube(smallCubeIndex, floor + 1);
        }else if(floor == N - 1 && smallCubeIndex[floor] < Math.pow((M-smallCubeWidth[floor]+1), 3)){
            int[] bigCubeArr = addCube(smallCubeIndex, tmp);
            if(cubeIsValid(bigCubeArr)){
                flag = true;
                return flag;
            }else if(smallCubeIndex[floor] == Math.pow((M-smallCubeWidth[floor]+1), 3) - 1){
                if(floor - 1 >= 0){
                    int i = floor;
                    smallCubeIndex[floor] = 0;
                    while(smallCubeIndex[i-1] == Math.pow((M-smallCubeWidth[i-1]+1), 3) - 1 && i - 1 > 0){
                        smallCubeIndex[i-1] = 0;
                        i--;
                    }
                    smallCubeIndex[i-1]++;
                    smallCubeIndex[floor] = 0;
                }
            }
            smallCubeIndex[floor]++;
            flag = insertCube(smallCubeIndex, floor);
        }
        return flag;
    }

    /**
     * Function addCube
     * add small cube(s) to the big cube
     * @param smallCubeIndex [int[]]
     * @param bigCubeArr [int []]
     * @return int[]
     */
    private int[] addCube(int[] smallCubeIndex, int[] bigCubeArr){
        int arrIndex = 0;
        for(int index: smallCubeIndex){
            LinkedList<Integer> list = findSmallCubePosition(index, smallCubeWidth[arrIndex], M, arrIndex);
            int tmp = 0;
            for(int i: list){
                bigCubeArr[i] = (bigCubeArr[i] + smallCube[arrIndex][tmp++]) % P;
            }
            arrIndex++;
        }
        return bigCubeArr;
    }

    /**
     * Function findSmallCubePosition
     * @param index [int]
     * @param smallLength [int]
     * @param bigLength [int]
     * @param floor [int]
     * @return LinkedList
     */
    private LinkedList<Integer> findSmallCubePosition(int index, int smallLength, int bigLength, int floor){
        int offsetY = (index / (bigLength - smallLength + 1)) % (bigLength-smallLength+1);
        int offsetX = index % (bigLength - smallLength + 1);
        int offsetPage = (int) (index / Math.pow(bigLength - smallLength + 1, 2));
        LinkedList<Integer> list = new LinkedList<Integer>();
        for(int i = 0; i < smallLength; i++){
            for(int j = 0; j < smallLength; j++){
                for(int k = 0; k < smallLength; k++){
                    int tmp = (int) (offsetX + j + i*bigLength + offsetY*bigLength + offsetPage * Math.pow(bigLength, 2));
                    list.add((int) (tmp + k * Math.pow(bigLength, 2)));
                    pos[floor][0] = offsetPage;
                    pos[floor][1] = offsetY;
                    pos[floor][2] = offsetX;
                }
            }
        }
        Collections.sort(list);
        return list;
    }
}
