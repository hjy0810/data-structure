package search;

/**
 * 学习于：https://labuladong.github.io/algo/di-ling-zh-bfe1b/wo-xie-le--3c789/
 * 二分法只适合有序序列的查找
 *
 * @author hjy
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {2, 10, 15, 26, 35, 48, 57, 60, 78, 88};
        //递归
        //System.out.println(binSearch(arr,0,arr.length-1,60));
        //System.out.println(binSearch2(arr,0,arr.length-1,60));

        //非递归
        //System.out.println(binSearch3(arr, 60));


        int[] arr2 = {2, 10, 15, 60, 60, 60, 60, 60, 78, 88};
        //非递归找左边界
        //System.out.println(binSearch4(arr2,60));

        //非递归找右边界
        System.out.println(binSearch5(arr2, 60));
    }

    /**
     * 递归方式
     * @param arr
     * @param left
     * @param right
     * @param target 目标值
     * @return 找到返回索引，没找到放回-1
     */
    private static int binSearch(int[] arr, int left, int right, int target) {
        int mid = (left + right) / 2;
        if (left <= right) {
            if (arr[mid] == target) {
                return mid;
            } else if (target < arr[mid]) {
                return binSearch(arr, left, mid - 1, target);
            } else {
                return binSearch(arr, mid + 1, right, target);
            }
        }
        return -1;
    }

    private static int binSearch2(int[] arr, int left, int right, int target) {
        if (left > right) {
            return -1;
        }
        int mid = left + (right - left) / 2;
        if (arr[mid] > target) {
            return binSearch2(arr, left, mid - 1, target);
        } else if (arr[mid] < target) {
            return binSearch2(arr, mid + 1, right, target);
        } else {
            return mid;
        }
    }

    /**
     * 非递归
     * @param arr
     * @param target
     * @return
     */
    private static int binSearch3(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 找左边界
     * @param arr    int[] arr2={2,10,15,60,60,60,60,60,78,88};
     * @param target
     * @return       返回目标值的左边界，如arr2中的第一个60的索引
     */
    private static int binSearch4(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target > arr[mid]) {
                left = mid + 1;
            } else if (target < arr[mid]) {
                right = mid - 1;
            } else if (target == arr[mid]) {
                right = mid - 1;
            }
        }
        return left;
    }

    /**
     * 找右边界
     * @param arr
     * @param target
     * @return      返回目标值的左边界，如arr2中的最后一个60的索引
     */
    private static int binSearch5(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target > arr[mid]) {
                left = mid + 1;
            } else if (target < arr[mid]) {
                right = mid - 1;
            } else if (target == arr[mid]) {
                left = mid + 1;
            }
        }
        return right;
    }
}
