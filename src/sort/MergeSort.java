package sort;

import java.util.Arrays;

/**
 * 学习于[排序算法——基于分治思想的排序](https://blog.csdn.net/vcj1009784814/article/details/114948851)
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr={2,4,5,1,6,5,8,7};
        sort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    private static void sort(int[] arr,int left,int right){
        if (left<right){
            int mid=(left+right)/2;
            sort(arr,left,mid);//左递归
            sort(arr,mid+1,right);//右递归
            merge(arr,left,mid,right);
        }
    }

    /**
     * 思想：
     * 先将数组平均分为左右两个部分，然后对左右两部分数组，递归调用归并排序算法，
     * 逻辑上可简单理解为，先把左右两个分区排好序，然后再对左右两个有序分区进行合并操作。
     * @param arr
     * @param left
     * @param mid  中间索引，也是左递归的最后一个索引
     * @param right
     */
    private static void merge(int[] arr,int left,int mid,int right){
        //定义左右 序列的初始值
        int posL=left;
        int posR=mid+1;
        //定义 辅助数组 及 初始索引
        int[] temp=new int[arr.length];
        int tempIndex=0;
        //合并左右有序分区
        while (posL<=mid && posR<=right){
            if (arr[posL]<arr[posR]){
                temp[tempIndex]=arr[posL];
                posL++;
            }else {
                temp[tempIndex]=arr[posR];
                posR++;
            }
            tempIndex++;
        }

        //左分区 还有元素，依次添加到辅助数组中
        while (posL<=mid) {
            temp[tempIndex]=arr[posL];
            posL++;
            tempIndex++;
        }

        while (posR<=right){
            temp[tempIndex]=arr[posR];
            posR++;
            tempIndex++;
        }

        //将合并结果，写入原数组
        for (int i = left; i <= right; i++) {
            arr[i]=temp[i-left];
        }
    }
}
