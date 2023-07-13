package com.kai.algorithm.leetcode.binarysearch;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName: BinarySearch287
 * @Description: 给定一个包含 n + 1 个整数的数组nums ，其数字都在[1, n]范围内（包括 1 和 n），可知至少存在一个重复的整数。
 *                  假设 nums 只有 一个重复的整数 ，返回这个重复的数 。
 * @Version: 1.0
 * @Author: Kai
 * @Date: 2023年04月02日 18:33:43
 **/
public class BinarySearch287 {
    //方法1 使用hash查找
    public int findDuplicate1(int[] nums) {
        Set set = new HashSet<Integer>();
        for (int num : nums) {
            if (set.contains(num)) {
                return num;
            } else {
                set.add(num);
            }
        }
        return -1;
    }

    //方法2 二分查找
    //因为数字是从1到n，所以不需要考虑数字在数组中的位置，只是按照数字的大小二分即可
    public int findDuplicate(int[] nums) {
        //定义开始和结束位置
        int start = 1;
        int end = nums.length - 1;
        //定义计数器
        int count = 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] < mid) {
                    count++;
                }
            }

        }
        return -1;
    }

    public static void main(String[] args) {
        BinarySearch287 binarySearch = new BinarySearch287();
        int[] nums = {1,1};
        System.out.println(binarySearch.findDuplicate(nums));
    }
}
