package sort;

import java.util.Arrays;

/**
 * @author 黄建永
 * @version 1.0
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr={3,2,10,5,4};
        System.out.println(Arrays.toString(sort(arr)));
    }

    /**
     * 排序规则：
     *      把n个待排序的元素看成为一个有序表和一个无序表，开始时有序表中只包含一个元素，
     *      无序表中包含有n-1个元素，排序过程中每次从无序表中取出第一个元素，把它依次与
     *      有序表元素的数进行比较，将它插入到有序表中的适当位置，使之成为新的有序表。
     * @param arr
     * @return
     */
    private static int[] sort(int[] arr){
        int length=arr.length;
        for (int i = 1; i < length; i++) {//第一个数有序，从第二个数开始无序序列
            int insertVal=arr[i];//记录无序表的第一个数，即将插入到有序表的那个数
            int insertIndex=i-1;
            //遍历有序表，将有序表中大于插入数的数依次后移
            while (insertIndex>=0 && insertVal<arr[insertIndex]){
                arr[insertIndex+1]=arr[insertIndex];
                insertIndex--;
            }
            //在有序表中第一个 <= 插入数的后面 插入要插入的数
            if (insertIndex+1!=i) {
                arr[insertIndex+1]=insertVal;
            }
        }
        return arr;
    }
}
