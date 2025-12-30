package com.itheima;

public class leecode4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1=nums1.length;
        int length2=nums2.length;
        int totalLength=length1+length2;
        if(totalLength%2==1){//奇数 只有一个中位数
            int minIndex=totalLength/2;
            double ans=getKthNum(nums1,nums2,minIndex+1);
            return ans;

        }
        else {
            int midIndex1 = totalLength / 2 - 1, midIndex2 = totalLength / 2;
            double ans=(getKthNum(nums1,nums2,midIndex2+1)+getKthNum(nums1,nums2,midIndex1+1))/2;
            return ans;
        }
    }
    public int getKthNum(int[] nums1,int[] nums2,int k) {//找到两个数组中第k小的数字
        int length1 = nums1.length;
        int length2 = nums2.length;
        int index1 = 0;
        int index2 = 0;
        while (true){
            if (index1 == length1) {
                return nums2[index2 + k - 1];
            }
        if (index2 == length2) {
            return nums1[index1 + k - 1];
        }
        if (k == 1) {
            // 直接比较当前两个数组的起始元素，较小者即为答案
            return Math.min(nums1[index1], nums2[index2]);
        }
        int half = k / 2;
            int newIndex1 = Math.min(index1 + half, length1)-1;
            int newIndex2 = Math.min(index2 + half, length2)-1;
        int mid1 = nums1[newIndex1];
        int mid2 = nums2[newIndex2];
        if (mid1 <= mid2) {
            k=k-(newIndex1-index1+1);
            index1=newIndex1+1;
        }
        else {
            k=k-(newIndex2-index2+1);
            index2=newIndex2+1;
        }
    }
    }
}
