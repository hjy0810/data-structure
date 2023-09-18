package sort;

import java.util.Arrays;

/**
 * 堆排序
 * 大顶堆：1。每个结点的值都大于左右子结点的值
 * 2。用于升序排序
 * 小顶堆：1。每个结点的值都小于左右子结点的值
 * 2。用于降序排序
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {2, 3, 6, 5, 7, 9, 8};

        //第一次编写的代码，heapify是获取一个二叉树中最大值的方法，但是时间复杂度为n
        //排序的整体时间复杂度为n^2
//        sort(arr);

        sort2(arr);


        System.out.println(Arrays.toString(arr));

    }

    /**
     * 堆排序：升序  =》 构建大顶堆
     * 堆排序的基本思想是：
     * 1。将待排序序列构造成一个大顶堆
     * 从最后一个非叶子结点开始，从下到上依次调整
     * 1）最后一个非叶子结点 =》 length/2 - 1
     * 2) 第n个结点的父结点 =》 （n - 1）/ 2
     * 3) 第n个结点的左结点 =》  2*n + 1
     * 2) 第n个结点的右结点 =》  2*n + 2
     * 2。此时，整个序列的最大值就是堆顶的根节点。
     * 3。将其与末尾元素进行交换，此时末尾就为最大值。
     * 4。然后将剩余n-1个元素重新构造成一个堆，这样会得到n个元素的次小值。如此反复执行，便能得到一个有序序列了。
     *
     * @param arr
     */
    public static void sort2(int[] arr) {
        int length = arr.length;
        //首次构建大顶堆 =》 9 7 2 5 3 6 8
        for (int i = length / 2 - 1; i >= 0; i--) {
            heapify2(arr, i, length);
        }

        //将arr[0]也就是最大值替换给arr[j]，替换之后不是大顶堆了，前面的非顺序元素再次改造
        for (int j = length - 1; j > 0; j--) {
            //首次构建大顶堆之后，将arr[0]和最后一个元素互换位置
            int temp = arr[0];
            arr[0] = arr[j];
            arr[j] = temp;
            //这里的行参j代表数组长度，上面的代表索引，因此每次循环长度会减1
            heapify2(arr, 0, j);
        }
    }

    /**
     * 将索引为i的（子）树改造为大顶堆
     * 注意：这里的子树已经过初次的改造，因此此时仅有root的值被修改了
     * @param arr
     * @param i      索引
     * @param length
     */
    public static void heapify2(int[] arr, int i, int length) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < length && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < length && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            heapify2(arr, largest, length);
        }
    }


    public static void sort(int[] arr) {
        int length = arr.length;
        while (length > 1) {
            heapify(arr, length);
            int temp = arr[0];
            arr[0] = arr[length - 1];
            arr[length - 1] = temp;
            length--;
        }
    }

    /**
     * 获取最大值
     *
     * @param arr
     */
    public static void heapify(int[] arr, int length) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            int largest = i;
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            if (left < length && arr[left] > arr[largest]) {
                largest = left;
            }
            if (right < length && arr[left] < arr[right]) {
                largest = right;
            }
            if (largest != i) {
                int temp = arr[i];
                arr[i] = arr[largest];
                arr[largest] = temp;
            }
        }
    }
}
