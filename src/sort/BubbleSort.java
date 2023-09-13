package sort;

import java.util.Arrays;

/**
 * @author 黄建永
 * @version 1.0
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {3, 2, 10, 5, 4};
        System.out.println(Arrays.toString(sort(arr)));
        System.out.println(Arrays.toString(sort2(arr)));
    }

    private static int[] sort(int[] arr) {
        int length = arr.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    //优化
    private static int[] sort2(int[] arr) {
        int length = arr.length;
        boolean flag = false;
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (!flag) {//当前内循环执行一遍没有发生交换，说明前面的顺序都是顺序，退出外循环
                break;
            } else {//当前内循环执行一遍发生交换，重置flag，以便进行下次循环
                flag = false;
            }
        }
        return arr;
    }
}
