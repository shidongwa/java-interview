package com.stone.studio.java.algo.sort;

import java.util.Arrays;

/**
 * 归并排序是先拆分排序再归并
 * 拆分最小粒度是每组一个元素，再两两归并
 * 空间复杂度O(N),时间复杂度O(NlogN)
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] numbers =  {3, 1, 99, 30, 21, 78, 7, 9, 56, 10};

        System.out.println("before sort:" + Arrays.toString(numbers));
        MergeSort sort = new MergeSort();
        sort.mergeSort(numbers, 0, numbers.length -1);
        System.out.println("after sort:" + Arrays.toString(numbers));

        System.out.println("--------------------------------------");

        // same element in array
        numbers =  new int[]{3, 1, 99, 30, 21, 30, 7, 9, 56, 10};
        System.out.println("before sort:" + Arrays.toString(numbers));
        sort.mergeSort(numbers, 0, numbers.length - 1);
        System.out.println("after sort:" + Arrays.toString(numbers));
    }

    public void mergeSort(int[] numbers, int left, int right) {
        if(left >= right) {
            return;
        }

        int mid = (left+right+1)/2;
        mergeSort(numbers, left, mid-1);
        mergeSort(numbers, mid, right);
        merge(numbers, left, mid, right);
    }

    public void merge(int[] numbers, int left, int mid, int right) {
        //创建临时数组
        int[] newArray = new int[numbers.length];

        int i = left;
        int j = mid;
        int k = left;
        while(k <= right) {
            // 数组前部分已遍历完成, 这里需要处理好边界条件，否则结果数组就乱掉了
            while(i >= mid && j<right+1) {
                newArray[k++] = numbers[j++];
            }

            // 数组后部分已遍历完成, 这里需要处理好边界条件，否则结果数组就乱掉了
            while(j > right && i<mid) {
                newArray[k++] = numbers[i++];
            }

            // 直接比较数组两部分大小，小的放到临时数组中, 这里需要处理好边界条件，否则结果数组就乱掉了
            if(i<=mid && j<=right) {
                if(numbers[i] < numbers[j]) {
                    newArray[k++] = numbers[i++];
                } else {
                    newArray[k++] = numbers[j++];
                }
            }
        }

        // 从临时数组copy回数据
        System.arraycopy(newArray, left, numbers, left, right - left +1);
    }
}
