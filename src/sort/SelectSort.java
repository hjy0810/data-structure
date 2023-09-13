package sort;

import java.util.Arrays;

/**
 * @author 黄建永
 * @version 1.0
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr={3,2,10,5,4};
        System.out.println(Arrays.toString(selectSort(arr)));
    }

    /**
     * 规则：
     *      第一次从arr[0]~arr[n-1]中选取最小值，与arr[0]交换，
     *      第二次从arr[1]~arr[n-1]中选取最小值，与arr[1]交换
     *      ……
     *      第n-1次从arr[n-2]~arr[n-1]中选取最小值，与arr[n-2]交换
     * @param arr
     * @return
     */
    private static int[] selectSort(int[] arr){
        int length=arr.length;
        for (int i = 0; i < length-1; i++) {
            int minIndex=i;//假定最小值的索引为当前循环的初始项
            for (int j = i+1; j < length; j++) {
                if (arr[minIndex]>arr[j]) {
                    minIndex=j;
                }
            }
            //内循环执行完毕，找到值最小的索引index，并让索引为index、i的值互换位置
            if (minIndex!=i) {
                int temp=arr[i];
                arr[i]=arr[minIndex];
                arr[minIndex]=temp;
            }
        }
        return arr;
    }
}
