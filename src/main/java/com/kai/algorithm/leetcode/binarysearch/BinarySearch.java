package com.kai.algorithm.leetcode.binarysearch;

import java.util.Arrays;

/**
 * @ClassName: BinarySearch
 * @Description: 二分查找
 * @Version: 1.0
 * @Author: Kai
 * @Date: 2023年03月23日 22:41:57
 **/
public class BinarySearch {
    /**
     * @Description: while模式
     * @Author: Kai
     * @Date: 2023/3/23 22:58
     */
    public static int binarySearchWhile(int[] nums, int key) {
        //排序
        Arrays.sort(nums);
        //定义第二分之一n项
        int start = 0;
        int end = nums.length - 1;
        //如果key过小/过大或者长度为0则返回-1
        if (nums[start] > key || nums[end] < key || end < start) return -1;
        //循环
        while (start <= end) {
            //取中间值
            int middle = (start + end) / 2;
            //二分
            if (nums[middle] > key) end = middle - 1;
            else if (nums[middle] < key) start = middle + 1;
            else return middle;
        }
        return -1;
    }

    /**
     * @Description: 递归模式
     * @Author: Kai
     * @Date: 2023/3/23 22:59
     */
    public static int binarySearchWhileRecursion(int[] nums, int key, int start, int end) {
        //如果key过小/过大或者长度为0则返回-1
        if (nums[start] > key || nums[end] < key || end < start) return -1;
        //取中间值
        int middle = (start + end) / 2;
        //递归
        if (nums[middle] > key) return binarySearchWhileRecursion(nums, key, start, middle - 1);
        else if (nums[middle] < key) return binarySearchWhileRecursion(nums, key, middle + 1, end);
        else return middle;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        System.out.println(binarySearchWhile(nums, 12));
        System.out.println(binarySearchWhileRecursion(nums, 12, 0, nums.length - 1));
    }

}