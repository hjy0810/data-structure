package leetcode.debug.array;

/**
 * @author:hjy
 * @date 2023/11/11
 */
/* 4. 寻找两个正序数组的中位数 */
public class findMedianSortedArrays_4 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1=new int[]{1,2};
        int[] nums2=new int[]{3,4};
        double res = solution.findMedianSortedArrays(nums1,nums2);
        System.out.println(res);
    }
}

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] resArray=mergeArray(nums1,nums2);
        System.out.println(resArray);
        int len=resArray.length;
        if(len%2==0){
            return (double)(resArray[len/2]+resArray[len/2-1])/2;
        }
        return (double)(resArray[len/2]);
    }

    public int[] mergeArray(int nums1[],int nums2[]){
        int len1=nums1.length;
        int len2=nums2.length;
        if(len1==0) return nums2;
        if(len2==0) return nums1;
        int p1=0;
        int p2=0;
        int[] res = new int[len1+len2];
        int i=0;
        while(p1<len1 && p2<len2){
            while(p1<len1 && p2<len2 && nums1[p1]<nums2[p2]){
                res[i]=nums1[p1];
                i++;
                p1++;
            }
            while(p1<len1 && p2<len2 && nums1[p1]>=nums2[p2]){
                res[i]=nums2[p2];
                i++;
                p2++;
            }
        }
        if(p1==len1){
            while(p2<len2){
                res[i]=nums2[p2];
                i++;
                p2++;
            }
        }
        if(p2==len2){
            while(p1<len1){
                res[i]=nums1[p1];
                i++;
                p1++;
            }
        }
        return res;
    }
}
