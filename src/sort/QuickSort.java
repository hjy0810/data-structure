package sort;

import java.util.Arrays;

/**
 * 学习于博客：[Java实现快排(图文讲解)](https://blog.csdn.net/m0_48013875/article/details/116034019)
 * 快速排序 又被称为 分区排序
 * 它的基本思想是:
 * 在待排序文件中任选一个记录(称为基准记录)，以它的排序码为基准值，
 * 将排序码比它小的记录都放到它的前面，排序码比它大的记录都放到它的后面，至此，该基准记录就找到了排序的
 * 最终位置，同时将文件划分成前、后两个区。
 * 在两个区上用同样的方法继续划分，直到每个区中最多只有一个记录，排序完成。
 *
 * 在快速排序过程中，比较和交换是从数组的两端向中间进行的，
 * 使得排序码较小或较大的记录一次就能够交换到数组的前面或后面，记录的每一次移动距离都较远，
 * 因而使得总的比较和移动次数较小。
 * 快速排序是对起泡排序的一种改进，它是目前所有的内部排序方法中速度最快的一种。
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr={3,2,10,5,4,6,8};
        sort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 设置两个数组索引，分别为低位索引 low 和高位索引 high，初始值分别为：low = 0 ；high = array.length-1；
     * 选取array[low]作为第一次划分的基准元素；
     * 若 low < high，从high位置开始向前搜索数组元素小于基准元素的数组元素索引，
     * 如果找到了，就将array[high]移动到array[low]的位置；
     * 然后从low的位置开始向后搜索数组元素大于基准元素的数组元素索引，
     * 如果找到了，就将array[low]移动到array[high]]的位置；
     * 重复上诉操作，直到低位索引和高位索引相遇，即low == high（即不满足条件low < high），
     * 此时就找到了基准元素的最终排序位置low，
     * 因为这个时候，low位置上的原值已经被移走，需要将基准放到该位置上，这个位置也是基准的最终排序位置。
     * 如此一次划分过程完毕。
     * @param arr
     * @param low
     * @param high
     */
    private static void sort(int[] arr,int low,int high){
        if (low<high) {//不满足该条件，程序直接退出
            int pivot=partition(arr,low,high);
            sort(arr,low,pivot-1);
            sort(arr,pivot+1,high);
        }
    }

    /**
     * 分区函数：一趟快速排序，返回值是本次基准的最终索引位置
     * @param arr
     * @param low
     * @param high
     * @return
     */
    private static int partition(int[] arr,int low,int high){
        int pivot=arr[low];////初始化基准，将低位索引值赋给基准
        while (low<high){
            while (low<high && arr[high]>=pivot){
                high--;
            }
            if (low<high) {
                arr[low]=arr[high];
            }
            while (low<high && arr[low]<pivot){
                low++;
            }
            if (low<high) {
                //将基准元素归位，基准元素的索引位置就是两个索引指针相遇的位置
                arr[high]=arr[low];
            }
        }
        arr[low]=pivot;
        return low;
    }
}
