package sort;

import java.util.Arrays;

/**
 * @author 黄建永
 * @version 1.0
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr={4,2,10,5,3,8,7,9,6};
        System.out.println(Arrays.toString(sort2(arr)));
    }

    /**
     * 希尔排序——交换法
     * @param arr
     * @return
     */
    private static int[] sort(int[] arr){
        int length=arr.length;
        for (int gap = length/2; gap >0; gap/=2) {
            for (int i = gap; i < length; i++) {
                for (int j = i-gap; j >=0 ; j-=gap) {
                    if (arr[j]>arr[j+gap]) {
                        int temp=arr[j];
                        arr[j]=arr[j+gap];
                        arr[j+gap]=temp;
                    }
                }
            }
        }
        return arr;
    }

    /**
     * 希尔排序——插入法
     * @param arr
     * @return
     */
    private static int[] sort2(int[] arr){
        int length=arr.length;
        for (int gap = length/2; gap >0 ; gap/=2) {
            for (int i = gap; i < length; i++) {
                int j=i-gap;//有序序列的最后一个索引，从后往前遍历找位置
                int val=arr[i];
                while (j>=0 && arr[j]>val){
                    arr[j+gap]=arr[j];
                    j-=gap;
                }
                arr[j+gap]=val;
            }
        }
        return arr;
    }
}
