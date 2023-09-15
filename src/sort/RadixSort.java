package sort;

import java.util.Arrays;

/**
 * 基数排序法是效率高的稳定性排序法，是桶排序的扩展
 * 实现方法：
 * 1。将所有待比较数值统一为同样的数位长度，数位较短的数前面补零。
 * 2。然后，从最低位开始，依次进行一次排序。这样从最低位排序一直到最高位排序完成以后, 数列就变成一个有序序列。
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {35, 2, 40, 100, 5, 76, 854, 653, 1009};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 该方法只适合正整数，若有负数，需改进
     *
     * @param arr
     */
    private static void sort(int[] arr) {
        //1。找到最大整数的长度
        int length = arr.length;
        int max = arr[0];//假定第一个就是最大的那个数
        for (int i = 0; i < length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        //找到最大数后求出这个数的长度，它决定了桶排序的外层循环次数
        int maxLength = (max + "").length();

        int[][] bucket = new int[10][length];
        int[] numsOfBucket = new int[10];//定义每个桶中的数据个数，初始都为0
        //2。遍历，放数+取数
        for (int i = 0, base = 1; i < maxLength; i++, base *= 10) {
            //往桶中放数
            for (int j = 0; j < length; j++) {
                int digit = arr[j] / base % 10;
                bucket[digit][numsOfBucket[digit]] = arr[j];
                numsOfBucket[digit]++;
            }
            //从桶中取数
            int index = 0;
            for (int k = 0; k < 10; k++) {
                if (numsOfBucket[k] != 0) {
                    for (int j = 0; j < numsOfBucket[k]; j++) {
                        arr[index++] = bucket[k][j];
                    }
                }
                //TODO：遍历当前桶之后要让当前桶中的数量置0
                numsOfBucket[k] = 0;
            }
        }
    }
}
