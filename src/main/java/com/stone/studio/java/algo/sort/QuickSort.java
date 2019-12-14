package com.stone.studio.java.algo.sort;

import java.util.Arrays;

/**
 * 快速排序算法实现
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] numbers =  {3, 1, 99, 30, 21, 78, 7, 9, 56, 10};

        System.out.println("before sort:" + Arrays.toString(numbers));
        QuickSort sort = new QuickSort();
        sort.sort(numbers);
        System.out.println("after sort:" + Arrays.toString(numbers));
    }

    public void sort(int[] numbers) {
        if(numbers.length > 0) {
            quickSort(numbers, 0, numbers.length-1);
        }
    }

    public void quickSort(int[] numbers, int left, int right) {
        if(left >= right) {
            return;
        }

        // flag是分隔数，先找出分隔数的位置
        int flag = numbers[left];
        int i=left, j=right;
        while(i<j) {

            // 从后往前找比分隔数小的
            while(numbers[j] > flag && i<j) {
                --j;
            }

            // 从前往后找比分隔数大的
            while(numbers[i] < flag && i<j) {
                ++i;
            }

            // 交换位置，实际上是找到数后跟分隔数交换位置（分隔数位置不停在变化）
            if(i<j) {
                swap(numbers, i, j);
            }
        }

        // j是分隔数（这里i应该等于j），递归分别排序j左边和右边的数组
        quickSort(numbers, left, j-1);
        quickSort(numbers, j+1, right);
    }

    // 交换数组中两个位置中的数
    public void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
